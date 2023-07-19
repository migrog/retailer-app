package com.negomatic.retailer.model.order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnitType {
    @NonNull
    @Expose
    @SerializedName("id")
    private String id;

    @NonNull
    @Expose
    @SerializedName("active")
    private int active;

    @Nullable
    @Expose
    @SerializedName("symbol")
    private String  symbol;

    @NonNull
    @Expose
    @SerializedName("description")
    private String  description;

    //SETS

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setSymbol(@Nullable String symbol) {
        this.symbol = symbol;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    //GETS

    @NonNull
    public String getId() {
        return id;
    }

    public int getActive() {
        return active;
    }

    @Nullable
    public String getSymbol() {
        return symbol;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    //CONSTRUCTOR

    public UnitType() {
    }
}
