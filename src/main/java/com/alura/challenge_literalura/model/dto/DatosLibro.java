package com.alura.challenge_literalura.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") double numeroDescargas) {

    @Override
    public String toString() {
        return String.format("""

                    Título: %s
                    Autor: %s
                    Idioma: %s
                    Descargas: %.0f
                    
                """, titulo, autor.get(0).name(), idioma.get(0), numeroDescargas);
    }

}
