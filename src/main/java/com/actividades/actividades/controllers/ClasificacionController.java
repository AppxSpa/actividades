package com.actividades.actividades.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.actividades.actividades.dto.ClasificacionDto;
import com.actividades.actividades.dto.ClasificacionRequest;
import com.actividades.actividades.dto.SubclasificacionRequest;
import com.actividades.actividades.entities.Clasificacion;
import com.actividades.actividades.entities.Subclasificacion;
import com.actividades.actividades.services.interfaces.ClasificacionService;
import com.actividades.actividades.services.interfaces.FotoClasificacionService;
import com.actividades.actividades.services.interfaces.SubClasificacionService;
import java.util.Map;

@RestController
@RequestMapping("/api/actividades/clasificaciones")
@CrossOrigin(origins = "https://dev.appx.cl/")
public class ClasificacionController {

    private final ClasificacionService clasificacionService;

    private final SubClasificacionService subclasificacionService;

    private final FotoClasificacionService fotoClasificacionService;

    public ClasificacionController(ClasificacionService clasificacionService,
            SubClasificacionService subclasificacionService, FotoClasificacionService fotoClasificacionService) {
        this.clasificacionService = clasificacionService;
        this.subclasificacionService = subclasificacionService;
        this.fotoClasificacionService = fotoClasificacionService;
    }

    @PostMapping
    public ResponseEntity<Clasificacion> crearClasificacion(@RequestBody ClasificacionRequest request) {
        return ResponseEntity.ok(clasificacionService.createClasificacion(request));
    }

    @PostMapping("/sub")
    public ResponseEntity<Subclasificacion> crearSubclasificacion(@RequestBody SubclasificacionRequest request) {
        return ResponseEntity.ok(subclasificacionService.createSubClasificacion(request));
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getClasificaciones() {
        List<ClasificacionDto> list = clasificacionService.getClasificacionesList();

        if (list.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "No hay clasificaciones"));
        }

        return ResponseEntity.ok(list);

    }

    @PostMapping(value = "/upload-foto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFoto(
            @RequestParam Long iClasificacion,
            @RequestParam(required = true) List<MultipartFile> files) {

        try {
            fotoClasificacionService.uploadFoto(iClasificacion, files);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Foto agregada exitosamente"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
