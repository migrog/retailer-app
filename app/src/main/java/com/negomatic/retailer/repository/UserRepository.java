package com.negomatic.retailer.repository;

import androidx.lifecycle.LiveData;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.negomatic.retailer.App;
import com.negomatic.retailer.api.UserApi;
import com.negomatic.retailer.db.dao.UserDao;
import com.negomatic.retailer.model.Account;
import com.negomatic.retailer.model.Authorization;
import com.negomatic.retailer.model.BaseResponse;
import com.negomatic.retailer.model.LoginBody;
import com.negomatic.retailer.entity.User;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.negomatic.retailer.prefs.SessionPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class UserRepository {

    private static int FRESH_TIMEOUT_IN_MINUTES = 1;

    private final UserApi webservice;
    private final UserDao userDao;
    private final Executor executor;

    @Inject
    public UserRepository(UserApi webservice, UserDao userDao, Executor executor) {
        this.webservice = webservice;
        this.userDao = userDao;
        this.executor = executor;
    }

    public LiveData<User> getUser(String userLogin) {
        refreshUser(userLogin); // try to refresh data if possible from Api
        return userDao.load(userLogin); // return a LiveData directly from the db.
    }

    private void refreshUser(final String userLogin) {
        executor.execute(() -> {
            // Check if user was fetched recently
            boolean userExists = (userDao.hasUser(userLogin, getMaxRefreshTime(new Date())) != null);
            // If user have to be updated
            if (!userExists) {
                webservice.getUser(userLogin).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.e("TAG", "DATA REFRESHED FROM NETWORK");
                        Toast.makeText(App.context, "Data refreshed from network !", Toast.LENGTH_LONG).show();
                        executor.execute(() -> {
                            User user = response.body();
                            user.setLastRefresh(new Date());
                            userDao.save(user);
                        });
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) { }
                });
            }
        });
    }

    public void attemptLoginAsync(String user, String password){
        executor.execute(()->{
            webservice.login(new LoginBody(user,password)).enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    Log.e("TAG", "exito en la peticion...");
                    executor.execute(()->{

                        BaseResponse baseResponse = response.body();
                        Authorization authorization = (Authorization) baseResponse.getData();
                        Account account = new Account();
                        account.setUserName(authorization.getUserName());
                        account.setPassword(password);
                        account.setToken(authorization.getToken());
                        SessionPrefs.get(App.context).saveAccount(account);
                        Log.e("TAG", "Session guardada...");
                    });
                }
                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) {
                    Log.e("TAG", "falla en la peticion...");
                }
            });
        });

    }
    public void attemptLoginSync(String user, String password) {
        try {
            Gson gson = new Gson();
            BaseResponse baseResponse =  webservice.login(new LoginBody(user,password)).execute().body();
            JsonObject data = gson.toJsonTree(baseResponse.getData()).getAsJsonObject();
            Authorization authorization = gson.fromJson(data,Authorization.class);
            Account account = new Account();
            account.setUserName(authorization.getUserName());
            account.setPassword(password);
            account.setToken(authorization.getToken());

            SessionPrefs.get(App.context).saveAccount(account);
            Log.e("TAG", "Session guardada...");
        }catch (IOException e){
            Log.e("TAG", "error de peticion...");
        }
    }
    private Date getMaxRefreshTime(Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }
}
