package com.actividades.actividades.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.FotoActividad;
import com.actividades.actividades.repositories.ActividadRepository;
import com.actividades.actividades.services.interfaces.ArchivoService;
import com.actividades.actividades.services.interfaces.FotoActividaService;
import com.actividades.actividades.utils.RepositoryUtils;

@Service
public class FotoActividadServiceImpl implements FotoActividaService {

    private final ActividadRepository actividadRepository;

    private final ArchivoService archivoService;

    public FotoActividadServiceImpl(ActividadRepository actividadRepository, ArchivoService archivoService) {
        this.actividadRepository = actividadRepository;
        this.archivoService = archivoService;
    }

    @Override
    @Transactional
    public void uploadFoto(Long idActividad, List<MultipartFile> files) throws IOException {
        Actividad actividad = getActividadById(idActividad);

        List<FotoActividad> fotos = Optional.ofNullable(actividad.getFotos())
                .orElseGet(ArrayList::new);

        for (MultipartFile file : files) {
            String nombreGuardado = archivoService.guardarArchivo(file);
            FotoActividad foto = new FotoActividad(nombreGuardado, actividad);
            fotos.add(foto);
        }

        actividad.setFotos(fotos);
        actividadRepository.save(actividad);

    }

    private Actividad getActividadById(Long idActividad) {
        return RepositoryUtils.findOrThrow(actividadRepository.findById(idActividad),
                String.format("No existe la actividad con id %s", idActividad));

    }

}
