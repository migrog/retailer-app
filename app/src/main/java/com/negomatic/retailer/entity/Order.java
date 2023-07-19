package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.model.order.Customer;
import com.negomatic.retailer.model.order.Item;
import com.negomatic.retailer.model.order.OrderPurchaseField;
import com.negomatic.retailer.util.converter.order.CustomerConverter;
import com.negomatic.retailer.util.converter.date.DateConverter;
import com.negomatic.retailer.util.converter.ItemConverter;
import com.negomatic.retailer.util.converter.order.OrderPurchaseFieldConverter;

import java.util.Date;
import java.util.List;

@Entity(tableName = "orders")
public class Order {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @Expose
    @SerializedName("id")
    private int id;

    @NonNull
    @Expose
    @SerializedName("external_id")
    private String externalId;

    @Nullable
    @Expose
    @TypeConverters(CustomerConverter.class)
    @SerializedName("customer")
    private Customer customer;

    @Nullable
    @Expose
    @SerializedName("shipping_address")
    private String shippingAddress;

    @Nullable
    @Expose
    @TypeConverters(ItemConverter.class)
    @SerializedName("items")
    private List<Item> items;

    @NonNull
    @Expose
    @SerializedName("total")
    private double total;

    @NonNull
    @Expose
    @SerializedName("reference_payment")
    private String referencePayment;

    @Nullable
    @Expose
    @SerializedName("document_external_id")
    private String documentExternalId;

    @Nullable
    @Expose
    @SerializedName("number_document")
    private String numberDocument;

    @Nullable
    @Expose
    @SerializedName("status_order_id")
    private int statusOrderId;

    @Nullable
    @Expose
    @TypeConverters(OrderPurchaseFieldConverter.class)
    @SerializedName("purchase")
    private OrderPurchaseField purchase;

    @Nullable
    @Expose
    @TypeConverters(DateConverter.class)
    @SerializedName("created_at")
    private Date createdAt;

    @Nullable
    @Expose
    @TypeConverters(DateConverter.class)
    @SerializedName("updated_at")
    private Date updatedAt;

    @Nullable
    @Expose
    @TypeConverters(DateConverter.class)
    @SerializedName("deleted_at")
    private Date deletedAt;

    //SETS

    public void setId(int id) {
        this.id = id;
    }

    public void setExternalId(@NonNull String externalId) {
        this.externalId = externalId;
    }

    public void setCustomer(@Nullable Customer customer) {
        this.customer = customer;
    }

    public void setShippingAddress(@Nullable String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setItems(@Nullable List<Item> items) {
        this.items = items;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setReferencePayment(@NonNull String referencePayment) {
        this.referencePayment = referencePayment;
    }

    public void setDocumentExternalId(@Nullable String documentExternalId) {
        this.documentExternalId = documentExternalId;
    }

    public void setNumberDocument(@Nullable String numberDocument) {
        this.numberDocument = numberDocument;
    }

    public void setStatusOrderId(int statusOrderId) {
        this.statusOrderId = statusOrderId;
    }

    public void setPurchase(@Nullable OrderPurchaseField purchase) {
        this.purchase = purchase;
    }

    public void setCreatedAt(@Nullable Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(@Nullable Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(@Nullable Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    //GETS

    public int getId() {
        return id;
    }

    @NonNull
    public String getExternalId() {
        return externalId;
    }

    @Nullable
    public Customer getCustomer() {
        return customer;
    }

    @Nullable
    public String getShippingAddress() {
        return shippingAddress;
    }

    @Nullable
    public List<Item> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    @NonNull
    public String getReferencePayment() {
        return referencePayment;
    }

    @Nullable
    public String getDocumentExternalId() {
        return documentExternalId;
    }

    @Nullable
    public String getNumberDocument() {
        return numberDocument;
    }

    public int getStatusOrderId() {
        return statusOrderId;
    }

    @Nullable
    public OrderPurchaseField getPurchase() {
        return purchase;
    }

    @Nullable
    public Date getCreatedAt() {
        return createdAt;
    }

    @Nullable
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @Nullable
    public Date getDeletedAt() {
        return deletedAt;
    }
}
