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

@Entity(tableName = "persons")
public class Person {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("type")
    @Expose
    @NonNull
    private String type;

    @SerializedName("identity_document_type_id")
    @Expose
    @NonNull
    private String identityDocumentTypeId;

    @SerializedName("number")
    @Expose
    @NonNull
    private String number;


    @SerializedName("name")
    @Expose
    @NonNull
    private String name;

    @SerializedName("internal_code")
    @Expose
    @Nullable
    private String internalCode;

    @SerializedName("country_id")
    @Expose
    @NonNull
    private String countryId;

    @SerializedName("department_id")
    @Expose
    @Nullable
    private String departmentId;

    @SerializedName("province_id")
    @Expose
    @Nullable
    private String provinceId;

    @SerializedName("district_id")
    @Expose
    @Nullable
    private String districtId;

    @SerializedName("address")
    @Expose
    @Nullable
    private String address;

    @SerializedName("email")
    @Expose
    @Nullable
    private String email;

    @SerializedName("telephone")
    @Expose
    @Nullable
    private String telephone;

    @SerializedName("enabled")
    @Expose
    @NonNull
    private int enabled;

    @SerializedName("created_at")
    @Nullable
    @Expose
    @TypeConverters(LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @SerializedName("updated_at")
    @Nullable
    @Expose
    @TypeConverters(LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;

    @SerializedName("trade_name")
    @Expose
    @Nullable
    private String tradeName;

    //GETS


    public int getId() {
        return id;
    }

    @NonNull
    public String getType() {
        return type;
    }

    @NonNull
    public String getIdentityDocumentTypeId() {
        return identityDocumentTypeId;
    }

    @NonNull
    public String getNumber() {
        return number;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getInternalCode() {
        return internalCode;
    }

    @NonNull
    public String getCountryId() {
        return countryId;
    }

    @Nullable
    public String getDepartmentId() {
        return departmentId;
    }

    @Nullable
    public String getProvinceId() {
        return provinceId;
    }

    @Nullable
    public String getDistrictId() {
        return districtId;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    @Nullable
    public String getTelephone() {
        return telephone;
    }

    public int getEnabled() {
        return enabled;
    }

    @Nullable
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Nullable
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Nullable
    public String getTradeName() {
        return tradeName;
    }
//SETS

    public void setId(int id) {
        this.id = id;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    public void setIdentityDocumentTypeId(@NonNull String identityDocumentTypeId) {
        this.identityDocumentTypeId = identityDocumentTypeId;
    }

    public void setNumber(@NonNull String number) {
        this.number = number;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setInternalCode(@Nullable String internalCode) {
        this.internalCode = internalCode;
    }

    public void setCountryId(@NonNull String countryId) {
        this.countryId = countryId;
    }

    public void setDepartmentId(@Nullable String departmentId) {
        this.departmentId = departmentId;
    }

    public void setProvinceId(@Nullable String provinceId) {
        this.provinceId = provinceId;
    }

    public void setDistrictId(@Nullable String districtId) {
        this.districtId = districtId;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    public void setTelephone(@Nullable String telephone) {
        this.telephone = telephone;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public void setCreatedAt(@Nullable LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(@Nullable LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setTradeName(@Nullable String tradeName) {
        this.tradeName = tradeName;
    }
}
