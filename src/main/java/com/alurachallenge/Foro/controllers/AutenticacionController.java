package com.alurachallenge.Foro.controllers;

import com.alurachallenge.Foro.modules.usuario.Usuario;
import com.alurachallenge.Foro.modules.usuario.UsuarioLoginRec;
import com.alurachallenge.Foro.security.JWTTokenRec;
import com.alurachallenge.Foro.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JWTTokenRec> autenticacionUsuario(@RequestBody @Valid UsuarioLoginRec ulr){
        Authentication token = new UsernamePasswordAuthenticationToken(ulr.correoElectronico(), ulr.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(token);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new JWTTokenRec(JWTtoken));
    }

}
