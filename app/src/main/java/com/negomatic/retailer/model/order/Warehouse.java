package com.negomatic.retailer.model.order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Warehouse {
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
