package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "cat_unit_types")
public class UnitType {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("active")
    @NonNull
    @Expose
    private int active;

    @SerializedName("symbol")
    @Nullable
    @Expose
    private String  symbol;

    @SerializedName("description")
    @NonNull
    @Expose
    private String  description;

    //GETS
    @NonNull
    public int getId() {
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

    //SETS
    public void setId(@NonNull int id) {
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
}
