package com.negomatic.retailer.model.order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.model.Attribute;
import com.negomatic.retailer.util.converter.AttributeConverter;
import com.negomatic.retailer.util.converter.CurrencyTypeConverter;
import com.negomatic.retailer.util.converter.date.DateConverter;
import com.negomatic.retailer.util.converter.ItemTypeConverter;
import com.negomatic.retailer.util.converter.ItemUnitTypeConverter;
import com.negomatic.retailer.util.converter.UnitTypeConverter;
import com.negomatic.retailer.util.converter.order.WarehouseRootConverter;

import java.util.Date;
import java.util.List;

public class Item {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @Nullable
    @SerializedName("line")
    private String line;

    @Expose
    @Nullable
    @SerializedName("name")
    private String name;
    // "tags": []

    @Expose
    @SerializedName("image")
    private String image;

    @Expose
    @Nullable
    @SerializedName("model")
    private String model;

    @Expose
    @SerializedName("stock")
    private double stock;

    @Expose
    @SerializedName("active")
    private int active;

    //"images": []
    @Expose
    @SerializedName("is_set")
    private int isSet;

    @Expose
    @SerializedName("status")
    private int status;

    @Expose
    @SerializedName("barcode")
    private String barcode;

    @Expose
    @SerializedName("has_igv")
    private int hasIgv;

    @Expose
    @SerializedName("has_isc")
    private int hasIsc;

    @Expose
    @Nullable
    @SerializedName("brand_id")
    private int brandId;

    @Expose
    @SerializedName("cantidad")
    private int cantidad;

    @Expose
    @Nullable
    @SerializedName("lot_code")
    private String lotCode;

    @Expose
    @Nullable
    @SerializedName("item_code")
    private String itemCode;

    @Nullable
    @Expose
    @TypeConverters(ItemTypeConverter.class)
    @SerializedName("item_type")
    private ItemType itemType;

    @Expose
    @SerializedName("stock_min")
    private double stockMin;

    @Expose
    @SerializedName("sub_total")
    private double subTotal;

    @Nullable
    @Expose
    @TypeConverters(UnitTypeConverter.class)
    @SerializedName("unit_type")
    private UnitType unitType;

    @Nullable
    @Expose
    @SerializedName("account_id")
    private int accountId;

    @Nullable
    @Expose
    @TypeConverters(AttributeConverter.class)
    @SerializedName("attributes")
    private List<Attribute> attributes;

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
    @TypeConverters(WarehouseRootConverter.class)
    @SerializedName("warehouses")
    private List<WarehouseRoot> warehouses;

    @Expose
    @SerializedName("apply_store")
    private int applyStore;

    @Nullable
    @Expose
    @SerializedName("category_id")
    private int categoryId;

    @Nullable
    @Expose
    @TypeConverters(DateConverter.class)
    @SerializedName("date_of_due")
    private Date dateOfDue;

    @Nullable
    @Expose
    @SerializedName("description")
    private String description;

    @NonNull
    @Expose
    @SerializedName("image_small")
    private String imageSmall;

    @Nullable
    @Expose
    @SerializedName("internal_id")
    private String internalId;

    @Nullable
    @Expose
    @SerializedName("second_name")
    private String secondName;

    @NonNull
    @Expose
    @SerializedName("image_medium")
    private String imageMedium;

    @NonNull
    @Expose
    @SerializedName("item_type_id")
    private String itemTypeId;

    @NonNull
    @Expose
    @SerializedName("unit_type_id")
    private String unitTypeId;

    @Expose
    @Nullable
    @SerializedName("warehouse_id")
    private int warehouse_id;

    @Nullable
    @Expose
    @TypeConverters(CurrencyTypeConverter.class)
    @SerializedName("currency_type")
    private CurrencyType currencyType;

    @Nullable
    @Expose
    @SerializedName("item_code_gs1")
    private String itemCodeGs1;

