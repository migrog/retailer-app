package com.negomatic.retailer.model.order;

import androidx.annotation.Nullable;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.util.converter.order.WarehouseConverter;

public class WarehouseRoot {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("stock")
    private String stock;

    @Expose
    @SerializedName("item_id")
    private int itemId;

    @Expose
    @SerializedName("warehouse")
    @TypeConverters(WarehouseConverter.class)
    private Warehouse warehouse;

    @Nullable
    @Expose
    @SerializedName("created_at")
    private String createdAt;

    @Nullable
    @Expose
    @SerializedName("updated_at")
    private String updatedAt;

    @Expose
    @SerializedName("warehouse_id")
    private int warehouseId;
}