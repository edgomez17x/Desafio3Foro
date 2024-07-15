package com.alurachallenge.Foro.modules.curso;

import jakarta.persistence.*;

@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private CursoCategoria categoria;

    public Curso(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CursoCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(CursoCategoria categoria) {
        this.categoria = categoria;
    }
}
