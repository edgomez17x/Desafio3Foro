package com.alurachallenge.Foro.modules.topico.records;

import com.alurachallenge.Foro.modules.topico.Topico;

public record TopicoRegistroResRec(
        Long id,
        String titulo
) {
    public TopicoRegistroResRec(Topico topico) {
        this(topico.getId(), topico.getTitulo());
    }
}
