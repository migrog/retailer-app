package com.negomatic.retailer.model.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {
    @SerializedName("ubigeo")
    @Expose
    private String ubigeo;

    @SerializedName("telefono")
    @Expose
    private String telefono;

    @SerializedName("direccion")
    @Expose
    private String direccion;

    @SerializedName("codigo_pais")
    @Expose
    private String codigoPais;

    @SerializedName("numero_documento")
    @Expose
    private String numeroDocumento;

    @SerializedName("correo_electronico")
    @Expose
    private String correoElectronico;

    @SerializedName("identity_document_type_id")
    @Expose
    private String identityDocumentTypeId;

    @SerializedName("codigo_tipo_documento_identidad")
    @Expose
    private String codigoTipoDocumentoIdentidad;

    @SerializedName("apellidos_y_nombres_o_razon_social")
    @Expose
    private String apellidosYNombresORazonSocial;

    //SETS

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setIdentityDocumentTypeId(String identityDocumentTypeId) {
        this.identityDocumentTypeId = identityDocumentTypeId;
    }

    public void setCodigoTipoDocumentoIdentidad(String codigoTipoDocumentoIdentidad) {
        this.codigoTipoDocumentoIdentidad = codigoTipoDocumentoIdentidad;
    }

    public void setApellidosYNombresORazonSocial(String apellidosYNombresORazonSocial) {
        this.apellidosYNombresORazonSocial = apellidosYNombresORazonSocial;
    }

    //GETS

    public String getUbigeo() {
        return ubigeo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getIdentityDocumentTypeId() {
        return identityDocumentTypeId;
    }

    public String getCodigoTipoDocumentoIdentidad() {
        return codigoTipoDocumentoIdentidad;
    }

    public String getApellidosYNombresORazonSocial() {
        return apellidosYNombresORazonSocial;
    }

    //CONSTRUCTOR

    public Customer() {
    }
}
