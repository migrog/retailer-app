package com.negomatic.retailer.model.order;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPurchaseFieldItem {
    @Expose
    @SerializedName("cantidad")
    private int cantidad;

    @Expose
    @SerializedName("total_igv")
    private double totalIgv;

    @Expose
    @SerializedName("total_item")
    private int totalItem;

    @Expose
    @SerializedName("descripcion")
    private int descripcion;

    @Expose
    @SerializedName("codigo_interno")
    private String codigoInterno;

    @Expose
    @SerializedName("porcentaje_igv")
    private int porcentajeIgv;

    @Expose
    @SerializedName("total_base_igv")
    private double totalBaseIgv;

    @Expose
    @SerializedName("valor_unitario")
    private double valorUnitario;

    @Expose
    @SerializedName("precio_unitario")
    private double precioUnitario;

    @Expose
    @SerializedName("total_impuestos")
    private double totalImpuestos;

    @Expose
    @SerializedName("total_valor_item")
    private double totalValorItem;

    @Expose
    @SerializedName("unidad_de_medida")
    private String unidadDeMedida;

    @Expose
    @SerializedName("codigo_tipo_precio")
    private String codigoTipoPrecio;

    @Nullable
    @Expose
    @SerializedName("codigo_producto_sunat")
    private String codigoProductoSunat;

    @Expose
    @SerializedName("codigo_tipo_afectacion_igv")
    private String codigoTipoAfectacionIgv;

}