    @NonNull
    @Expose
    @SerializedName("has_perception")
    private int hasPerception;

    @NonNull
    @Expose
    @SerializedName("percentage_isc")
    private double percentageIsc;

    @NonNull
    @Expose
    @SerializedName("series_enabled")
    private int seriesEnabled;

    @Nullable
    @Expose
    @SerializedName("commission_type")
    private String commissionType;

    @Nullable
    @Expose
    @TypeConverters(ItemUnitTypeConverter.class)
    @SerializedName("item_unit_types")
    private List<ItemUnitType> itemUnitTypes;

    @NonNull
    @Expose
    @SerializedName("sale_unit_price")
    private double saleUnitPrice;

    @NonNull
    @Expose
    @SerializedName("suggested_price")
    private double suggestedPrice;

    @Expose
    @Nullable
    @SerializedName("web_platform_id")
    private int webPlatformId;

    @NonNull
    @Expose
    @SerializedName("currency_type_id")
    private String currencyTypeId;

    @NonNull
    @Expose
    @SerializedName("purchase_has_igv")
    private int purchaseHasIgv;

    @Nullable
    @Expose
    @SerializedName("commission_amount")
    private double commissionAmount;

    @NonNull
    @Expose
    @SerializedName("calculate_quantity")
    private int calculateQuantity;

    @Nullable
    @Expose
    @SerializedName("exchange_rate_sale")
    private double exchangeRateSale;

    @Nullable
    @Expose
    @SerializedName("system_isc_type_id")
    private String systemIscTypeId;

    @NonNull
    @Expose
    @SerializedName("purchase_unit_price")
    private double purchaseUnitPrice;

    @Nullable
    @Expose
    @SerializedName("sale_unit_price_set")
    private double saleUnitPriceSet;

    @NonNull
    @Expose
    @SerializedName("percentage_of_profit")
    private double percentageOfProfit;

    @NonNull
    @Expose
    @SerializedName("has_plastic_bag_taxes")
    private int hasPlasticBagTaxes;

    @Nullable
    @Expose
    @SerializedName("percentage_perception")
    private double percentage_perception;

    @NonNull
    @Expose
    @SerializedName("amount_plastic_bag_taxes")
    private double amountPlasticBagTaxes;

    @Nullable
    @Expose
    @SerializedName("technical_specifications")
    private String technicalSpecifications;

    @NonNull
    @Expose
    @SerializedName("sale_affectation_igv_type_id")
    private String saleAffectationIgvTypeId;

    @NonNull
    @Expose
    @SerializedName("purchase_affectation_igv_type_id")
    private String purchaseAffectationIgvTypeId;

    //SETS

    public void setId(int id) {
        this.id = id;
    }

