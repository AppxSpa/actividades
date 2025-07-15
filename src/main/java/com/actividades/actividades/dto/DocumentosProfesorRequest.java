package com.actividades.actividades.dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentosProfesorRequest {

    private Integer rutProfesor;
    private MultipartFile file;
    private Long tipoDoc;

    public DocumentosProfesorRequest() {
    }

    public DocumentosProfesorRequest(Integer rutProfesor, MultipartFile file, Long tipoDoc) {
        this.rutProfesor = rutProfesor;
        this.file = file;
        this.tipoDoc = tipoDoc;
    }

    public Integer getRutProfesor() {
        return rutProfesor;
    }

    public void setRutProfesor(Integer rutProfesor) {
        this.rutProfesor = rutProfesor;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(Long tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

}
