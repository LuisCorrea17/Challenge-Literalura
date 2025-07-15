package com.alura.challenge_literalura.model.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
    @JsonAlias("name") String name,
    @JsonAlias("birth_year") String fechaNacimiento,
    @JsonAlias("death_year") String fechaMuerte
) {}
