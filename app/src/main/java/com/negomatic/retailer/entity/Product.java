package com.negomatic.retailer.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.util.converter.date.DateConverter;

@Entity
@TypeConverters(DateConverter.class)
public class Product {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("unit_price")
    @Expose
    private String unitPrice;

    @SerializedName("current_stock")
    @Expose
    private String currentStock;

    @SerializedName("um")
    @Expose
    private String um;

    public Product(String name, String currency, String unitPrice, String currentStock, String um) {
        this.name = name;
        this.currency = currency;
        this.unitPrice = unitPrice;
        this.currentStock = currentStock;
        this.um = um;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public String getCurrentStock() {
        return currentStock;
    }

    public String getUm() {
        return um;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setCurrentStock(String currentStock) {
        this.currentStock = currentStock;
    }

    public void setUm(String um) {
        this.um = um;
    }
}
