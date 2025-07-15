package com.actividades.actividades.services.interfaces;

import java.io.IOException;

import com.actividades.actividades.dto.DocumentosProfesorRequest;

public interface UploadDocumentosProfesorService {

    void uploadDocumento(DocumentosProfesorRequest request) throws IOException;

}
