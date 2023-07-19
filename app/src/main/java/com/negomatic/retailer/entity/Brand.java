package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

@Entity
public class Brand {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @NonNull
    @Expose
    private int name;

    @SerializedName("created_at")
    @Nullable
    @Expose
    private Timestamp createdAt;

    @SerializedName("updated_at")
    @Nullable
    @Expose
    private Timestamp updatedAt;
}
