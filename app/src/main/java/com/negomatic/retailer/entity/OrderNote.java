package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.model.order_note.Customer;
import com.negomatic.retailer.model.order_note.Establishment;
import com.negomatic.retailer.util.converter.ObjectConverter;
import com.negomatic.retailer.util.converter.ObjectListConverter;
import com.negomatic.retailer.util.converter.date.DateConverter;
import com.negomatic.retailer.util.converter.date.LocalDateConvert;
import com.negomatic.retailer.util.converter.date.LocalDateTimeConverter;
import com.negomatic.retailer.util.converter.date.LocalTimeConverter;
import com.negomatic.retailer.util.converter.order_note.CustomerConverter;
import com.negomatic.retailer.util.converter.order_note.EstablishmentConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity(tableName = "order_notes")
public class OrderNote {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @Expose
    @SerializedName("id")
    private int id;

    @NonNull
    @Expose
    @SerializedName("user_id")
    private int userId;

    @NonNull
    @Expose
    @SerializedName("external_id")
    private String externalId;

    @NonNull
    @Expose
    @SerializedName("establishment_id")
    private int establishmentId;

    @NonNull
    @Expose
    @SerializedName("establishment")
    @TypeConverters(EstablishmentConverter.class)
    private Establishment establishment;

    @Nullable
    @Expose
    @SerializedName("soap_type_id")
    private String soapTypeId;

    @Nullable
    @Expose
    @SerializedName("state_type_id")
    private String stateTypeId;

    @Nullable
    @Expose
    @SerializedName("prefix")
    private String prefix;

    @Nullable
    @Expose
    @TypeConverters(LocalDateConvert.class)
    @SerializedName("date_of_issue")
    private LocalDate dateOfIssue;

    @Nullable
    @Expose
    @TypeConverters(LocalTimeConverter.class)
    @SerializedName("time_of_issue")
    private LocalTime timeOfIssue;

    @Nullable
    @Expose
    @TypeConverters(LocalDateConvert.class)
    @SerializedName("date_of_due")
    private LocalDate dateOfDue;

    @Nullable
    @Expose
    @TypeConverters(LocalDateConvert.class)
    @SerializedName("delivery_date")
    private LocalDate deliveryDate;

    @NonNull
    @Expose
    @SerializedName("customer_id")
    private int customerId;

    @NonNull
    @Expose
    @TypeConverters(CustomerConverter.class)
    @SerializedName("customer")
    private Customer customer;

    @Nullable
    @Expose
    @SerializedName("shipping_address")
    private String shippingAddress;

    @NonNull
    @Expose
    @SerializedName("currency_type_id")
    private String currencyTypeId;

    @Nullable
    @Expose
    @SerializedName("payment_method_type_id")
    private String paymentMethodTypeId;

    @NonNull
    @Expose
    @SerializedName("exchange_rate_sale")
    private double exchangeRateSale;

    @NonNull
    @Expose
    @SerializedName("totalPrepayment")
    private double totalPrepayment;

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
    @SerializedName("total_exportation")
    private double totalExportation;

    @NonNull
    @Expose
    @SerializedName("total_free")
    private double totalFree;

    @NonNull
    @Expose
    @SerializedName("total_taxed")
    private double totalTaxed;

    @NonNull
    @Expose
    @SerializedName("total_unaffected")
    private double totalUnaffected;

    @NonNull
    @Expose
    @SerializedName("total_exonerated")
    private double totalExonerated;

    @NonNull
    @Expose
    @SerializedName("total_igv")
    private double totalIgv;

    @NonNull
    @Expose
    @SerializedName("total_base_isc")
    private double totalBaseIsc;

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
    @SerializedName("total_other_taxes")
    private double totalOtherTaxes;

    @NonNull
    @Expose
    @SerializedName("total_taxes")
    private double totalTaxes;

    @NonNull
    @Expose
    @SerializedName("total_value")
    private double totalValue;

    @NonNull
    @Expose
    @SerializedName("total")
    private double total;

