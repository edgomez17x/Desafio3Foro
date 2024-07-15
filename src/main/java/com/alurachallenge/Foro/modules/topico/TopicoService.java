package com.alurachallenge.Foro.modules.topico;

import com.alurachallenge.Foro.errores.ValidacionException;
import com.alurachallenge.Foro.modules.curso.Curso;
import com.alurachallenge.Foro.modules.curso.CursoRepository;
import com.alurachallenge.Foro.modules.topico.records.TopicoRegistroReqRec;
import com.alurachallenge.Foro.modules.topico.records.TopicoRegistroResRec;
import com.alurachallenge.Foro.modules.topico.validaciones.TopicoValidaciones;
import com.alurachallenge.Foro.modules.usuario.Usuario;
import com.alurachallenge.Foro.modules.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private List<TopicoValidaciones> topicoValidacionesList;

    public TopicoRegistroResRec registrarTopico(TopicoRegistroReqRec trqr){
        topicoValidacionesList.forEach(tvl -> tvl.validar(trqr));
        Usuario usuario = usuarioRepository.getReferenceById(trqr.autor());
        Curso curso = cursoRepository.getReferenceById(trqr.curso());
        Topico topico = new Topico(trqr, usuario, curso);
        topico = topicoRepository.save(topico);
        return new TopicoRegistroResRec(topico);
    }

    public Page<Topico> listarTopicos(Pageable pageable, String curso, Integer anio) {
        Page<Topico> topicoPage;
        if(curso!=null || anio!=null){
            if(anio!=null){
                LocalDateTime iniAnio = LocalDateTime.of(anio,1,1,0,0);
                LocalDateTime finAnio = LocalDateTime.of(anio,12,31,23,59);
                if(curso!=null){
                    return topicoRepository.findByCursoNombreAndFechaCreacionBetweenOrderByFechaCreacionAsc(pageable, curso, iniAnio, finAnio);
                }else{
                    return topicoRepository.findByFechaCreacionBetweenOrderByFechaCreacionAsc(pageable, iniAnio, finAnio);
                }
            }else{
                return topicoRepository.findByCursoNombreOrderByFechaCreacionAsc(pageable, curso);
            }
        } else{
            return topicoRepository.findAll(pageable);
        }
    }

    public Topico buscarTopicoPorId(Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if(topicoOptional.isPresent()){
            return topicoOptional.get();
        }
        throw new ValidacionException("El Topico buscado no existe");
    }

    public Topico actualizarTopicoPorId(Long id, TopicoRegistroReqRec trqr) {
        topicoValidacionesList.forEach(tvl -> tvl.validar(trqr));
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if(topicoOptional.isPresent()){
            Topico topico = topicoOptional.get();
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(trqr.autor());
            if (usuarioOptional.isPresent()){
                Optional<Curso> cursoOptional = cursoRepository.findById(trqr.curso());
                if(cursoOptional.isPresent()){
                    topico.actualizaInformacion(trqr, usuarioOptional.get(), cursoOptional.get());
                    return topicoRepository.save(topico);
                }
                throw new ValidacionException("El Curso que intenta usar no existe");
            }
            throw new ValidacionException("El Usuario que intenta usar no existe");
        }
        throw new ValidacionException("El Topico que intenta actualizar no existe");
    }

    public void borrarTopicoPorId(Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if(topicoOptional.isPresent()){
            topicoRepository.deleteById(id);
            return;
        }
        throw new ValidacionException("El Topico que intenta eliminar no existe");
    }
}
