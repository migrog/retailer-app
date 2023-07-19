package com.negomatic.retailer.model.order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrencyType {
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
}