    @Nullable
    @Expose
    @TypeConverters(ObjectListConverter.class)
    @SerializedName("charges")
    private List<Object> charges;

    @Nullable
    @Expose
    @TypeConverters(ObjectListConverter.class)
    @SerializedName("discounts")
    private List<Object> discounts;

    @Nullable
    @Expose
    @TypeConverters(ObjectConverter.class)
    @SerializedName("prepayments")
    private Object prepayments;

    @Nullable
    @Expose
    @TypeConverters(ObjectListConverter.class)
    @SerializedName("guides")
    private List<Object> guides;

    @Nullable
    @Expose
    @TypeConverters(ObjectConverter.class)
    @SerializedName("related")
    private Object related;

    @Nullable
    @Expose
    @TypeConverters(ObjectConverter.class)
    @SerializedName("perception")
    private Object perception;

    @Nullable
    @Expose
    @TypeConverters(ObjectConverter.class)
    @SerializedName("detraction")
    private Object detraction;

    @Nullable
    @Expose
    @TypeConverters(ObjectConverter.class)
    @SerializedName("legends")
    private Object legends;

    @Nullable
    @Expose
    @SerializedName("filename")
    private String filename;

    @Nullable
    @Expose
    @TypeConverters(ObjectConverter.class)
    @SerializedName("observation")
    private Object observation;

    @Expose
    @TypeConverters(LocalDateTimeConverter.class)
    @SerializedName("created_at")
    private LocalDateTime createdAt;

    @Nullable
    @Expose
    @TypeConverters(LocalDateTimeConverter.class)
    @SerializedName("updated_at")
    private LocalDateTime updatedAt;

    //SETS

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setExternalId(@NonNull String externalId) {
        this.externalId = externalId;
    }

    public void setEstablishmentId(int establishmentId) {
        this.establishmentId = establishmentId;
    }

    public void setEstablishment(@NonNull Establishment establishment) {
        this.establishment = establishment;
    }

    public void setSoapTypeId(@Nullable String soapTypeId) {
        this.soapTypeId = soapTypeId;
    }

    public void setStateTypeId(@Nullable String stateTypeId) {
        this.stateTypeId = stateTypeId;
    }

    public void setPrefix(@Nullable String prefix) {
        this.prefix = prefix;
    }

