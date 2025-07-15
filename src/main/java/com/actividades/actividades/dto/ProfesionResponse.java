package com.actividades.actividades.dto;

public class ProfesionResponse {

    private Long idProfesion;
    private String nombreProfesion;

    private ProfesionResponse(Builder builder) {
        this.idProfesion = builder.idProfesion;
        this.nombreProfesion = builder.nombreProfesion;
    }

    public Long getIdProfesion() {
        return idProfesion;
    }

    public void setIdProfesion(Long idProfesion) {
        this.idProfesion = idProfesion;
    }

    public String getNombreProfesion() {
        return nombreProfesion;
    }

    public void setNombreProfesion(String nombreProfesion) {
        this.nombreProfesion = nombreProfesion;
    }

     public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long idProfesion;
        private String nombreProfesion;

        public Builder idProfesion(Long idProfesion) {
            this.idProfesion = idProfesion;
            return this;
        }

        public Builder nombreProfesion(String nombreProfesion) {
            this.nombreProfesion = nombreProfesion;
            return this;
        }

        public ProfesionResponse build() {
            return new ProfesionResponse(this);
        }

    }

}
