package com.negomatic.retailer.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.util.converter.date.DateConverter;

import java.util.Date;

@Entity
@TypeConverters(DateConverter.class)
public class User {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email_verified_at")
    @Expose
    private Date emailVerifiedAt;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("remember_token")
    @Expose
    private String rememberToken;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updatedAt;


    private Date lastRefresh;



    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public String getPassword() {
        return password;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Date getLastRefresh() {
        return lastRefresh;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailVerifiedAt(Date emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setLastRefresh(Date lastRefresh) {
        this.lastRefresh = lastRefresh;
    }
}
