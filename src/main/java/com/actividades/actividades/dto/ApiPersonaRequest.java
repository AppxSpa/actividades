package com.actividades.actividades.dto;

import java.time.LocalDate;

public class ApiPersonaRequest {

    private Integer rut;
    private String vrut;
    private String nombres;
    private String paterno;
    private String materno;
    private LocalDate fechaNac;
    private Integer fono;
    private String email;
    private String estadoCivil;
    private String genero;
    private String nacionalidad;
    private Long dirId;

    private ApiPersonaRequest(Builder builder) {
        this.rut = builder.rut;
        this.vrut = builder.vrut;
        this.nombres = builder.nombres;
        this.paterno = builder.paterno;
        this.materno = builder.materno;
        this.fechaNac = builder.fechaNac;
        this.fono = builder.fono;
        this.email = builder.email;
        this.estadoCivil = builder.estadoCivil;
        this.genero = builder.genero;
        this.nacionalidad = builder.nacionalidad;
        this.dirId = builder.dirId;
    }

    public static class Builder {
        private Integer rut;
        private String vrut;
        private String nombres;
        private String paterno;
        private String materno;
        private LocalDate fechaNac;
        private Integer fono;
        private String email;
        private String estadoCivil;
        private String genero;
        private String nacionalidad;
        private Long dirId;

        public Builder rut(Integer rut) {
            this.rut = rut;
            return this;
        }

        public Builder vrut(String vrut) {
            this.vrut = vrut;
            return this;
        }

        public Builder nombres(String nombres) {
            this.nombres = nombres;
            return this;
        }

        public Builder paterno(String paterno) {
            this.paterno = paterno;
            return this;
        }

        public Builder materno(String materno) {
            this.materno = materno;
            return this;
        }

        public Builder fechaNac(LocalDate fechaNac) {
            this.fechaNac = fechaNac;
            return this;
        }

        public Builder fono(Integer fono) {
            this.fono = fono;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder estadoCivil(String estadoCivil) {
            this.estadoCivil = estadoCivil;
            return this;
        }

        public Builder genero(String genero) {
            this.genero = genero;
            return this;
        }

        public Builder nacionalidad(String nacionalidad) {
            this.nacionalidad = nacionalidad;
            return this;
        }

        public ApiPersonaRequest build() {
            return new ApiPersonaRequest(this);
        }

        public Builder dirId(Long dirId) {
            this.dirId = dirId;
            return this;
        }
    }

    public Long getDirId() {
        return dirId;
    }

    public void setDirId(Long dirId) {
        this.dirId = dirId;
    }

    // Getters y setters
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
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public Integer getFono() {
        return fono;
    }

    public void setFono(Integer fono) {
        this.fono = fono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
