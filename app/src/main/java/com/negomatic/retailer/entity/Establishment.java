package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.util.converter.date.LocalDateTimeConverter;

import java.time.LocalDateTime;

@Entity(tableName = "establishments")
public class Establishment {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @Expose
    @SerializedName("id")
    private int id;

    @NonNull
    @Expose
    @SerializedName("description")
    private String description;

    @NonNull
    @Expose
    @SerializedName("country_id")
    private String countryId;

    @NonNull
    @Expose
    @SerializedName("department_id")
    private String departmentId;

    @NonNull
    @Expose
    @SerializedName("province_id")
    private String provinceId;

    @NonNull
    @Expose
    @SerializedName("district_id")
    private String districtId;

    @NonNull
    @Expose
    @SerializedName("address")
    private String address;

    @NonNull
    @Expose
    @SerializedName("email")
    private String email;

    @NonNull
    @Expose
    @SerializedName("telephone")
    private String telephone;

    @NonNull
    @Expose
    @SerializedName("code")
    private String code;

    @Nullable
    @Expose
    @SerializedName("aditional_information")
    private String aditionalInformation;

    @Nullable
    @Expose
    @SerializedName("web_address")
    private String webAddress;

    @Nullable
    @Expose
    @SerializedName("trade_address")
    private String tradeAddress;

    @Nullable
    @Expose
    @SerializedName("customer_id")
    private String customerId;

    @Nullable
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

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public void setCountryId(@NonNull String countryId) {
        this.countryId = countryId;
    }

    public void setDepartmentId(@NonNull String departmentId) {
        this.departmentId = departmentId;
    }

    public void setProvinceId(@NonNull String provinceId) {
        this.provinceId = provinceId;
    }

    public void setDistrictId(@NonNull String districtId) {
        this.districtId = districtId;
    }

    public void setAddress(@NonNull String address) {
        this.address = address;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setTelephone(@NonNull String telephone) {
        this.telephone = telephone;
    }

    public void setCode(@NonNull String code) {
        this.code = code;
    }

    public void setAditionalInformation(@Nullable String aditionalInformation) {
        this.aditionalInformation = aditionalInformation;
    }

    public void setWebAddress(@Nullable String webAddress) {
        this.webAddress = webAddress;
    }

    public void setTradeAddress(@Nullable String tradeAddress) {
        this.tradeAddress = tradeAddress;
    }

    public void setCustomerId(@Nullable String customerId) {
        this.customerId = customerId;
    }

    public void setCreatedAt(@Nullable LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(@Nullable LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    //GETS

    public int getId() {
        return id;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getCountryId() {
        return countryId;
    }

    @NonNull
    public String getDepartmentId() {
        return departmentId;
    }

    @NonNull
    public String getProvinceId() {
        return provinceId;
    }

    @NonNull
    public String getDistrictId() {
        return districtId;
    }

    @NonNull
    public String getAddress() {
        return address;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getTelephone() {
        return telephone;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @Nullable
    public String getAditionalInformation() {
        return aditionalInformation;
    }

    @Nullable
    public String getWebAddress() {
        return webAddress;
    }

    @Nullable
    public String getTradeAddress() {
        return tradeAddress;
    }

    @Nullable
    public String getCustomerId() {
        return customerId;
    }

    @Nullable
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Nullable
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    //CONSTRUCTOR

    public Establishment() {
    }
}