    public void setDateOfIssue(@Nullable LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public void setTimeOfIssue(@Nullable LocalTime timeOfIssue) {
        this.timeOfIssue = timeOfIssue;
    }

    public void setDateOfDue(@NonNull LocalDate dateOfDue) {
        this.dateOfDue = dateOfDue;
    }

    public void setDeliveryDate(@NonNull LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomer(@NonNull Customer customer) {
        this.customer = customer;
    }

    public void setShippingAddress(@Nullable String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setCurrencyTypeId(@NonNull String currencyTypeId) {
        this.currencyTypeId = currencyTypeId;
    }

    public void setPaymentMethodTypeId(@Nullable String paymentMethodTypeId) {
        this.paymentMethodTypeId = paymentMethodTypeId;
    }

    public void setExchangeRateSale(double exchangeRateSale) {
        this.exchangeRateSale = exchangeRateSale;
    }

    public void setTotalPrepayment(double totalPrepayment) {
        this.totalPrepayment = totalPrepayment;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public void setTotalExportation(double totalExportation) {
        this.totalExportation = totalExportation;
    }

    public void setTotalFree(double totalFree) {
        this.totalFree = totalFree;
    }

    public void setTotalTaxed(double totalTaxed) {
        this.totalTaxed = totalTaxed;
    }

    public void setTotalUnaffected(double totalUnaffected) {
        this.totalUnaffected = totalUnaffected;
    }

    public void setTotalExonerated(double totalExonerated) {
        this.totalExonerated = totalExonerated;
    }

    public void setTotalIgv(double totalIgv) {
        this.totalIgv = totalIgv;
    }

    public void setTotalBaseIsc(double totalBaseIsc) {
        this.totalBaseIsc = totalBaseIsc;
    }

    public void setTotalIsc(double totalIsc) {
        this.totalIsc = totalIsc;
    }

    public void setTotalBaseOtherTaxes(double totalBaseOtherTaxes) {
        this.totalBaseOtherTaxes = totalBaseOtherTaxes;
    }

    public void setTotalOtherTaxes(double totalOtherTaxes) {
        this.totalOtherTaxes = totalOtherTaxes;
    }

    public void setTotalTaxes(double totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setCharges(@Nullable List<Object> charges) {
        this.charges = charges;
    }

    public void setDiscounts(@Nullable List<Object> discounts) {
        this.discounts = discounts;
    }

    public void setPrepayments(@Nullable Object prepayments) {
        this.prepayments = prepayments;
    }

    public void setGuides(@Nullable List<Object> guides) {
        this.guides = guides;
    }

    public void setRelated(@Nullable Object related) {
        this.related = related;
    }

    public void setPerception(@Nullable Object perception) {
        this.perception = perception;
    }

    public void setDetraction(@Nullable Object detraction) {
        this.detraction = detraction;
    }

    public void setLegends(@Nullable Object legends) {
        this.legends = legends;
    }

    public void setFilename(@Nullable String filename) {
        this.filename = filename;
    }

    public void setObservation(@Nullable Object observation) {
        this.observation = observation;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(@Nullable LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    //GETS

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    @NonNull
    public String getExternalId() {
        return externalId;
    }

    public int getEstablishmentId() {
        return establishmentId;
    }

    @NonNull
    public Establishment getEstablishment() {
        return establishment;
    }

    @Nullable
    public String getSoapTypeId() {
        return soapTypeId;
    }

    @Nullable
    public String getStateTypeId() {
        return stateTypeId;
    }

    @Nullable
    public String getPrefix() {
        return prefix;
    }

    @Nullable
    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    @Nullable
    public LocalTime getTimeOfIssue() {
        return timeOfIssue;
    }

    @NonNull
    public LocalDate getDateOfDue() {
        return dateOfDue;
    }

    @NonNull
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    @NonNull
    public Customer getCustomer() {
        return customer;
    }

    @Nullable
    public String getShippingAddress() {
        return shippingAddress;
    }

    @NonNull
    public String getCurrencyTypeId() {
        return currencyTypeId;
    }

    @Nullable
    public String getPaymentMethodTypeId() {
        return paymentMethodTypeId;
    }

    public double getExchangeRateSale() {
        return exchangeRateSale;
    }

    public double getTotalPrepayment() {
        return totalPrepayment;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public double getTotalExportation() {
        return totalExportation;
    }

    public double getTotalFree() {
        return totalFree;
    }

    public double getTotalTaxed() {
        return totalTaxed;
    }

    public double getTotalUnaffected() {
        return totalUnaffected;
    }

    public double getTotalExonerated() {
        return totalExonerated;
    }

    public double getTotalIgv() {
        return totalIgv;
    }

    public double getTotalBaseIsc() {
        return totalBaseIsc;
    }

    public double getTotalIsc() {
        return totalIsc;
    }

    public double getTotalBaseOtherTaxes() {
        return totalBaseOtherTaxes;
    }

    public double getTotalOtherTaxes() {
        return totalOtherTaxes;
    }

    public double getTotalTaxes() {
        return totalTaxes;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public double getTotal() {
        return total;
    }

    @Nullable
    public List<Object> getCharges() {
        return charges;
    }

    @Nullable
    public List<Object> getDiscounts() {
        return discounts;
    }

    @Nullable
    public Object getPrepayments() {
        return prepayments;
    }

    @Nullable
    public List<Object> getGuides() {
        return guides;
    }

    @Nullable
    public Object getRelated() {
        return related;
    }

    @Nullable
    public Object getPerception() {
        return perception;
    }

    @Nullable
    public Object getDetraction() {
        return detraction;
    }

    @Nullable
    public Object getLegends() {
        return legends;
    }

    @Nullable
    public String getFilename() {
        return filename;
    }

    @Nullable
    public Object getObservation() {
        return observation;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Nullable
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    //CONSTRUCTOR

    public OrderNote() {
    }
}
