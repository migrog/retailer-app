package com.negomatic.retailer.model.order_note;

import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.util.converter.ItemTypeConverter;
import com.negomatic.retailer.util.converter.ObjectListConverter;
import com.negomatic.retailer.util.converter.order_note.WarehouseConverter;

import java.util.List;

public class Item {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @TypeConverters(ObjectListConverter.class)
    @SerializedName("lots")
    private List<Object> lots;

    @Expose
    @SerializedName("is_set")
    private boolean isset;

    @Expose
    @SerializedName("has_igv")
    private boolean hasIgv;

    @Expose
    @SerializedName("unit_price")
    private double unitPrice;

    @Expose
    @TypeConverters(WarehouseConverter.class)
    @SerializedName("warehouses")
    private List<Warehouse> warehouses;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @TypeConverters(ObjectListConverter.class)
    @SerializedName("presentation")
    private List<Object> presentation;

    @Expose
    @SerializedName("series_enabled")
    private String seriesEnabled;

    @Expose
    @TypeConverters(ObjectListConverter.class)
    @SerializedName("item_unit_types")
    private List<Object> itemUnitTypes;

    @Expose
    @SerializedName("sale_unit_price")
    private String saleUnitPrice;

    @Expose
    @SerializedName("currency_type_id")
    private String currencyTypId;

    @Expose
    @SerializedName("full_description")
    private String fullDescription;

    @Expose
    @SerializedName("calculate_quantity")
    private boolean calculateQuantity;

    @Expose
    @SerializedName("purchase_unit_price")
    private String purchaseUnitPrice;

    @Expose
    @SerializedName("currency_type_symbol")
    private String currencyTypeSymbol;

    @Expose
    @SerializedName("sale_affectation_igv_type_id")
    private String saleAffectationIgvTypeId;

    @Expose
    @SerializedName("purchase_affectation_igv_type_id")
    private String purchaseAffectationIgvTypeId;

    //SETS

    public void setId(int id) {
        this.id = id;
    }

    public void setLots(List<Object> lots) {
        this.lots = lots;
    }

    public void setIsset(boolean isset) {
        this.isset = isset;
    }

    public void setHasIgv(boolean hasIgv) {
        this.hasIgv = hasIgv;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setWarehouses(List<Warehouse> warehouses) {
        this.warehouses = warehouses;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPresentation(List<Object> presentation) {
        this.presentation = presentation;
    }

    public void setSeriesEnabled(String seriesEnabled) {
        this.seriesEnabled = seriesEnabled;
    }

    public void setItemUnitTypes(List<Object> itemUnitTypes) {
        this.itemUnitTypes = itemUnitTypes;
    }

    public void setSaleUnitPrice(String saleUnitPrice) {
        this.saleUnitPrice = saleUnitPrice;
    }

    public void setCurrencyTypId(String currencyTypId) {
        this.currencyTypId = currencyTypId;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public void setCalculateQuantity(boolean calculateQuantity) {
        this.calculateQuantity = calculateQuantity;
    }

    public void setPurchaseUnitPrice(String purchaseUnitPrice) {
        this.purchaseUnitPrice = purchaseUnitPrice;
    }

    public void setCurrencyTypeSymbol(String currencyTypeSymbol) {
        this.currencyTypeSymbol = currencyTypeSymbol;
    }

    public void setSaleAffectationIgvTypeId(String saleAffectationIgvTypeId) {
        this.saleAffectationIgvTypeId = saleAffectationIgvTypeId;
    }

    public void setPurchaseAffectationIgvTypeId(String purchaseAffectationIgvTypeId) {
        this.purchaseAffectationIgvTypeId = purchaseAffectationIgvTypeId;
    }

    //GETS

    public int getId() {
        return id;
    }

    public List<Object> getLots() {
        return lots;
    }

    public boolean isIsset() {
        return isset;
    }

    public boolean isHasIgv() {
        return hasIgv;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public List<Warehouse> getWarehouses() {
        return warehouses;
    }

    public String getDescription() {
        return description;
    }

    public List<Object> getPresentation() {
        return presentation;
    }

    public String getSeriesEnabled() {
        return seriesEnabled;
    }

    public List<Object> getItemUnitTypes() {
        return itemUnitTypes;
    }

    public String getSaleUnitPrice() {
        return saleUnitPrice;
    }

    public String getCurrencyTypId() {
        return currencyTypId;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public boolean isCalculateQuantity() {
        return calculateQuantity;
    }

    public String getPurchaseUnitPrice() {
        return purchaseUnitPrice;
    }

    public String getCurrencyTypeSymbol() {
        return currencyTypeSymbol;
    }

    public String getSaleAffectationIgvTypeId() {
        return saleAffectationIgvTypeId;
    }

    public String getPurchaseAffectationIgvTypeId() {
        return purchaseAffectationIgvTypeId;
    }

    //CONSTRUCTOR

    public Item() {
    }
}
