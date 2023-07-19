package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "cat_currency_types")
public class CurrencyType {
    @PrimaryKey
    @SerializedName("id")
    @NonNull
    @Expose
    private String id;

    @SerializedName("code")
    @NonNull
    @Expose
    private String code;

    @SerializedName("symbol")
    @Nullable
    @Expose
    private String  symbol;

    @SerializedName("description")
    @NonNull
    @Expose
    private String  description;

    @SerializedName("active")
    @NonNull
    @Expose
    private int active;

    public CurrencyType(@NonNull String id, @NonNull String code, @Nullable String symbol, @NonNull String description, int active) {
        this.id = id;
        this.code = code;
        this.symbol = symbol;
        this.description = description;
        this.active = active;
    }

    //GETS
    public String getId() {
        return id;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @Nullable
    public String getSymbol() {
        return symbol;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public int getActive() {
        return active;
    }

    //SETS
    public void setId(String id) {
        this.id = id;
    }

    public void setCode(@NonNull String code) {
        this.code = code;
    }

    public void setSymbol(@Nullable String symbol) {
        this.symbol = symbol;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public void setActive(int active) {
        this.active = active;
    }

}
