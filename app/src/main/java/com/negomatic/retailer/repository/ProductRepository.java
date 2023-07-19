package com.negomatic.retailer.repository;

import androidx.lifecycle.LiveData;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.negomatic.retailer.api.ProductApi;
import com.negomatic.retailer.db.dao.ProductDao;
import com.negomatic.retailer.entity.Product;
import com.negomatic.retailer.model.BaseResponse;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class ProductRepository {
    private final ProductApi webservice;
    private final ProductDao productDao;
    private final Executor executor;

    @Inject
    public ProductRepository(ProductApi webservice, ProductDao productDao, Executor executor){
        this.webservice = webservice;
        this.productDao = productDao;
        this.executor = executor;
    }
    public LiveData<List<Product>> getProducts(){
        //request to webservice and save in db local
        refreshProducts();
        return productDao.load();
    }
    private void refreshProducts() {
        executor.execute(() -> {
            webservice.getProducts().enqueue(new Callback<BaseResponse>() {
                @Override
                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    Log.e("TAG", "DATA REFRESHED FROM NETWORK");
                    executor.execute(() -> {
                        Gson gson = new Gson();
                        JsonArray data = gson.toJsonTree(response.body().getData()).getAsJsonArray();
                        List<Product> products = gson.fromJson(data,new TypeToken<List<Product>>() {}.getType());
                        if(products != null){
                            for(Product item : products){
                                productDao.save(item);
                            }
                        }
                    });
                }

                @Override
                public void onFailure(Call<BaseResponse> call, Throwable t) { }
                });

        });
    }
}
