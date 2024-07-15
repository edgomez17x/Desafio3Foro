package com.alurachallenge.Foro.modules.topico;

import com.alurachallenge.Foro.modules.curso.Curso;
import com.alurachallenge.Foro.modules.topico.records.TopicoRegistroReqRec;
import com.alurachallenge.Foro.modules.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name="fechacreacion")
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private TopicoStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuarios")
    private Usuario autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcursos")
    private Curso curso;

    public Topico() {
    }

    public Topico(TopicoRegistroReqRec trqr, Usuario usuario, Curso curso) {
        this.autor = usuario;
        this.mensaje = trqr.mensaje();
        this.curso = curso;
        this.titulo = trqr.titulo();
        this.fechaCreacion = LocalDateTime.now();
        this.status = TopicoStatus.NUEVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public TopicoStatus getStatus() {
        return status;
    }

    public void setStatus(TopicoStatus status) {
        this.status = status;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void actualizaInformacion(TopicoRegistroReqRec trqr, Usuario autor, Curso curso){
        setTitulo(trqr.titulo());
        setMensaje(trqr.mensaje());
        setCurso(curso);
        setAutor(autor);
    }
}
