package com.actividades.actividades.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PreguntaEncuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pregunta;

    @ManyToOne
    @JoinColumn(name = "encuesta_id")
    private Encuesta encuesta;

    public PreguntaEncuesta() {
    }

    public PreguntaEncuesta(String pregunta, Encuesta encuesta) {
        this.pregunta = pregunta;
        this.encuesta = encuesta;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPregunta() {
        return this.pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

}
