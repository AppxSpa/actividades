package com.actividades.actividades.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actividades.actividades.dto.ClasificacionRequest;
import com.actividades.actividades.dto.SubclasificacionRequest;
import com.actividades.actividades.entities.Clasificacion;
import com.actividades.actividades.entities.Subclasificacion;
import com.actividades.actividades.services.interfaces.ClasificacionService;
import com.actividades.actividades.services.interfaces.SubClasificacionService;

@RestController
@RequestMapping("/api/actividades/clasificaciones")
@CrossOrigin(origins = "https://dev.appx.cl/")
public class ClasificacionController {

    private final ClasificacionService clasificacionService;

    private final SubClasificacionService subclasificacionService;

    public ClasificacionController(ClasificacionService clasificacionService,
            SubClasificacionService subclasificacionService) {
        this.clasificacionService = clasificacionService;
        this.subclasificacionService = subclasificacionService;
    }

    @PostMapping
    public ResponseEntity<Clasificacion> crearClasificacion(@RequestBody ClasificacionRequest request) {
        return ResponseEntity.ok(clasificacionService.createClasificacion(request));
    }

    @PostMapping("/sub")
    public ResponseEntity<Subclasificacion> crearSubclasificacion(@RequestBody SubclasificacionRequest request) {
        return ResponseEntity.ok(subclasificacionService.createSubClasificacion(request));
    }

}
