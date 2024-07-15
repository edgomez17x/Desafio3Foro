package com.alurachallenge.Foro.modules.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Boolean existsByTituloAndMensaje(String titulo, String mensaje);

    Page<Topico> findByCursoNombreAndFechaCreacionBetweenOrderByFechaCreacionAsc(Pageable pageable, String curso, LocalDateTime iniAnio, LocalDateTime finAnio);

    Page<Topico> findByFechaCreacionBetweenOrderByFechaCreacionAsc(Pageable pageable, LocalDateTime iniAnio, LocalDateTime finAnio);

    Page<Topico> findByCursoNombreOrderByFechaCreacionAsc(Pageable pageable, String curso);
}
