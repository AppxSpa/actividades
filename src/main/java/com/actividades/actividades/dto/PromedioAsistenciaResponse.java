package com.actividades.actividades.dto;

public class PromedioAsistenciaResponse {

    private int diasAsitentica;
    private int diasInasistencia;
    private int diasTotales;
    private Double promedioAsistencia;

    public int getDiasAsitentica() {
        return diasAsitentica;
    }

    public void setDiasAsitentica(int diasAsitentica) {
        this.diasAsitentica = diasAsitentica;
    }

    public int getDiasInasistencia() {
        return diasInasistencia;
    }

    public void setDiasInasistencia(int diasInasistencia) {
        this.diasInasistencia = diasInasistencia;
    }

    public Double getPromedioAsistencia() {
        return promedioAsistencia;
    }

    public void setPromedioAsistencia(Double promedioAsistencia) {
        this.promedioAsistencia = promedioAsistencia;
    }

    public int getDiasTotales() {
        return diasTotales;
    }

    public void setDiasTotales(int diasTotales) {
        this.diasTotales = diasTotales;
    }

}
