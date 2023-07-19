package com.negomatic.retailer.model.order_note;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Warehouse {
    @Expose
    @SerializedName("stock")
    private String stock;

    @Expose
    @SerializedName("checked")
    private boolean checked;

    @Expose
    @SerializedName("warehouse_id")
    private int warehouseId;

    @Expose
    @SerializedName("warehouse_description")
    private String warehouseDescription;
}
