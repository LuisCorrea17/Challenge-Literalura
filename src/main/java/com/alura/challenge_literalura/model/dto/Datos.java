package com.alura.challenge_literalura.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
    @JsonAlias("results") List<DatosLibro> libros
) {}
