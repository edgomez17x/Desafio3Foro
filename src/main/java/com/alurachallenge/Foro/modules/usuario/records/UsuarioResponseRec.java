package com.alurachallenge.Foro.modules.usuario.records;

import com.alurachallenge.Foro.modules.perfil.Perfil;
import com.alurachallenge.Foro.modules.usuario.Usuario;
import jakarta.persistence.*;

public record UsuarioResponseRec(
        Long id,
        String nombre,
        String correoElectronico,
        Perfil perfil
) {
    public UsuarioResponseRec(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getUsername(), usuario.getPerfil());
    }
}
