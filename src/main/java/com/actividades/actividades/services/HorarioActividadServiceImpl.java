package com.actividades.actividades.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.actividades.actividades.dto.HorarioDTO;
import com.actividades.actividades.entities.Actividad;
import com.actividades.actividades.entities.DiaActividad.DiaSemana;
import com.actividades.actividades.exceptions.HorarioExceptions;
import com.actividades.actividades.entities.HorarioActividad;
import com.actividades.actividades.services.interfaces.HorarioActividadService;

@Service
public class HorarioActividadServiceImpl implements HorarioActividadService {

 

    @Override
    public Set<HorarioActividad> convertHorarios(List<HorarioDTO> horariosDTO, Actividad actividad) {
        Set<HorarioActividad> horarios = new HashSet<>();

        for (HorarioDTO dto : horariosDTO) {
            DiaSemana diaEnum;
            try {
                diaEnum = DiaSemana.valueOf(dto.getDia().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new HorarioExceptions("Día inválido: " + dto.getDia());
            }

            HorarioActividad horario = new HorarioActividad();
            horario.setDiaSemana(diaEnum);
            horario.setHoraInicio(dto.getHoraInicio());
            horario.setHoraFin(dto.getHoraTermino());
            horario.setActividad(actividad);

            horarios.add(horario);
        }

        return horarios;
    }
    }


