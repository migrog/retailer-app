package com.negomatic.retailer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("success")
    @Expose
    private boolean success;

    @SerializedName("msg")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
