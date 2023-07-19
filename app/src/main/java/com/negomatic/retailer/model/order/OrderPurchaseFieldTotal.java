package com.negomatic.retailer.model.order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.util.converter.ItemUnitTypeConverter;

import java.util.List;

public class OrderPurchaseFieldTotal {
    @Expose
    @SerializedName("total_igv")
    private double totalIgv;

    @Expose
    @SerializedName("total_valor")
    private double totalValor;

    @Expose
    @SerializedName("total_venta")
    private double totalVenta;

    @Expose
    @SerializedName("total_impuestos")
    private double totalImpuestos;

    @Expose
    @SerializedName("total_exportacion")
    private double totalExportacion;

    @Expose
    @SerializedName("total_operaciones_gravadas")
    private double totalOperacionesGravadas;

    @Expose
    @SerializedName("total_operaciones_gratuitas")
    private double totalOperacionesGratuitas;

    @Expose
    @SerializedName("total_operaciones_inafectas")
    private double totalOperacionesInafectas;

    @Expose
    @SerializedName("total_operaciones_inafectas")
    private double totalOperacionesExoneradas;
}
