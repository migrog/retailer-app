package com.negomatic.retailer.model.order;

import androidx.annotation.NonNull;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.model.order.UnitType;
import com.negomatic.retailer.util.converter.UnitTypeConverter;

public class ItemUnitType {
    @NonNull
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("price1")
    private String price1;

    @Expose
    @SerializedName("price2")
    private String price2;

    @Expose
    @SerializedName("price3")
    private String price3;

    @Expose
    @SerializedName("item_id")
    private int itemId;

    @Expose
    @SerializedName("unit_type")
    @TypeConverters(UnitTypeConverter.class)
    private UnitType unitType;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("unit_type_id")
    private String unitTypeId;

    @Expose
    @SerializedName("price_default")
    private int priceDefault;

    @Expose
    @SerializedName("quantity_unit")
    private String quantity_unit;

}
