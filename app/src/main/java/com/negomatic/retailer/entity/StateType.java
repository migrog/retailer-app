package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "state_types")
public class StateType {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("description")
    @NonNull
    @Expose
    private String  description;

    public StateType(String id, String description) {
        this.id = id;
        this.description = description;
    }
    @Ignore
    public StateType(){};

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
}
