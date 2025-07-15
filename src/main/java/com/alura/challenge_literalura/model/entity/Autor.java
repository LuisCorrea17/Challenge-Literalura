package com.alura.challenge_literalura.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.alura.challenge_literalura.model.dto.DatosAutor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "autores")
@Entity(name = "Autor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String fechaNacimiento;
    private String fechaMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor(DatosAutor datosAutor, String nombre) {
        this.nombre = nombre;
        this.fechaNacimiento = datosAutor.fechaNacimiento().toString();
        this.fechaMuerte = datosAutor.fechaMuerte().toString();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
        libro.setAutor(this);
    }

    @Override
    public String toString() {
        StringBuilder librosTitulo = new StringBuilder();
        for (Libro libro : libros) {
            librosTitulo.append("   -").append(libro.getTitulo()).append("\n");
        }
        return String.format("""

                Nombre: %s
                Fecha de Nacimiento: %s
                Fecha de muerte: %s
                Libros: 
                %s
                -------------------------
                """, nombre, fechaNacimiento, fechaMuerte, librosTitulo.toString());
    }

}
