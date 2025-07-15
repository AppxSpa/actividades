package com.actividades.actividades.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actividades.actividades.dto.SubClasifacionReponse;
import com.actividades.actividades.services.interfaces.SubClasificacionService;

@RestController
@RequestMapping("/api/actividades/subclasificacion")
@CrossOrigin(origins = "https://dev.appx.cl/")
public class SubclasificacionController {

    private final SubClasificacionService subClasificacionService;

    public SubclasificacionController(SubClasificacionService subClasificacionService) {
        this.subClasificacionService = subClasificacionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSubclasificacionById(@PathVariable Long id) {

        return ResponseEntity.ok(subClasificacionService.getSubclaficacionById(id));

    }

    @GetMapping("/list")
    public ResponseEntity<Object> getAllSubclasificacion() {

        List<SubClasifacionReponse> subclasificaciones = subClasificacionService.getAllSubclasificaciones();

        if (subclasificaciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "No se encontraron resultados"));
        }

        return ResponseEntity.ok(subclasificaciones);

    }

}
