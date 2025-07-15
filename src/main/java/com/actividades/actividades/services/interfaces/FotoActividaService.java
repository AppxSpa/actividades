package com.actividades.actividades.services.interfaces;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FotoActividaService {

    void uploadFoto(Long idActividad, MultipartFile file) throws IOException;

}
