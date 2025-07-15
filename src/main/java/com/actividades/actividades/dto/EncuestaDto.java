package com.actividades.actividades.dto;

import java.util.Set;

public class EncuestaDto {

    
    private Long idEncuesta;
    private String nombreEncuesta;
    private Set<PreguntaDto> preguntas;

    public Long getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Long idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getNombreEncuesta() {
        return nombreEncuesta;
    }

    public void setNombreEncuesta(String nombreEncuesta) {
        this.nombreEncuesta = nombreEncuesta;
    }

    public Set<PreguntaDto> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(Set<PreguntaDto> preguntas) {
        this.preguntas = preguntas;
    }

    
   

}
