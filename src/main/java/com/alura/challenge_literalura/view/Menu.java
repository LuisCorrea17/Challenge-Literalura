package com.alura.challenge_literalura.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.challenge_literalura.controller.AutorController;
import com.alura.challenge_literalura.controller.LibroController;

@Component
public class Menu {

    @Autowired
    private LibroController libroController;

    @Autowired
    private AutorController autorController;

    private Scanner teclado = new Scanner(System.in);

    public void mostrarMenu() {
        boolean menu = true;
        while (menu) {
            System.out.println("---------- Bienvenido ----------");
            System.out.println("\nElija una opcion:\n");
            var listaOpciones = """
                    1. Buscar libro por titulo
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un determinado año
                    5. Listar libros por idioma
                    6. Top 10 libros mas descargados
                    0. Salir
                    """;
            System.out.println(listaOpciones);
            try {
                var opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
                    case 1:
                        buscarLibroPorTitulo();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivos();
                        break;
                    case 5:
                        listarLibrosPorIdioma();
                        break;
                    case 6:
                        top10LibrosMasDescargados();
                        break;
                    case 0:
                        menu = false;
                        break;
                    default:
                        System.out.println("Opcion no valida, por favor escoja una de la opciones listadas");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresa un numero valido");
                teclado.nextLine();
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        var libroBuscado = libroController.buscarLibroPorTitulo(tituloLibro);
        if (libroBuscado.isPresent()) {
            System.out.println("\n----- Libro encontrado -----\n");
            System.out.println(libroBuscado.get());
        } else {
            System.out.println("\n----- Libro no encontrado -----\n");
        }
    }

    private void listarLibrosRegistrados() {
        var listaLibros = libroController.listarLibrosRegistrados();
        System.out.println("\n------- Libros Registrados -------");
        listaLibros.forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        var listaAutores = autorController.listarAutoresRegistrados();
        System.out.println("\n------- Autores Registrados -------");
        listaAutores.forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año en el que desea buscar autores vivos");
        try {
            var año = teclado.nextInt();
            teclado.nextLine();
            var listaAutores = autorController.listarAutoresVivos(año);
            if (listaAutores.isEmpty()) {
                System.out.println("\n****No hay autores vivos en ese año****\n");
            } else {
                System.out.println("\n------- Autores Vivos en " + año + " -------");
                listaAutores.forEach(System.out::println);
            }
        } catch (InputMismatchException e) {
            System.out.println("\nIngresa un año válido (número entero)\n");
            teclado.nextLine();
            listarAutoresVivos();
        }
    }

    private void listarLibrosPorIdioma() {
        var listaIdiomas = """
                Selecciona el idioma por el que deseas buscar:

                es - Español
                en - Ingles
                fr - Frances
                pt - Portugues
                """;
        System.out.println(listaIdiomas);
        var idiomaSeleccionado = teclado.nextLine();
        if (!idiomaSeleccionado.equals("es") && !idiomaSeleccionado.equals("en") && !idiomaSeleccionado.equals("fr")
                && !idiomaSeleccionado.equals("pt")) {
            System.out.println("Idioma no válido, intenta de nuevo");
        }
        var libros = libroController.listarLibrosPorIdioma(idiomaSeleccionado);
        if (libros.isEmpty()) {
            System.out.println("\n****No hay libros registrados en ese idioma****\n");
        } else {
            libros.forEach(System.out::println);
        }
    }

    private void top10LibrosMasDescargados() {
        var top10 = libroController.top10LibrosMasDescargados();
        if (!top10.isEmpty()) {
            top10.forEach(System.out::println);
        } else {
            System.out.println("Aun no hay libros registrados");
        }
    }
}
