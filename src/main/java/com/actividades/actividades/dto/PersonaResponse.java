package com.actividades.actividades.dto;

import java.util.Optional;

public class PersonaResponse {

    private Integer rut;
    private String vrut;
    private String nombres;
    private String paterno;
    private String materno;
    private String email;

    public PersonaResponse() {

    }

    public PersonaResponse(Integer rut, String vrut, String nombres, String paterno, String materno, String email) {
        this.rut = rut;
        this.vrut = vrut;
        this.nombres = nombres;
        this.paterno = paterno;
        this.materno = materno;
        this.email = email;
    }

    public Integer getRut() {
        return rut;
    }

    public void setRut(Integer rut) {
        this.rut = rut;
    }

    public String getVrut() {
        return vrut;
    }

    public void setVrut(String vrut) {
        this.vrut = vrut;
    }

    public String getNombres() {
        return Optional.ofNullable(this.nombres)
                .orElse(null);
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return Optional.ofNullable(this.paterno)
                .orElse(null);
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return Optional.ofNullable(this.materno)
                .orElse(null);
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreCompleto() {

        return Optional.ofNullable(this.nombres).orElse("")
                .concat(" ").concat(Optional.ofNullable(this.paterno).orElse("")).concat(" ")
                .concat(Optional.ofNullable(this.materno).orElse(""));
    }

}