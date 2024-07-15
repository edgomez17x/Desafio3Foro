package com.alurachallenge.Foro.modules.usuario;

import com.alurachallenge.Foro.errores.ValidacionException;
import com.alurachallenge.Foro.modules.perfil.Perfil;
import com.alurachallenge.Foro.modules.perfil.PerfilRepository;
import com.alurachallenge.Foro.modules.usuario.records.UsuarioRegistroReqRec;
import com.alurachallenge.Foro.modules.usuario.records.UsuarioRegistroResRec;
import com.alurachallenge.Foro.modules.usuario.records.UsuarioRequestRec;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    public Page<Usuario> obtenerTodosLosUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Usuario buscarTopicoPorId(Long id) {
        if(usuarioRepository.existsById(id)){
            return usuarioRepository.getReferenceById(id);
        }
        throw new ValidacionException("El usuario buscado no existe");
    }

    public Usuario actualizarUsuarioPorId(Long id, UsuarioRequestRec urr) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            Usuario usuario = usuarioOptional.get();
            Optional<Perfil> perfilOptional = perfilRepository.findById(id);
            if(perfilOptional.isPresent()){
                usuario.actualizarUsuario(urr, perfilOptional.get());
                return usuarioRepository.save(usuario);
            }
            throw new ValidacionException("El perfil al que intentas actualizar no existe");
        }
        throw new ValidacionException("El usuario buscado no existe");
    }

    public void borrarUsuarioPorId(Long id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()){
            usuarioRepository.deleteById(id);
            return;
        }
        throw new ValidacionException("El Usuario que intentas borrar no existe");
    }

    public UsuarioRegistroResRec registrarUsuario(UsuarioRegistroReqRec urqr){
        UserDetails userDetails = usuarioRepository.findByCorreoElectronico(urqr.correoElectronico());
        if(userDetails != null){
            Optional<Perfil> perfilOptional = perfilRepository.findById(urqr.idPerfil());
            if(perfilOptional.isPresent()){
                Usuario usuario = new Usuario(urqr.nombre(), urqr.correoElectronico(),urqr.contrasena(), perfilOptional.get());
                usuario = usuarioRepository.save(usuario);
                return new UsuarioRegistroResRec(usuario.getId(), usuario.getNombre());
            }
            throw new ValidacionException("El perfil con el que intenta registrar el usuario no existe");
        }
        throw new ValidacionException("El correo que intenta registrar ya está siendo usado por otro usuario");
    }
}
