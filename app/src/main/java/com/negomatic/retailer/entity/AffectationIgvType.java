package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AffectationIgvType {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("active")
    @NonNull
    @Expose
    private int active;

    @SerializedName("exportation")
    @Nullable
    @Expose
    private int exportation;

    @SerializedName("free")
    @Nullable
    @Expose
    private int free;

    @SerializedName("description")
    @NonNull
    @Expose
    private int description;

}
