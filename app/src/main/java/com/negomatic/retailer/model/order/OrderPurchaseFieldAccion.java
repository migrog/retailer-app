package com.negomatic.retailer.model.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderPurchaseFieldAccion {
    @Expose
    @SerializedName("formato_pdf")
    private String formatoPdf;

    @Expose
    @SerializedName("enviar_email")
    private boolean enviarEmail;
}
