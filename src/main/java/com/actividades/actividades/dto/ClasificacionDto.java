package com.actividades.actividades.dto;

import java.util.List;

public class ClasificacionDto {

    private Long id;
    private String nombre;
    private List<SubclasificacionDto> subclasificaciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<SubclasificacionDto> getSubclasificaciones() {
        return subclasificaciones;
    }

    public void setSubclasificaciones(List<SubclasificacionDto> subclasificaciones) {
        this.subclasificaciones = subclasificaciones;
    }

}
