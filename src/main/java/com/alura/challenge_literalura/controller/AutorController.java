package com.alura.challenge_literalura.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alura.challenge_literalura.model.entity.Autor;
import com.alura.challenge_literalura.service.AutorService;

@Controller
public class AutorController {

    @Autowired
    private AutorService autorService;

    public List<Autor> listarAutoresRegistrados() {
        return autorService.listarAutoresRegistrados();
    }

    public List<Autor> listarAutoresVivos(int año) {
        return autorService.listarAutoresVivos(año);
    }

    public Optional<Autor> buscarAutorRegistradoPorNombre(String autorBuscado) {
        return autorService.buscarAutorRegistradoPorNombre(autorBuscado); 
    }
}
