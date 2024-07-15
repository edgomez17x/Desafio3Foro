package com.alurachallenge.Foro.modules.topico.records;

import com.alurachallenge.Foro.modules.topico.Topico;
import com.alurachallenge.Foro.modules.topico.TopicoStatus;

import java.time.LocalDateTime;

public record TopicoResponseRec(
        String titulo,
        LocalDateTime fechaCreacion,
        TopicoStatus status,
        Long autor,
        Long curso
) {
    public TopicoResponseRec(Topico topico){
        this(topico.getTitulo(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getId(),
                topico.getCurso().getId());
    }
}
