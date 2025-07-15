package com.actividades.actividades.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class RespuestaEncuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "pregunta_id")
    private PreguntaEncuesta pregunta;

    @ManyToOne
    @JoinColumn(name = "encuesta_id")
    private Encuesta encuesta;

    @ManyToOne
    @JoinColumn(name = "actividad_id")
    private Actividad actividad;

    private LocalDate fechaRespuesta;

    private Double nota;

    public RespuestaEncuesta() {
    }

    public RespuestaEncuesta(Persona persona, PreguntaEncuesta pregunta, Encuesta encuesta, Actividad actividad,
            LocalDate fechaRespuesta, Double nota) {
        this.persona = persona;
        this.pregunta = pregunta;
        this.encuesta = encuesta;
        this.actividad = actividad;
        this.fechaRespuesta = fechaRespuesta;
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public LocalDate getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(LocalDate fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public PreguntaEncuesta getPregunta() {
        return pregunta;
    }

    public void setPregunta(PreguntaEncuesta pregunta) {
        this.pregunta = pregunta;
    }

}
