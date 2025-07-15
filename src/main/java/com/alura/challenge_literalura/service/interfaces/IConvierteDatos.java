package com.alura.challenge_literalura.service.interfaces;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
