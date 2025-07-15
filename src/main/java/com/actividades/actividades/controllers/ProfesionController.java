package com.actividades.actividades.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actividades.actividades.dto.ProfesionResponse;
import com.actividades.actividades.entities.Profesion;
import com.actividades.actividades.services.interfaces.ProfesionService;

@RestController
@RequestMapping("/api/actividades/profesion")
@CrossOrigin(origins = "https://dev.appx.cl/")
public class ProfesionController {

    private final ProfesionService profesionService;

    public ProfesionController(ProfesionService profesionService) {
        this.profesionService = profesionService;
    }

    @PostMapping
    public ResponseEntity<Object> createProfesion(@RequestBody Profesion request) {
        Profesion profesion = profesionService.createProfesion(request.getNombreProfesion());
        return ResponseEntity.status(HttpStatus.CREATED).body(profesion);
    }

    @GetMapping
    public ResponseEntity<Object> getProfesiones() {
        List<ProfesionResponse> list = profesionService.getProfesiones();

        if (list.isEmpty()) {

            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "La Lista no tiene resultados"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

}
