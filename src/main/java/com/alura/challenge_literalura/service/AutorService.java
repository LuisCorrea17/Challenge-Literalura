package com.alura.challenge_literalura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.challenge_literalura.model.entity.Autor;
import com.alura.challenge_literalura.repository.AutorRepository;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> listarAutoresRegistrados() {
        return autorRepository.findAll();
    }

    public String invertirNombreAutor(String nombreOriginal) {
        String[] partes = nombreOriginal.split(",", 2);
        String apellido = partes[0].trim();
        String nombre = partes[1].trim();

        return nombre + " " + apellido;
    }

    public boolean verificarAutor(String nombreAutor) {
        return autorRepository.existsByNombre(nombreAutor);
    }

    public List<Autor> listarAutoresVivos(int año) {
        return autorRepository.autoresVivos(año);
    }

    public Optional<Autor> buscarAutorRegistradoPorNombre(String autorBuscado) {
        var autor = autorRepository.findByNombreContainsIgnoreCase(autorBuscado);
        return autor;
    }

}
