package com.alura.challenge_literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alura.challenge_literalura.model.entity.Autor;
import com.alura.challenge_literalura.model.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

    Optional<Libro> findByTituloAndAutorAndIdioma(String titulo, Autor autor, String idioma);

    List<Libro> findByIdiomaContaining(String idiomaSeleccionado);

    @Query("SELECT l FROM Libro l ORDER BY l.numeroDescargas DESC LIMIT 10")
    List<Libro> top10Libros();

}
