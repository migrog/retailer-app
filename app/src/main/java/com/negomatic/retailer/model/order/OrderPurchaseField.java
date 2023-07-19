package com.negomatic.retailer.model.order;

import androidx.annotation.Nullable;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.negomatic.retailer.util.converter.DataCustomerOrReceptorConverter;
import com.negomatic.retailer.util.converter.order.OrderPurchaseFieldAccionConverter;
import com.negomatic.retailer.util.converter.order.OrderPurchaseFieldItemConverter;
import com.negomatic.retailer.util.converter.order.OrderPurchaseFieldTotalConverter;

import java.util.List;

public class OrderPurchaseField {
    @Nullable
    @Expose
    @TypeConverters(OrderPurchaseFieldItemConverter.class)
    @SerializedName("items")
    private List<OrderPurchaseFieldItem> items;

    @Nullable
    @Expose
    @TypeConverters(OrderPurchaseFieldTotalConverter.class)
    @SerializedName("totales")
    private OrderPurchaseFieldTotal total;

    @Nullable
    @Expose
    @TypeConverters(OrderPurchaseFieldAccionConverter.class)
    @SerializedName("acciones")
    private OrderPurchaseFieldAccion accion;

    @Expose
    @SerializedName("hora_de_emision")
    private String horaDeEmision;

    @Expose
    @SerializedName("serie_documento")
    private String serieDocumento;

    @Expose
    @SerializedName("fecha_de_emision")
    private String fechaDeEmision;

    @Expose
    @SerializedName("numero_documento")
    private String numeroDocumento;

    @Expose
    @SerializedName("codigo_tipo_moneda")
    private String codigoTipoMoneda;

    @Expose
    @SerializedName("fecha_de_vencimiento")
    private String fechaDeVencimiento;

    @Expose
    @SerializedName("codigo_tipo_documento")
    private String codigoTipoDocumento;

    @Expose
    @SerializedName("codigo_tipo_operacion")
    private String codigoTipoOperacion;

    @Expose
    @TypeConverters(DataCustomerOrReceptorConverter.class)
    @SerializedName("datos_del_cliente_o_receptor")
    private DataCustomerOrReceptor datosDelClienteOReceptor;

    //SETS


    public void setItems(@Nullable List<OrderPurchaseFieldItem> items) {
        this.items = items;
    }

    public void setTotal(@Nullable OrderPurchaseFieldTotal total) {
        this.total = total;
    }

    public void setAccion(@Nullable OrderPurchaseFieldAccion accion) {
        this.accion = accion;
    }

    public void setHoraDeEmision(String horaDeEmision) {
        this.horaDeEmision = horaDeEmision;
    }

    public void setSerieDocumento(String serieDocumento) {
        this.serieDocumento = serieDocumento;
    }

    public void setFechaDeEmision(String fechaDeEmision) {
        this.fechaDeEmision = fechaDeEmision;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public void setCodigoTipoMoneda(String codigoTipoMoneda) {
        this.codigoTipoMoneda = codigoTipoMoneda;
    }

    public void setFechaDeVencimiento(String fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
    }

    public void setCodigoTipoDocumento(String codigoTipoDocumento) {
        this.codigoTipoDocumento = codigoTipoDocumento;
    }

    public void setCodigoTipoOperacion(String codigoTipoOperacion) {
        this.codigoTipoOperacion = codigoTipoOperacion;
    }

    public void setDatosDelClienteOReceptor(DataCustomerOrReceptor datosDelClienteOReceptor) {
        this.datosDelClienteOReceptor = datosDelClienteOReceptor;
    }

    //GETS

    @Nullable
    public List<OrderPurchaseFieldItem> getItems() {
        return items;
    }

    @Nullable
    public OrderPurchaseFieldTotal getTotal() {
        return total;
    }

    @Nullable
    public OrderPurchaseFieldAccion getAccion() {
        return accion;
    }

    public String getHoraDeEmision() {
        return horaDeEmision;
    }

    public String getSerieDocumento() {
        return serieDocumento;
    }

    public String getFechaDeEmision() {
        return fechaDeEmision;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getCodigoTipoMoneda() {
        return codigoTipoMoneda;
    }

    public String getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    public String getCodigoTipoDocumento() {
        return codigoTipoDocumento;
    }

    public String getCodigoTipoOperacion() {
        return codigoTipoOperacion;
    }

    public DataCustomerOrReceptor getDatosDelClienteOReceptor() {
        return datosDelClienteOReceptor;
    }

    //CONSTRUCTOR

    public OrderPurchaseField() {}
}
