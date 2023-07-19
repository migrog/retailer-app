package com.negomatic.retailer.model.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataCustomerOrReceptor {
    @Expose
    @SerializedName("ubigeo")
    private String ubigeo;

    @Expose
    @SerializedName("telefono")
    private String telefono;

    @Expose
    @SerializedName("direccion")
    private String direccion;

    @Expose
    @SerializedName("codigo_pais")
    private String codigoPais;

    @Expose
    @SerializedName("numero_documento")
    private String numeroDocumento;

    @Expose
    @SerializedName("correo_electronico")
    private String correoElectronico;

    @Expose
    @SerializedName("identity_document_type_id")
    private String identityDocumentTypeId;

    @Expose
    @SerializedName("codigo_tipo_documento_identidad")
    private String codigoTipoDocumentoIdentidad;

    @Expose
    @SerializedName("apellidos_y_nombres_o_razon_social")
    private String apellidosYNombresORazonSocial;
}
