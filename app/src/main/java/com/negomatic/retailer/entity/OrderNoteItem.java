package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.model.order_note.Item;
import com.negomatic.retailer.util.converter.ObjectListConverter;
import com.negomatic.retailer.util.converter.order_note.ItemConverter;

import java.util.List;

@Entity(tableName = "order_note_items")
public class OrderNoteItem {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @Expose
    @SerializedName("id")
    private int id;

    @NonNull
    @Expose
    @SerializedName("order_note_id")
    private int orderNoteId;

    @NonNull
    @Expose
    @SerializedName("item_id")
    private int item_id;

    @NonNull
    @Expose
    @SerializedName("item")
    @TypeConverters(ItemConverter.class)
    private Item item;

    @NonNull
    @Expose
    @SerializedName("quantity")
    private double quantity;

    @NonNull
    @Expose
    @SerializedName("unit_value")
    private double unitValue;

    @NonNull
    @Expose
    @SerializedName("affectation_igv_type_id")
    private String affectationIgvTypeId;

    @NonNull
    @Expose
    @SerializedName("total_base_igv")
    private double totalBaseIgv;

    @NonNull
    @Expose
    @SerializedName("percentage_igv")
    private double percentageIgv;

    @NonNull
    @Expose
    @SerializedName("total_igv")
    private double totalIgv;

    @Nullable
    @Expose
    @SerializedName("system_isc_type_id")
    private String systemIscTypeId;

    @NonNull
    @Expose
    @SerializedName("total_base_isc")
    private double totalBaseIsc;

    @NonNull
    @Expose
    @SerializedName("percentage_isc")
    private double percentageIsc;

    @NonNull
    @Expose
    @SerializedName("total_isc")
    private double totalIsc;

    @NonNull
    @Expose
    @SerializedName("total_base_other_taxes")
    private double totalBaseOtherTaxes;

    @NonNull
    @Expose
    @SerializedName("percentage_other_taxes")
    private double percentageOtherTaxes;

    @NonNull
    @Expose
    @SerializedName("total_other_taxes")
    private double totalOtherTaxes;

    @NonNull
    @Expose
    @SerializedName("total_taxes")
    private double totalTaxes;

    @NonNull
    @Expose
    @SerializedName("price_type_id")
    private String priceTypeId;

    @NonNull
    @Expose
    @SerializedName("unit_price")
    private double unitPrice;

    @NonNull
    @Expose
    @SerializedName("total_value")
    private double totalValue;

    @NonNull
    @Expose
    @SerializedName("total_charge")
    private double totalCharge;

    @NonNull
    @Expose
    @SerializedName("total_discount")
    private double totalDiscount;

    @NonNull
    @Expose
    @SerializedName("total")
    private double total;

    @Nullable
    @Expose
    @TypeConverters(ObjectListConverter.class)
    @SerializedName("attributes")
    private List<Object> attributes;

    @Nullable
    @Expose
    @TypeConverters(ObjectListConverter.class)
    @SerializedName("discounts")
    private List<Object> discounts;

    @Nullable
    @Expose
    @TypeConverters(ObjectListConverter.class)
    @SerializedName("charges")
    private List<Object> charges;

    @Nullable
    @Expose
    @SerializedName("warehouse_id")
    private int warehouseId;

    //SETS

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderNoteId(int orderNoteId) {
        this.orderNoteId = orderNoteId;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public void setItem(@NonNull Item item) {
        this.item = item;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

    public void setAffectationIgvTypeId(@NonNull String affectationIgvTypeId) {
        this.affectationIgvTypeId = affectationIgvTypeId;
    }

    public void setTotalBaseIgv(double totalBaseIgv) {
        this.totalBaseIgv = totalBaseIgv;
    }

    public void setPercentageIgv(double percentageIgv) {
        this.percentageIgv = percentageIgv;
    }

    public void setTotalIgv(double totalIgv) {
        this.totalIgv = totalIgv;
    }

    public void setSystemIscTypeId(@Nullable String systemIscTypeId) {
        this.systemIscTypeId = systemIscTypeId;
    }

    public void setTotalBaseIsc(double totalBaseIsc) {
        this.totalBaseIsc = totalBaseIsc;
    }

    public void setPercentageIsc(double percentageIsc) {
        this.percentageIsc = percentageIsc;
    }

    public void setTotalIsc(double totalIsc) {
        this.totalIsc = totalIsc;
    }

    public void setTotalBaseOtherTaxes(double totalBaseOtherTaxes) {
        this.totalBaseOtherTaxes = totalBaseOtherTaxes;
    }

    public void setPercentageOtherTaxes(double percentageOtherTaxes) {
        this.percentageOtherTaxes = percentageOtherTaxes;
    }

    public void setTotalOtherTaxes(double totalOtherTaxes) {
        this.totalOtherTaxes = totalOtherTaxes;
    }

    public void setTotalTaxes(double totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public void setPriceTypeId(String priceTypeId) {
        this.priceTypeId = priceTypeId;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setAttributes(@Nullable List<Object> attributes) {
        this.attributes = attributes;
    }

    public void setDiscounts(@Nullable List<Object> discounts) {
        this.discounts = discounts;
    }

    public void setCharges(@Nullable List<Object> charges) {
        this.charges = charges;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    //GETS

    public int getId() {
        return id;
    }

    public int getOrderNoteId() {
        return orderNoteId;
    }

    public int getItem_id() {
        return item_id;
    }

    @NonNull
    public Item getItem() {
        return item;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getUnitValue() {
        return unitValue;
    }

    @NonNull
    public String getAffectationIgvTypeId() {
        return affectationIgvTypeId;
    }

    public double getTotalBaseIgv() {
        return totalBaseIgv;
    }

    public double getPercentageIgv() {
        return percentageIgv;
    }

    public double getTotalIgv() {
        return totalIgv;
    }

    @Nullable
    public String getSystemIscTypeId() {
        return systemIscTypeId;
    }

    public double getTotalBaseIsc() {
        return totalBaseIsc;
    }

    public double getPercentageIsc() {
        return percentageIsc;
    }

    public double getTotalIsc() {
        return totalIsc;
    }

    public double getTotalBaseOtherTaxes() {
        return totalBaseOtherTaxes;
    }

    public double getPercentageOtherTaxes() {
        return percentageOtherTaxes;
    }

    public double getTotalOtherTaxes() {
        return totalOtherTaxes;
    }

    public double getTotalTaxes() {
        return totalTaxes;
    }

    public String getPriceTypeId() {
        return priceTypeId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public double getTotal() {
        return total;
    }

    @Nullable
    public List<Object> getAttributes() {
        return attributes;
    }

    @Nullable
    public List<Object> getDiscounts() {
        return discounts;
    }

    @Nullable
    public List<Object> getCharges() {
        return charges;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    //CONSTRUCTOR

    public OrderNoteItem() {
    }
}
