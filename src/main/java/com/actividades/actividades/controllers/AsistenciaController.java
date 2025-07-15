package com.actividades.actividades.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.actividades.actividades.dto.AsistenciaRequest;
import com.actividades.actividades.dto.AsistenciaResponse;
import com.actividades.actividades.dto.PromedioAsistenciaActividad;
import com.actividades.actividades.dto.PromedioAsistenciaResponse;
import com.actividades.actividades.services.interfaces.AsistenciaService;
import com.actividades.actividades.services.interfaces.PromedioAsistenciaService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/actividades/asistencia")
@CrossOrigin(origins = "https://dev.appx.cl/")
public class AsistenciaController {

    private final AsistenciaService asistenciaService;

    private final PromedioAsistenciaService promedioAsistenciaService;

    private static String keyValue = "Mensaje";
    private static String value = "Sin resultados";

    public AsistenciaController(AsistenciaService asistenciaService,
            PromedioAsistenciaService promedioAsistenciaService) {
        this.asistenciaService = asistenciaService;
        this.promedioAsistenciaService = promedioAsistenciaService;
    }

    @PostMapping
    public ResponseEntity<Object> createAsistencia(@RequestBody AsistenciaRequest request) {

        asistenciaService.createAsistencia(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(keyValue, "Asistencia creada correctamente"));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAsistenciaByActividadId(@PathVariable Long id) {

        List<AsistenciaResponse> list = asistenciaService.getAsistenciasByActividad(id);
        if (list.isEmpty()) {

            return ResponseEntity.status(HttpStatus.OK).body(Map.of(keyValue, value));

        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/promedio-rut")
    public ResponseEntity<Object> getPromedioAsistenciaByRutAndActividadId(@RequestParam Integer rut,
            @RequestParam Long idActividad) {

        PromedioAsistenciaResponse response = promedioAsistenciaService.promedioByRutAndActividad(rut, idActividad);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/promedio-actividad")
    public ResponseEntity<Object> getPromedioAsistenciadActividadId(@RequestParam Long idActividad) {

        List<PromedioAsistenciaActividad> response = promedioAsistenciaService.promedioByActividad(idActividad);
        if (response.isEmpty()) {

            return ResponseEntity.status(HttpStatus.OK).body(Map.of(keyValue, value));
        }

        return ResponseEntity.ok(response);

    }

    @GetMapping("/list")
    public ResponseEntity<Object> geAsistenciaByActividadAndFecha(@RequestParam Long idActividad,
            @RequestParam LocalDate fechaAsistencia) {

        List<AsistenciaResponse> response = asistenciaService.getAsistenciaByActividadIdAndFecha(idActividad,
                fechaAsistencia);
        if (response.isEmpty()) {

            return ResponseEntity.status(HttpStatus.OK).body(Map.of(keyValue, value));
        }

        return ResponseEntity.ok(response);

    }

}
