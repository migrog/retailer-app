package com.negomatic.retailer.api;

import com.negomatic.retailer.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {
    @GET("products")
    Call<BaseResponse> getProducts();
}
