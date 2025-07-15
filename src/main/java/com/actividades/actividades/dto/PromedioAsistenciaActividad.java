package com.actividades.actividades.dto;

public class PromedioAsistenciaActividad {

    private Integer rut;
    private String vrut;
    private String nombres;
    private String paterno;
    private String materno;
    private PromedioAsistenciaResponse promedios;

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

    public PromedioAsistenciaResponse getPromedios() {
        return promedios;
    }

    public void setPromedios(PromedioAsistenciaResponse promedios) {
        this.promedios = promedios;
    }

}
