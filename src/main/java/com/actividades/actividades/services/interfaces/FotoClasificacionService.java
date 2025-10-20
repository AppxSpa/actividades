package com.actividades.actividades.services.interfaces;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FotoClasificacionService {

    void uploadFoto(Long idClasificacion, List<MultipartFile> files) throws IOException;

}
