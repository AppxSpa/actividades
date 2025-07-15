package com.actividades.actividades.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Clasificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreClasificacion; 

    

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public String getNombreClasificacion() {
        return nombreClasificacion;
    }



    public void setNombreClasificacion(String nombreClasificacion) {
        this.nombreClasificacion = nombreClasificacion;
    }



    public List<Subclasificacion> getSubclasificaciones() {
        return subclasificaciones;
    }



    public void setSubclasificaciones(List<Subclasificacion> subclasificaciones) {
        this.subclasificaciones = subclasificaciones;
    }



    @OneToMany(mappedBy = "clasificacion", cascade = CascadeType.ALL)
    private List<Subclasificacion> subclasificaciones = new ArrayList<>();
}