package com.alurachallenge.Foro.modules.usuario.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UsuarioRegistroReqRec(
        @NotBlank @Length(min=10 ,max = 100) String nombre,
        @NotBlank @Length(min=10) String contrasena,
        @NotBlank @Length(min=4 ,max = 100) @Email String correoElectronico,
        @NotBlank Long idPerfil
) {
}
