package com.alurachallenge.Foro.modules.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioLoginRec(
        @NotBlank @Email String correoElectronico,
        @NotBlank String contrasena
) {
}
