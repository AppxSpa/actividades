package com.actividades.actividades.services;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.actividades.actividades.config.ApiProperties;
import com.actividades.actividades.dto.DireccionResponse;
import com.actividades.actividades.services.interfaces.ApiGetDireccionService;

import reactor.core.publisher.Mono;

@Service
public class ApiGetDireccionServiceImpl implements ApiGetDireccionService    {

    
    private final WebClient webClientDireccion;

    public ApiGetDireccionServiceImpl(WebClient.Builder webClientBuilder, ApiProperties apiProperties) {
        this.webClientDireccion = webClientBuilder.baseUrl(apiProperties.getDireccionUrl()).build();
    }

    @Override
    public DireccionResponse getDireccion(Long dirId) {
            return webClientDireccion.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/direcciones")
                            .queryParam("id", dirId)
                            .build())
                    .retrieve()
                    .bodyToMono(DireccionResponse.class)
                    .onErrorResume(e -> Mono.empty())
                    .block();
        }
    }


