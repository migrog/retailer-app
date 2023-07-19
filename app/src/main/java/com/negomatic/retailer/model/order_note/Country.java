package com.negomatic.retailer.model.order_note;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {
    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("description")
    private String description;
}
