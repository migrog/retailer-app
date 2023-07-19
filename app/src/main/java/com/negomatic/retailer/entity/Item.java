package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.model.Attribute;
import com.negomatic.retailer.util.converter.AttributeConverter;
import com.negomatic.retailer.util.converter.date.DateConverter;

import java.util.Date;
import java.util.List;

@Entity(tableName = "items")
public class Item {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    @Nullable
    private String name;

    @SerializedName("second_name")
    @Nullable
    @Expose
    private String secondName;

    @SerializedName("description")
    @Nullable
    @Expose
    private String description;

    @SerializedName("model")
    @Nullable
    @Expose
    private String model;

    @SerializedName("barcode")
    @Nullable
    @Expose
    private String barcode;

    @SerializedName("technical_specifications")
    @Nullable
    @Expose
    private String technicalSpecifications;

    @SerializedName("item_type_id")
    @NonNull
    @Expose
    private String itemTypeId;

    @SerializedName("internal_id")
    @Nullable
    @Expose
    private String internalId;

    @SerializedName("item_code")
    @Nullable
    @Expose
    private String itemCode;

    @SerializedName("date_of_due")
    @Nullable
    @Expose
    @TypeConverters(DateConverter.class)
    private Date dateOfDue;

    @SerializedName("account_id")
    @Nullable
    @Expose
    private int accountId;

    @SerializedName("item_code_gs1")
    @Nullable
    @Expose
    private String itemCodeGs1;

    @SerializedName("unit_type_id")
    @NonNull
    @Expose
    private String unitTypeId;

    @SerializedName("currency_type_id")
    @NonNull
    @Expose
    private String currencyTypeId;

    @SerializedName("sale_unit_price")
    @NonNull
    @Expose
    private double saleUnitPrice;

    @SerializedName("purchase_has_igv")
    @NonNull
    @Expose
    private int purchaseHasIgv;

    @SerializedName("has_igv")
    @NonNull
    @Expose
    private int hasIgv;

    @SerializedName("purchase_unit_price")
    @NonNull
    @Expose
    private double purchaseUnitPrice;

    @SerializedName("has_isc")
    @NonNull
    @Expose
    private int hasIsc;

    @SerializedName("commission_amount")
    @Nullable
    @Expose
    private double commissionAmount;

    @SerializedName("line")
    @Nullable
    @Expose
    private String line;

    @SerializedName("commission_type")
    @Nullable
    @Expose
    private String commissionType;

    @SerializedName("amount_plastic_bag_taxes")
    @NonNull
    @Expose
    private double amountPlasticBagTaxes;

    @SerializedName("system_isc_type_id")
    @Nullable
    @Expose
    private String systemIscTypeId;

    @SerializedName("percentage_isc")
    @NonNull
    @Expose
    private double percentageIsc;

    @SerializedName("suggested_price")
    @NonNull
    @Expose
    private double suggestedPrice;

    @SerializedName("sale_affectation_igv_type_id")
    @NonNull
    @Expose
    private String saleAffectationIgvTypeId;

    @SerializedName("purchase_affectation_igv_type_id")
    @NonNull
    @Expose
    private String purchaseAffectationIgvTypeId;

    @SerializedName("calculate_quantity")
    @NonNull
    @Expose
    private int calculateQuantity;

    @SerializedName("sale_unit_price_set")
    @Nullable
    @Expose
    private double saleUnitPriceSet;

    @SerializedName("is_set")
    @NonNull
    @Expose
    private int isSet;

    @SerializedName("category_id")
    @Nullable
    @Expose
    private int categoryId;

    @SerializedName("brand_id")
    @Nullable
    @Expose
    private int brandId;

    @SerializedName("image")
    @NonNull
    @Expose
    private String image;

    @SerializedName("image_medium")
    @NonNull
    @Expose
    private String imageMedium;

    @SerializedName("image_small")
    @NonNull
    @Expose
    private String imageSmall;

    @SerializedName("stock")
    @NonNull
    @Expose
    private double stock;

    @SerializedName("stock_min")
    @NonNull
    @Expose
    private double stockMin;

    @SerializedName("has_plastic_bag_taxes")
    @NonNull
    @Expose
    private int hasPlasticBagTaxes;

    @SerializedName("lot_code")
    @Nullable
    @Expose
    private String lot_code;

