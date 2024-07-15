package com.alurachallenge.Foro.modules.usuario.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UsuarioRequestRec(
        @Length(min=10 ,max = 100) String nombre,
        @Length(min=10) String contrasena,
        @Length(min=4 ,max = 100) @Email String correoElectronico,
        Long idPerfil) {
}
