package com.alurachallenge.Foro.controllers;

import com.alurachallenge.Foro.modules.topico.Topico;
import com.alurachallenge.Foro.modules.topico.records.TopicoResponseRec;
import com.alurachallenge.Foro.modules.usuario.Usuario;
import com.alurachallenge.Foro.modules.usuario.UsuarioRepository;
import com.alurachallenge.Foro.modules.usuario.UsuarioService;
import com.alurachallenge.Foro.modules.usuario.records.UsuarioRegistroReqRec;
import com.alurachallenge.Foro.modules.usuario.records.UsuarioRegistroResRec;
import com.alurachallenge.Foro.modules.usuario.records.UsuarioRequestRec;
import com.alurachallenge.Foro.modules.usuario.records.UsuarioResponseRec;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseRec>> obtenerTodosLosUsuarios(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios(pageable).map(UsuarioResponseRec::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseRec> obtenerUsuarioPorId(@PathVariable Long id){
        Usuario usuario = usuarioService.buscarTopicoPorId(id);
        return ResponseEntity.ok(new UsuarioResponseRec(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseRec> actualizarUsuarioPorId(@PathVariable Long id, @RequestBody @Valid UsuarioRequestRec urr){
        Usuario usuario = usuarioService.actualizarUsuarioPorId(id, urr);
        return ResponseEntity.ok(new UsuarioResponseRec(usuario));
    }

    @PostMapping
    public ResponseEntity<UsuarioRegistroResRec> registrarUsuario(@RequestBody @Valid UsuarioRegistroReqRec urqr, UriComponentsBuilder uriComponentsBuilder){
        UsuarioRegistroResRec ursr = usuarioService.registrarUsuario(urqr);
        URI uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(ursr.id()).toUri();
        return ResponseEntity.created(uri).body(ursr);
    }

}
