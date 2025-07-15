package com.actividades.actividades.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.FotoActividad;
import com.actividades.actividades.repositories.ActividadRepository;
import com.actividades.actividades.repositories.FotoActividadRepository;
import com.actividades.actividades.services.interfaces.ArchivoService;
import com.actividades.actividades.services.interfaces.FotoActividaService;
import com.actividades.actividades.utils.RepositoryUtils;

@Service
public class FotoActividadServiceImpl implements FotoActividaService {

    private final ActividadRepository actividadRepository;

    private final ArchivoService archivoService;

    private final FotoActividadRepository fotoActividadRepository;

    public FotoActividadServiceImpl(ActividadRepository actividadRepository, ArchivoService archivoService,
            FotoActividadRepository fotoActividadRepository) {
        this.actividadRepository = actividadRepository;
        this.archivoService = archivoService;
        this.fotoActividadRepository = fotoActividadRepository;
    }

    @Override
    public void uploadFoto(Long idActividad, MultipartFile file) throws IOException {

        Actividad actividad = getActividadById(idActividad);

        String nombreGuardado = archivoService.guardarArchivo(file);

        fotoActividadRepository.save(new FotoActividad(nombreGuardado, actividad));

    }

    private Actividad getActividadById(Long idActividad) {
        return RepositoryUtils.findOrThrow(actividadRepository.findById(idActividad),
                String.format("No existe la actividad con id %s", idActividad));

    }

}
