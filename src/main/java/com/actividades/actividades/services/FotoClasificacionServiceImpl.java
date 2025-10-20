package com.actividades.actividades.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.actividades.actividades.entities.Clasificacion;
import com.actividades.actividades.entities.FotoClasificacion;
import com.actividades.actividades.repositories.ClasificacionRepository;
import com.actividades.actividades.services.interfaces.ArchivoService;
import com.actividades.actividades.services.interfaces.FotoClasificacionService;
import com.actividades.actividades.utils.RepositoryUtils;

@Service
public class FotoClasificacionServiceImpl implements FotoClasificacionService {

    private final ClasificacionRepository clasificacionRepository;
    private final ArchivoService archivoService;

    public FotoClasificacionServiceImpl(ClasificacionRepository clasificacionRepository,
            ArchivoService archivoService) {
        this.clasificacionRepository = clasificacionRepository;
        this.archivoService = archivoService;
    }

    @Override
    public void uploadFoto(Long idClasificacion, List<MultipartFile> files) throws IOException {

        Clasificacion clasificacion = getClasificacionById(idClasificacion);

        List<FotoClasificacion> fotos = Optional.ofNullable(clasificacion.getFotos())
                .orElseGet(ArrayList::new);

        for (MultipartFile file : files) {
            String nombreGuardado = archivoService.guardarArchivo(file);
            FotoClasificacion foto = new FotoClasificacion(idClasificacion, nombreGuardado);
            fotos.add(foto);
        }

    }

    private Clasificacion getClasificacionById(Long idClasificacion) {
        return RepositoryUtils.findOrThrow(clasificacionRepository.findById(idClasificacion),
                String.format("No existe la actividad con id %s", idClasificacion));

    }

}
