package com.actividades.actividades.dto;

public class SubclasificacionRequest {
    private String nombre;
    private Long clasificacionId;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getClasificacionId() {
        return clasificacionId;
    }

    public void setClasificacionId(Long clasificacionId) {
        this.clasificacionId = clasificacionId;
    }
}