    @SerializedName("lots_enabled")
    @NonNull
    @Expose
    private int lotsEnabled;

    @SerializedName("series_enabled")
    @NonNull
    @Expose
    private int seriesEnabled;

    @SerializedName("percentage_of_profit")
    @NonNull
    @Expose
    private double percentageOfProfit;

    @SerializedName("has_perception")
    @NonNull
    @Expose
    private int hasPerception;

    @SerializedName("percentage_perception")
    @Nullable
    @Expose
    private double percentage_perception;

    @SerializedName("attributes")
    @Nullable
    @Expose
    @TypeConverters(AttributeConverter.class)
    private List<Attribute> attributes;

    @SerializedName("active")
    @NonNull
    @Expose
    private int active;

    @SerializedName("web_platform_id")
    @Expose
    @Nullable
    private int webPlatformId;

    @SerializedName("created_at")
    @Nullable
    @Expose
    @TypeConverters(DateConverter.class)
    private Date createdAt;

    @SerializedName("updated_at")
    @Nullable
    @Expose
    @TypeConverters(DateConverter.class)
    private Date updatedAt;

    @SerializedName("warehouse_id")
    @Expose
    @Nullable
    private int warehouse_id;

    @SerializedName("status")
    @Expose
    @NonNull
    private int status;

