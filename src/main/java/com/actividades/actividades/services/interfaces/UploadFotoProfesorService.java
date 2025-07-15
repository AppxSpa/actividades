package com.actividades.actividades.services.interfaces;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;
public interface UploadFotoProfesorService {

    void uploadFoto(Integer rut, MultipartFile file) throws IOException;

}
