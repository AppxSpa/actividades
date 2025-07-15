package com.actividades.actividades.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.actividades.actividades.config.ApiProperties;
import com.actividades.actividades.dto.CreateDireccionResponse;
import com.actividades.actividades.dto.DireccionRequest;
import com.actividades.actividades.services.interfaces.ApiDireccionService;

@Service
public class ApiDireccionServiceImpl implements ApiDireccionService {

    private final WebClient webClient;

    public ApiDireccionServiceImpl(WebClient.Builder webClientBuilder, ApiProperties apiProperties) {
        this.webClient = webClientBuilder.baseUrl(apiProperties.getDireccionUrl()).build();
    }

    @Override
    public CreateDireccionResponse createDireccion(DireccionRequest request) {

        return webClient.post()
                .uri("/api/direcciones")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(CreateDireccionResponse.class)
                .block();

    }

}
