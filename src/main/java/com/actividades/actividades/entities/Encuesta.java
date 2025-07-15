package com.actividades.actividades.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Encuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreEncuesta;

    public Encuesta(String nombreEncuesta) {
        this.nombreEncuesta = nombreEncuesta;
    }

    public Encuesta() {
    }

    @OneToMany(mappedBy = "encuesta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PreguntaEncuesta> preguntas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PreguntaEncuesta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaEncuesta> preguntas) {
        this.preguntas = preguntas;
    }

    public String getNombreEncuesta() {
        return nombreEncuesta;
    }

    public void setNombreEncuesta(String nombreEncuesta) {
        this.nombreEncuesta = nombreEncuesta;
    }

}
