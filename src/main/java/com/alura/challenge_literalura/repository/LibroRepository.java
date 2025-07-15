package com.alura.challenge_literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.challenge_literalura.model.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

    boolean existsByTitulo(String titulo);

    List<Libro> findByIdiomaContaining(String idiomaSeleccionado);

}
