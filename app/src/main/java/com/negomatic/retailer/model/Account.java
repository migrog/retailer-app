package com.negomatic.retailer.model;

/**
 * Objeto plano Java para representar afiliados
 */
public class Account {

    private String userId;
    private String userName;
    private String password;
    private String token;


    public Account(String userId,String userName, String password, String token) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.token = token;
    }

    public Account() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
