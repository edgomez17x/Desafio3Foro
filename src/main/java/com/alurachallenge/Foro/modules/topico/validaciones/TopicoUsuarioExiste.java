package com.alurachallenge.Foro.modules.topico.validaciones;

import com.alurachallenge.Foro.errores.ValidacionException;
import com.alurachallenge.Foro.modules.topico.records.TopicoRegistroReqRec;
import com.alurachallenge.Foro.modules.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoUsuarioExiste implements TopicoValidaciones{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void validar(TopicoRegistroReqRec trqr) {
        Boolean exists = usuarioRepository.existsById(trqr.autor());
        if(!exists){
            throw new ValidacionException("El autor no existe en la base de datos");
        }
    }
}