package com.actividades.actividades.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class FotoProfesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "profesor_id", referencedColumnName = "id")
    private Profesor profesor;

    public FotoProfesor() {
    }

    public FotoProfesor(Profesor profesor, String nombreArchivo) {
        this.profesor = profesor;
        this.nombreArchivo = nombreArchivo;
    }

    private String nombreArchivo;

    public Long getId() {
        return id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

}
