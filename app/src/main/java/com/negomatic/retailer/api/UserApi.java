package com.negomatic.retailer.api;

import com.negomatic.retailer.model.BaseResponse;
import com.negomatic.retailer.model.LoginBody;
import com.negomatic.retailer.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
public interface UserApi {
    @GET("/users/{id}")
    Call<User> getUser(@Path("id") String userId);
    @POST("login")
    Call<BaseResponse> login(@Body LoginBody loginBody);
}
