package com.alura.challenge_literalura.model.entity;

import com.alura.challenge_literalura.model.dto.DatosLibro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "libros")
@Entity(name = "Libro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne
    private Autor autor;
    private String idioma;
    private double numeroDescargas;

    public Libro(DatosLibro resultadoLibro, Autor autor) {
        this.titulo = resultadoLibro.titulo();
        this.autor = autor;
        this.idioma = resultadoLibro.idioma().get(0);
        this.numeroDescargas = resultadoLibro.numeroDescargas();
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return String.format("""
                
                Titulo: %s  
                Autor: %s  
                Idioma: %s  
                Numero de descargas: %.2f
                -------------------------
                """, titulo, autor.getNombre(), idioma, numeroDescargas);
    }

    
}
