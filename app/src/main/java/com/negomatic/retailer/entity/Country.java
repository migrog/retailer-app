package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "countries")
public class Country {
    @PrimaryKey
    @SerializedName("id")
    @NonNull
    @Expose
    private String id;

    @SerializedName("code")
    @NonNull
    @Expose
    private String code;

    @SerializedName("description")
    @NonNull
    @Expose
    private String  description;

    @SerializedName("currency_id")
    @NonNull
    @Expose
    private String currencyId;

    @SerializedName("active")
    @NonNull
    @Expose
    private int active;

    public Country(@NonNull String id, @NonNull String code, @NonNull String description, @NonNull String currencyId, int active) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.currencyId = currencyId;
        this.active = active;
    }

    //GETS

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getCurrencyId() {
        return currencyId;
    }

    public int getActive() {
        return active;
    }

    //SETS

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setCode(@NonNull String code) {
        this.code = code;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public void setCurrencyId(@NonNull String currencyId) {
        this.currencyId = currencyId;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
