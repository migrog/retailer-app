package com.negomatic.retailer.model.order_note;

import androidx.annotation.Nullable;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.util.converter.order_note.CountryConverter;
import com.negomatic.retailer.util.converter.order_note.DepartmentConverter;
import com.negomatic.retailer.util.converter.order_note.DistrictConverter;
import com.negomatic.retailer.util.converter.order_note.ProvinceConverter;
import com.negomatic.retailer.util.converter.order_note.IdentityDocumentTypeConverter;

public class Customer {
    @Expose
    @SerializedName("name")
    private String name;

    @Nullable
    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("number")
    private String number;

    @Nullable
    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("country")
    @TypeConverters(CountryConverter.class)
    private Country country;

    @Expose
    @SerializedName("district")
    @TypeConverters(DistrictConverter.class)
    private District district;

    @Expose
    @SerializedName("province")
    @TypeConverters(ProvinceConverter.class)
    private Province province;

    @Nullable
    @Expose
    @SerializedName("telephone")
    private String telephone;

    @Nullable
    @Expose
    @SerializedName("address_id")
    private String addressId;

    @Expose
    @SerializedName("country_id")
    private String countryId;

    @Expose
    @SerializedName("department")
    @TypeConverters(DepartmentConverter.class)
    private Department department;

    @Nullable
    @Expose
    @SerializedName("trade_name")
    private String tradeName;

    @Nullable
    @Expose
    @SerializedName("district_id")
    private String districtId;

    @Nullable
    @Expose
    @SerializedName("province_id")
    private String provinceId;

    @Nullable
    @Expose
    @SerializedName("department_id")
    private String departmentId;

    @Nullable
    @Expose
    @SerializedName("internal_code")
    private String internalCode;

    @Nullable
    @Expose
    @SerializedName("perception_agent")
    private int perceptionAgent;

    @Nullable
    @Expose
    @TypeConverters(IdentityDocumentTypeConverter.class)
    @SerializedName("identity_document_type")
    private IdentityDocumentType identityDocumentType;

    @Expose
    @SerializedName("identity_document_type_id")
    private String identityDocumentTypeId;

    //SETS

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public void setTelephone(@Nullable String telephone) {
        this.telephone = telephone;
    }

    public void setAddressId(@Nullable String addressId) {
        this.addressId = addressId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setTradeName(@Nullable String tradeName) {
        this.tradeName = tradeName;
    }

    public void setDistrictId(@Nullable String districtId) {
        this.districtId = districtId;
    }

    public void setProvinceId(@Nullable String provinceId) {
        this.provinceId = provinceId;
    }

    public void setDepartmentId(@Nullable String departmentId) {
        this.departmentId = departmentId;
    }

    public void setInternalCode(@Nullable String internalCode) {
        this.internalCode = internalCode;
    }

    public void setPerceptionAgent(int perceptionAgent) {
        this.perceptionAgent = perceptionAgent;
    }

    public void setIdentityDocumentType(@Nullable IdentityDocumentType identityDocumentType) {
        this.identityDocumentType = identityDocumentType;
    }

    public void setIdentityDocumentTypeId(String identityDocumentTypeId) {
        this.identityDocumentTypeId = identityDocumentTypeId;
    }

    //GETS

    public String getName() {
        return name;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    public Country getCountry() {
        return country;
    }

    public District getDistrict() {
        return district;
    }

    public Province getProvince() {
        return province;
    }

    @Nullable
    public String getTelephone() {
        return telephone;
    }

    @Nullable
    public String getAddressId() {
        return addressId;
    }

    public String getCountryId() {
        return countryId;
    }

    public Department getDepartment() {
        return department;
    }

    @Nullable
    public String getTradeName() {
        return tradeName;
    }

    @Nullable
    public String getDistrictId() {
        return districtId;
    }

    @Nullable
    public String getProvinceId() {
        return provinceId;
    }

    @Nullable
    public String getDepartmentId() {
        return departmentId;
    }

    @Nullable
    public String getInternalCode() {
        return internalCode;
    }

    public int getPerceptionAgent() {
        return perceptionAgent;
    }

    @Nullable
    public IdentityDocumentType getIdentityDocumentType() {
        return identityDocumentType;
    }

    public String getIdentityDocumentTypeId() {
        return identityDocumentTypeId;
    }
}