    @SerializedName("apply_store")
    @Expose
    @NonNull
    private int apply_store;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(@Nullable String secondName) {
        this.secondName = secondName;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    @Nullable
    public String getModel() {
        return model;
    }

    public void setModel(@Nullable String model) {
        this.model = model;
    }

    @Nullable
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(@Nullable String barcode) {
        this.barcode = barcode;
    }

    @Nullable
    public String getTechnicalSpecifications() {
        return technicalSpecifications;
    }

    public void setTechnicalSpecifications(@Nullable String technicalSpecifications) {
        this.technicalSpecifications = technicalSpecifications;
    }

    @NonNull
    public String getItemTypeId() {
        return itemTypeId;
    }

    public void setItemTypeId(@NonNull String itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    @Nullable
    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(@Nullable String internalId) {
        this.internalId = internalId;
    }

    @Nullable
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(@Nullable String itemCode) {
        this.itemCode = itemCode;
    }

    @Nullable
    public Date getDateOfDue() {
        return dateOfDue;
    }

    public void setDateOfDue(@Nullable Date dateOfDue) {
        this.dateOfDue = dateOfDue;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Nullable
    public String getItemCodeGs1() {
        return itemCodeGs1;
    }

    public void setItemCodeGs1(@Nullable String itemCodeGs1) {
        this.itemCodeGs1 = itemCodeGs1;
    }

    @NonNull
    public String getUnitTypeId() {
        return unitTypeId;
    }

    public void setUnitTypeId(@NonNull String unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    @NonNull
    public String getCurrencyTypeId() {
        return currencyTypeId;
    }

    public void setCurrencyTypeId(@NonNull String currencyTypeId) {
        this.currencyTypeId = currencyTypeId;
    }

    public double getSaleUnitPrice() {
        return saleUnitPrice;
    }

    public void setSaleUnitPrice(double saleUnitPrice) {
        this.saleUnitPrice = saleUnitPrice;
    }

    public int getPurchaseHasIgv() {
        return purchaseHasIgv;
    }

    public void setPurchaseHasIgv(int purchaseHasIgv) {
        this.purchaseHasIgv = purchaseHasIgv;
    }

    public int getHasIgv() {
        return hasIgv;
    }

    public void setHasIgv(int hasIgv) {
        this.hasIgv = hasIgv;
    }

    public double getPurchaseUnitPrice() {
        return purchaseUnitPrice;
    }

    public void setPurchaseUnitPrice(double purchaseUnitPrice) {
        this.purchaseUnitPrice = purchaseUnitPrice;
    }

    public int getHasIsc() {
        return hasIsc;
    }

    public void setHasIsc(int hasIsc) {
        this.hasIsc = hasIsc;
    }

    public double getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    @Nullable
    public String getLine() {
        return line;
    }

    public void setLine(@Nullable String line) {
        this.line = line;
    }

    @Nullable
    public String getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(@Nullable String commissionType) {
        this.commissionType = commissionType;
    }

    public double getAmountPlasticBagTaxes() {
        return amountPlasticBagTaxes;
    }

    public void setAmountPlasticBagTaxes(double amountPlasticBagTaxes) {
        this.amountPlasticBagTaxes = amountPlasticBagTaxes;
    }

    @Nullable
    public String getSystemIscTypeId() {
        return systemIscTypeId;
    }

    public void setSystemIscTypeId(@Nullable String systemIscTypeId) {
        this.systemIscTypeId = systemIscTypeId;
    }

    public double getPercentageIsc() {
        return percentageIsc;
    }

    public void setPercentageIsc(double percentageIsc) {
        this.percentageIsc = percentageIsc;
    }

    public double getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(double suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    @NonNull
    public String getSaleAffectationIgvTypeId() {
        return saleAffectationIgvTypeId;
    }

    public void setSaleAffectationIgvTypeId(@NonNull String saleAffectationIgvTypeId) {
        this.saleAffectationIgvTypeId = saleAffectationIgvTypeId;
    }

    @NonNull
    public String getPurchaseAffectationIgvTypeId() {
        return purchaseAffectationIgvTypeId;
    }

    public void setPurchaseAffectationIgvTypeId(@NonNull String purchaseAffectationIgvTypeId) {
        this.purchaseAffectationIgvTypeId = purchaseAffectationIgvTypeId;
    }

    public int getCalculateQuantity() {
        return calculateQuantity;
    }

    public void setCalculateQuantity(int calculateQuantity) {
        this.calculateQuantity = calculateQuantity;
    }

    public double getSaleUnitPriceSet() {
        return saleUnitPriceSet;
    }

    public void setSaleUnitPriceSet(double saleUnitPriceSet) {
        this.saleUnitPriceSet = saleUnitPriceSet;
    }

    public int getIsSet() {
        return isSet;
    }

    public void setIsSet(int isSet) {
        this.isSet = isSet;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    @NonNull
    public String getImage() {
        return image;
    }

    public void setImage(@NonNull String image) {
        this.image = image;
    }

    @NonNull
    public String getImageMedium() {
        return imageMedium;
    }

    public void setImageMedium(@NonNull String imageMedium) {
        this.imageMedium = imageMedium;
    }

    @NonNull
    public String getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(@NonNull String imageSmall) {
        this.imageSmall = imageSmall;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public double getStockMin() {
        return stockMin;
    }

    public void setStockMin(double stockMin) {
        this.stockMin = stockMin;
    }

    public int getHasPlasticBagTaxes() {
        return hasPlasticBagTaxes;
    }

    public void setHasPlasticBagTaxes(int hasPlasticBagTaxes) {
        this.hasPlasticBagTaxes = hasPlasticBagTaxes;
    }

    @Nullable
    public String getLot_code() {
        return lot_code;
    }

    public void setLot_code(@Nullable String lot_code) {
        this.lot_code = lot_code;
    }

    public int getLotsEnabled() {
        return lotsEnabled;
    }

    public void setLotsEnabled(int lotsEnabled) {
        this.lotsEnabled = lotsEnabled;
    }

    public int getSeriesEnabled() {
        return seriesEnabled;
    }

    public void setSeriesEnabled(int seriesEnabled) {
        this.seriesEnabled = seriesEnabled;
    }

    public double getPercentageOfProfit() {
        return percentageOfProfit;
    }

    public void setPercentageOfProfit(double percentageOfProfit) {
        this.percentageOfProfit = percentageOfProfit;
    }

    public int getHasPerception() {
        return hasPerception;
    }

    public void setHasPerception(int hasPerception) {
        this.hasPerception = hasPerception;
    }

    public double getPercentage_perception() {
        return percentage_perception;
    }

    public void setPercentage_perception(double percentage_perception) {
        this.percentage_perception = percentage_perception;
    }

    @Nullable
    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(@Nullable List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getWebPlatformId() {
        return webPlatformId;
    }

    public void setWebPlatformId(int webPlatformId) {
        this.webPlatformId = webPlatformId;
    }

    @Nullable
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@Nullable Date createdAt) {
        this.createdAt = createdAt;
    }

    @Nullable
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(@Nullable Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getApply_store() {
        return apply_store;
    }

    public void setApply_store(int apply_store) {
        this.apply_store = apply_store;
    }

    public Item() {
        this.createdAt = new Date();
    }
}
