package com.alura.challenge_literalura.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.challenge_literalura.model.dto.Datos;
import com.alura.challenge_literalura.model.dto.DatosLibro;
import com.alura.challenge_literalura.model.entity.Autor;
import com.alura.challenge_literalura.model.entity.Libro;
import com.alura.challenge_literalura.repository.AutorRepository;
import com.alura.challenge_literalura.repository.LibroRepository;
import com.alura.challenge_literalura.service.api.ConsumoAPI;
import com.alura.challenge_literalura.service.api.ConvierteDatos;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

     @Autowired
    private ConsumoAPI consumoAPI;
    
    @Autowired
    private ConvierteDatos conversor;

    @Autowired
    private AutorService autorService;

    private static final String URL_BASE = "https://gutendex.com/books/";

    public Optional<DatosLibro> buscarLibro(String tituloLibro) {
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        var resultado = datosBusqueda.libros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (resultado.isPresent()) {
            guardarLibro(resultado.get());
        }
        return resultado;
        
    }

    public void guardarLibro(DatosLibro resultadoLibro) {
        var nombreAutor = autorService.invertirNombreAutor(resultadoLibro.autor().get(0).name());
        Autor autor = autorRepository.findByNombre(nombreAutor).orElseGet(() -> {
            Autor nuevo = new Autor(resultadoLibro.autor().get(0), nombreAutor);
            return autorRepository.save(nuevo);
        });
        if (libroRepository.existsByTitulo(resultadoLibro.titulo())) {
            System.out.println("\n****El libro ya se encuentra registrado****\n");
            return;
        }
        Libro libro = new Libro(resultadoLibro, autor);
        autor.agregarLibro(libro);
        autorRepository.save(autor);
        libroRepository.save(libro);
    }

    public List<Libro> listarLibrosRegistrados() {
        return libroRepository.findAll();
    }

    public List<Libro> listarLibrosPorIdioma(String idiomaSeleccionado) {
        var listaLibrosPorIdioma = libroRepository.findByIdiomaContaining(idiomaSeleccionado);
        return listaLibrosPorIdioma.stream()
            .sorted(Comparator.comparing(Libro::getTitulo))
            .toList();
    }

    public List<Libro> top10LibrosMasDescargados() {
        return libroRepository.top10Libros();
    }
}
