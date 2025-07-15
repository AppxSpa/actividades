package com.actividades.actividades.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Subclasificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreSubClasificacion;

    @ManyToOne
    @JoinColumn(name = "clasificacion_id")
    private Clasificacion clasificacion;

     @ManyToMany(mappedBy = "subclasificaciones")
    private List<Profesor> profesores;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreSubClasificacion() {
        return nombreSubClasificacion;
    }

    public void setNombreSubClasificacion(String nombreSubClasificacion) {
        this.nombreSubClasificacion = nombreSubClasificacion;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getNombreClasificacion() {
        return this.clasificacion.getNombreClasificacion();
    }
}
  