package com.negomatic.retailer.model.order_note;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Province {
    @Nullable
    @Expose
    @SerializedName("id")
    private String id;

    @Nullable
    @Expose
    @SerializedName("description")
    private String description;
}
