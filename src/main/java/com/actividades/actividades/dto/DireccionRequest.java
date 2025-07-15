package com.actividades.actividades.dto;

public class DireccionRequest {

    private String nombreCalle;
    private Integer numeroCalle;
    private String nombreComuna;
    private Double longitud;
    private Double latitud;
    private String adicional;

    private DireccionRequest(Builder builder) {
        this.nombreCalle = builder.nombreCalle;
        this.numeroCalle = builder.numeroCalle;
        this.nombreComuna = builder.nombreComuna;
        this.longitud = builder.longitud;
        this.latitud = builder.latitud;
        this.adicional = builder.adicional;
    }

    public static class Builder {
        private String nombreCalle;
        private Integer numeroCalle;
        private String nombreComuna;
        private Double longitud;
        private Double latitud;
        private String adicional;

        public Builder nombreCalle(String nombreCalle) {
            this.nombreCalle = nombreCalle;
            return this;
        }

        public Builder numeroCalle(Integer numeroCalle) {
            this.numeroCalle = numeroCalle;
            return this;
        }

        public Builder nombreComuna(String nombreComuna) {
            this.nombreComuna = nombreComuna;
            return this;
        }

        public Builder longitud(Double longitud) {
            this.longitud = longitud;
            return this;
        }

        public Builder latitud(Double latitud) {
            this.latitud = latitud;
            return this;
        }

        public Builder adicional(String adicional) {
            this.adicional = adicional;
            return this;
        }

        public DireccionRequest build() {
            return new DireccionRequest(this);
        }
    }

    // Getters y setters
    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public Integer getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(Integer numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public String getNombreComuna() {
        return nombreComuna;
    }

    public void setNombreComuna(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
    }
}
