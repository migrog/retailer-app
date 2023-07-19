package com.negomatic.retailer.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "cat_identity_document_types")
public class IdentityDocumentType {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("code")
    @NonNull
    @Expose
    private String  code;

    @SerializedName("description")
    @NonNull
    @Expose
    private String  description;

    @SerializedName("country_id")
    @NonNull
    @Expose
    private String  countryId;

    @SerializedName("active")
    @NonNull
    @Expose
    private int active;

    public IdentityDocumentType(int id, @NonNull String code, @NonNull String description, @NonNull String countryId, int active) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.countryId = countryId;
        this.active = active;
    }

    @Ignore
    public IdentityDocumentType() {

    }

    //GETS

    public int getId() {
        return id;
    }

    @NonNull
    public String getCode() {
        return code;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getCountryId() {
        return countryId;
    }

    public int getActive() {
        return active;
    }

    //SETS

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(@NonNull String code) {
        this.code = code;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    public void setCountryId(@NonNull String countryId) {
        this.countryId = countryId;
    }

    public void setActive(int active) {
        this.active = active;
    }

}
