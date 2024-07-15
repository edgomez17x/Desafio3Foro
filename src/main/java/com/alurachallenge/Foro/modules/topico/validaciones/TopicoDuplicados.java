package com.alurachallenge.Foro.modules.topico.validaciones;

import com.alurachallenge.Foro.errores.ValidacionException;
import com.alurachallenge.Foro.modules.topico.records.TopicoRegistroReqRec;
import com.alurachallenge.Foro.modules.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoDuplicados implements TopicoValidaciones{
    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(TopicoRegistroReqRec trqr) {
        Boolean duplicado = topicoRepository.existsByTituloAndMensaje(trqr.titulo(), trqr.mensaje());
        if(duplicado){
            throw new ValidacionException("El registro ya existe, no se puede duplicar topicos");
        }
    }
}
