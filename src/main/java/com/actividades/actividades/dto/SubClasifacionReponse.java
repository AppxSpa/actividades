package com.actividades.actividades.dto;

public class SubClasifacionReponse {

    private Long idSubclasificacion;
    private String nombreSubclasificacion;
    private String nombreClasificacion;

    

    public SubClasifacionReponse() {
    }

    

    public SubClasifacionReponse(Long idSubclasificacion, String nombreSubclasificacion, String nombreClasificacion) {
        this.idSubclasificacion = idSubclasificacion;
        this.nombreSubclasificacion = nombreSubclasificacion;
        this.nombreClasificacion = nombreClasificacion;
    }



    public Long getIdSubclasificacion() {
        return idSubclasificacion;
    }

    public void setIdSubclasificacion(Long idSubclasificacion) {
        this.idSubclasificacion = idSubclasificacion;
    }

    public String getNombreSubclasificacion() {
        return nombreSubclasificacion;
    }

    public void setNombreSubclasificacion(String nombreSubclasificacion) {
        this.nombreSubclasificacion = nombreSubclasificacion;
    }

    public String getNombreClasificacion() {
        return nombreClasificacion;
    }

    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }

}
