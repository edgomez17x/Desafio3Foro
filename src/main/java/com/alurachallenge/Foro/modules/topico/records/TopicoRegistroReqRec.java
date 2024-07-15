package com.alurachallenge.Foro.modules.topico.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicoRegistroReqRec(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotNull Long autor,
        @NotNull Long curso
) {
}
