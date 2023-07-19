package com.negomatic.retailer.api;

import com.negomatic.retailer.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("login")
    @FormUrlEncoded
    Call<BaseResponse> loginAccount(@Field("user") String email, @Field("password") String password);
}
