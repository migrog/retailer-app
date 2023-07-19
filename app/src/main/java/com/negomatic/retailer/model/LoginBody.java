package com.negomatic.retailer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Objeto plano Java para representar el cuerpo de la petición POST /user/login
 */
public class LoginBody {
    @SerializedName("user")
    private String user;

    @SerializedName("password")
    private String password;

    public LoginBody(String user,String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }
    public String getPassword() {
        return password;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
