package com.alura.challenge_literalura.controller;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alura.challenge_literalura.model.dto.DatosLibro;
import com.alura.challenge_literalura.model.entity.Libro;
import com.alura.challenge_literalura.service.LibroService;

@Controller
public class LibroController {

    @Autowired
    private LibroService libroService;

    public Optional<DatosLibro> buscarLibroPorTitulo(String tituloLibro) {
        return libroService.buscarLibro(tituloLibro);
    }

    public List<Libro> listarLibrosRegistrados() {
        return libroService.listarLibrosRegistrados();
    }

    public List<Libro> listarLibrosPorIdioma(String idiomaSeleccionado) {
        return libroService.listarLibrosPorIdioma(idiomaSeleccionado);
    }

    public List<Libro> top10LibrosMasDescargados() {
        return libroService.top10LibrosMasDescargados();
    }

    public DoubleSummaryStatistics obtenerEstadisticas() {
        return libroService.obtenerEstadisticas();
    }

}
