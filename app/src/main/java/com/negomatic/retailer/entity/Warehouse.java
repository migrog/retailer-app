package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Warehouse {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @Expose
    @SerializedName("id")
    private int id;

    @NonNull
    @Expose
    @SerializedName("establishment_id")
    private String establishmentId;

    @NonNull
    @Expose
    @SerializedName("description")
    private String description;

    @Nullable
    @Expose
    @SerializedName("created_at")
    private String createdAt;

    @Nullable
    @Expose
    @SerializedName("updated_at")
    private String updatedAt;
}
