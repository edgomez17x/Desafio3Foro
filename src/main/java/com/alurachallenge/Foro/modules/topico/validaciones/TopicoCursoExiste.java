package com.alurachallenge.Foro.modules.topico.validaciones;

import com.alurachallenge.Foro.errores.ValidacionException;
import com.alurachallenge.Foro.modules.curso.CursoRepository;
import com.alurachallenge.Foro.modules.topico.records.TopicoRegistroReqRec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoCursoExiste implements TopicoValidaciones{
    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public void validar(TopicoRegistroReqRec trqr) {
        Boolean exists = cursoRepository.existsById(trqr.curso());
        if(!exists){
            throw new ValidacionException("El curso no existe en la base de datos");
        }
    }
}