    public void setLine(@Nullable String line) {
        this.line = line;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setModel(@Nullable String model) {
        this.model = model;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public void setIsSet(int isSet) {
        this.isSet = isSet;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setHasIgv(int hasIgv) {
        this.hasIgv = hasIgv;
    }

    public void setHasIsc(int hasIsc) {
        this.hasIsc = hasIsc;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setLotCode(@Nullable String lotCode) {
        this.lotCode = lotCode;
    }

    public void setItemCode(@Nullable String itemCode) {
        this.itemCode = itemCode;
    }

    public void setItemType(@Nullable ItemType itemType) {
        this.itemType = itemType;
    }

    public void setStockMin(double stockMin) {
        this.stockMin = stockMin;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void setUnitType(@Nullable UnitType unitType) {
        this.unitType = unitType;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setAttributes(@Nullable List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public void setCreatedAt(@Nullable Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(@Nullable Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setWarehouses(@Nullable List<WarehouseRoot> warehouses) {
        this.warehouses = warehouses;
    }

    public void setApplyStore(int applyStore) {
        this.applyStore = applyStore;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setDateOfDue(@Nullable Date dateOfDue) {
        this.dateOfDue = dateOfDue;
    }

    public void setDescription(@Nullable String description) {
        this.description = description;
    }

    public void setImageSmall(@NonNull String imageSmall) {
        this.imageSmall = imageSmall;
    }

    public void setInternalId(@Nullable String internalId) {
        this.internalId = internalId;
    }

    public void setSecondName(@Nullable String secondName) {
        this.secondName = secondName;
    }

    public void setImageMedium(@NonNull String imageMedium) {
        this.imageMedium = imageMedium;
    }

    public void setItemTypeId(@NonNull String itemTypeId) {
        this.itemTypeId = itemTypeId;
    }

    public void setUnitTypeId(@NonNull String unitTypeId) {
        this.unitTypeId = unitTypeId;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public void setCurrencyType(@Nullable CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    public void setItemCodeGs1(@Nullable String itemCodeGs1) {
        this.itemCodeGs1 = itemCodeGs1;
    }

    public void setHasPerception(int hasPerception) {
        this.hasPerception = hasPerception;
    }

    public void setPercentageIsc(double percentageIsc) {
        this.percentageIsc = percentageIsc;
    }

    public void setSeriesEnabled(int seriesEnabled) {
        this.seriesEnabled = seriesEnabled;
    }

    public void setCommissionType(@Nullable String commissionType) {
        this.commissionType = commissionType;
    }

    public void setItemUnitTypes(@Nullable List<ItemUnitType> itemUnitTypes) {
        this.itemUnitTypes = itemUnitTypes;
    }

    public void setSaleUnitPrice(double saleUnitPrice) {
        this.saleUnitPrice = saleUnitPrice;
    }

    public void setSuggestedPrice(double suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }

    public void setWebPlatformId(int webPlatformId) {
        this.webPlatformId = webPlatformId;
    }

    public void setCurrencyTypeId(@NonNull String currencyTypeId) {
        this.currencyTypeId = currencyTypeId;
    }

    public void setPurchaseHasIgv(int purchaseHasIgv) {
        this.purchaseHasIgv = purchaseHasIgv;
    }

    public void setCommissionAmount(double commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public void setCalculateQuantity(int calculateQuantity) {
        this.calculateQuantity = calculateQuantity;
    }

    public void setExchangeRateSale(double exchangeRateSale) {
        this.exchangeRateSale = exchangeRateSale;
    }

    public void setSystemIscTypeId(@Nullable String systemIscTypeId) {
        this.systemIscTypeId = systemIscTypeId;
    }

    public void setPurchaseUnitPrice(double purchaseUnitPrice) {
        this.purchaseUnitPrice = purchaseUnitPrice;
    }

    public void setSaleUnitPriceSet(double saleUnitPriceSet) {
        this.saleUnitPriceSet = saleUnitPriceSet;
    }

    public void setPercentageOfProfit(double percentageOfProfit) {
        this.percentageOfProfit = percentageOfProfit;
    }

    public void setHasPlasticBagTaxes(int hasPlasticBagTaxes) {
        this.hasPlasticBagTaxes = hasPlasticBagTaxes;
    }

    public void setPercentage_perception(double percentage_perception) {
        this.percentage_perception = percentage_perception;
    }

    public void setAmountPlasticBagTaxes(double amountPlasticBagTaxes) {
        this.amountPlasticBagTaxes = amountPlasticBagTaxes;
    }

    public void setTechnicalSpecifications(@Nullable String technicalSpecifications) {
        this.technicalSpecifications = technicalSpecifications;
    }

    public void setSaleAffectationIgvTypeId(@NonNull String saleAffectationIgvTypeId) {
        this.saleAffectationIgvTypeId = saleAffectationIgvTypeId;
    }

    public void setPurchaseAffectationIgvTypeId(@NonNull String purchaseAffectationIgvTypeId) {
        this.purchaseAffectationIgvTypeId = purchaseAffectationIgvTypeId;
    }

    //GETS

    public int getId() {
        return id;
    }

    @Nullable
    public String getLine() {
        return line;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    @Nullable
    public String getModel() {
        return model;
    }

    public double getStock() {
        return stock;
    }

    public int getActive() {
        return active;
    }

    public int getIsSet() {
        return isSet;
    }

    public int getStatus() {
        return status;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getHasIgv() {
        return hasIgv;
    }

    public int getHasIsc() {
        return hasIsc;
    }

    public int getBrandId() {
        return brandId;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Nullable
    public String getLotCode() {
        return lotCode;
    }

    @Nullable
    public String getItemCode() {
        return itemCode;
    }

    @Nullable
    public ItemType getItemType() {
        return itemType;
    }

    public double getStockMin() {
        return stockMin;
    }

    public double getSubTotal() {
        return subTotal;
    }

    @Nullable
    public UnitType getUnitType() {
        return unitType;
    }

    public int getAccountId() {
        return accountId;
    }

    @Nullable
    public List<Attribute> getAttributes() {
        return attributes;
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
    public List<WarehouseRoot> getWarehouses() {
        return warehouses;
    }

    public int getApplyStore() {
        return applyStore;
    }

    public int getCategoryId() {
        return categoryId;
    }

    @Nullable
    public Date getDateOfDue() {
        return dateOfDue;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getImageSmall() {
        return imageSmall;
    }

    @Nullable
    public String getInternalId() {
        return internalId;
    }

    @Nullable
    public String getSecondName() {
        return secondName;
    }

    @NonNull
    public String getImageMedium() {
        return imageMedium;
    }

    @NonNull
    public String getItemTypeId() {
        return itemTypeId;
    }

    @NonNull
    public String getUnitTypeId() {
        return unitTypeId;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    @Nullable
    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    @Nullable
    public String getItemCodeGs1() {
        return itemCodeGs1;
    }

    public int getHasPerception() {
        return hasPerception;
    }

    public double getPercentageIsc() {
        return percentageIsc;
    }

    public int getSeriesEnabled() {
        return seriesEnabled;
    }

    @Nullable
    public String getCommissionType() {
        return commissionType;
    }

    @Nullable
    public List<ItemUnitType> getItemUnitTypes() {
        return itemUnitTypes;
    }

    public double getSaleUnitPrice() {
        return saleUnitPrice;
    }

    public double getSuggestedPrice() {
        return suggestedPrice;
    }

    public int getWebPlatformId() {
        return webPlatformId;
    }

    @NonNull
    public String getCurrencyTypeId() {
        return currencyTypeId;
    }

    public int getPurchaseHasIgv() {
        return purchaseHasIgv;
    }

    public double getCommissionAmount() {
        return commissionAmount;
    }

    public int getCalculateQuantity() {
        return calculateQuantity;
    }

    public double getExchangeRateSale() {
        return exchangeRateSale;
    }

    @Nullable
    public String getSystemIscTypeId() {
        return systemIscTypeId;
    }

    public double getPurchaseUnitPrice() {
        return purchaseUnitPrice;
    }

    public double getSaleUnitPriceSet() {
        return saleUnitPriceSet;
    }

    public double getPercentageOfProfit() {
        return percentageOfProfit;
    }

    public int getHasPlasticBagTaxes() {
        return hasPlasticBagTaxes;
    }

    public double getPercentage_perception() {
        return percentage_perception;
    }

    public double getAmountPlasticBagTaxes() {
        return amountPlasticBagTaxes;
    }

    @Nullable
    public String getTechnicalSpecifications() {
        return technicalSpecifications;
    }

    @NonNull
    public String getSaleAffectationIgvTypeId() {
        return saleAffectationIgvTypeId;
    }

    @NonNull
    public String getPurchaseAffectationIgvTypeId() {
        return purchaseAffectationIgvTypeId;
    }

    //CONSTRUCTOR

    public Item() {
    }
}
