package com.negomatic.retailer.model.order;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemType {
    @NonNull
    @Expose
    @SerializedName("id")
    private String id;

    @NonNull
    @Expose
    @SerializedName("description")
    private String description;

    //SETS

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    //GETS

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    //CONSTRUCTOR

    public ItemType() {
    }
}
