package com.actividades.actividades.services.interfaces;

import com.actividades.actividades.dto.CreateDireccionResponse;
import com.actividades.actividades.dto.DireccionRequest;

public interface ApiDireccionService {

    CreateDireccionResponse createDireccion(DireccionRequest request);

}
