package com.negomatic.retailer.model.order_note;

import androidx.annotation.Nullable;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.util.converter.order_note.CountryConverter;
import com.negomatic.retailer.util.converter.order_note.DepartmentConverter;
import com.negomatic.retailer.util.converter.order_note.DistrictConverter;
import com.negomatic.retailer.util.converter.order_note.ProvinceConverter;

public class Establishment {
    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("email")
    private String email;

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

    @Expose
    @SerializedName("telephone")
    private String telephone;

    @Expose
    @SerializedName("country_id")
    private String countryId;

    @Expose
    @SerializedName("department")
    @TypeConverters(DepartmentConverter.class)
    private Department department;

    @Expose
    @SerializedName("district_id")
    private String districtId;

    @Expose
    @SerializedName("province_id")
    private String provinceId;

    @Nullable
    @Expose
    @SerializedName("web_address")
    private String webAddress;

    @Nullable
    @Expose
    @SerializedName("urbanization")
    private String urbanization;

    @Expose
    @SerializedName("department_id")
    private String departmentId;

    @Nullable
    @Expose
    @SerializedName("trade_address")
    private String tradeAddress;

    @Nullable
    @Expose
    @SerializedName("aditional_information")
    private String aditionalInformation;
}
