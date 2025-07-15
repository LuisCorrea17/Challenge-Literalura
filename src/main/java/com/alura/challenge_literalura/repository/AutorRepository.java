package com.alura.challenge_literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alura.challenge_literalura.model.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    boolean existsByNombre(String nombreAutor);

    Optional<Autor> findByNombre(String nombreAutor);

    @Query(value = "SELECT * FROM autores a WHERE a.fecha_nacimiento::INTEGER <= :año AND a.fecha_muerte::INTEGER > :año", nativeQuery = true)
    List<Autor> autoresVivos(int año);

    Optional<Autor> findByNombreContainsIgnoreCase(String autorBuscado);

}
