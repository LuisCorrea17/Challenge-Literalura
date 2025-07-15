package com.alura.challenge_literalura.service.api;

import org.springframework.stereotype.Service;

import com.alura.challenge_literalura.service.interfaces.IConvierteDatos;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ConvierteDatos implements IConvierteDatos{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
