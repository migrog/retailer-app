package com.negomatic.retailer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attribute {
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("Ancho")
    @Expose
    private String Ancho;
    @SerializedName("attribute_type_id")
    @Expose
    private String attributeTypeId;

    //SETS

    public void setValue(String value) {
        this.value = value;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAncho(String ancho) {
        Ancho = ancho;
    }

    public void setAttributeTypeId(String attributeTypeId) {
        this.attributeTypeId = attributeTypeId;
    }

    //GETS

    public String getValue() {
        return value;
    }

    public String getDuration() {
        return duration;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getDescription() {
        return description;
    }

    public String getAncho() {
        return Ancho;
    }

    public String getAttributeTypeId() {
        return attributeTypeId;
    }

    //CONSTRUCTOR

    public Attribute() {
    }
}
