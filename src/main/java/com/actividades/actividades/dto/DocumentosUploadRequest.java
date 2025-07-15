package com.actividades.actividades.dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentosUploadRequest {

    private Long tipoDoc;
    private MultipartFile file;

    public Long getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(Long tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
