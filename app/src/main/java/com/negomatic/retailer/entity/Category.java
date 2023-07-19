package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.util.converter.date.DateConverter;
import com.negomatic.retailer.util.converter.date.LocalDateTimeConverter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity(tableName = "categories")
public class Category {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @NonNull
    @Expose
    private String name;

    @SerializedName("created_at")
    @Nullable
    @Expose
    @TypeConverters(LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @SerializedName("updated_at")
    @Nullable
    @Expose
    @TypeConverters(LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;

    //GETS
    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Nullable
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    //SETS
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedAt(@Nullable LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(@Nullable LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
