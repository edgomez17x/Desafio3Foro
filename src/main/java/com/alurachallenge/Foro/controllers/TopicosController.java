package com.alurachallenge.Foro.controllers;

import com.alurachallenge.Foro.modules.topico.Topico;
import com.alurachallenge.Foro.modules.topico.records.TopicoRequestRec;
import com.alurachallenge.Foro.modules.topico.records.TopicoResponseRec;
import com.alurachallenge.Foro.modules.topico.records.TopicoRegistroReqRec;
import com.alurachallenge.Foro.modules.topico.records.TopicoRegistroResRec;
import com.alurachallenge.Foro.modules.topico.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<TopicoRegistroResRec> registrarTopico(@RequestBody @Valid TopicoRegistroReqRec trqr, UriComponentsBuilder uriComponentsBuilder){
        TopicoRegistroResRec trsr = topicoService.registrarTopico(trqr);
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(trsr.id()).toUri();
        return ResponseEntity.created(uri).body(trsr);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoResponseRec>> listarTopicos(@PageableDefault(size = 10) Pageable pageable, @RequestParam(required = false) String curso, @RequestParam(required = false) Integer anio){
        return ResponseEntity.ok(topicoService.listarTopicos(pageable, curso, anio).map(TopicoResponseRec::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseRec> buscarTopicoPorId(@PathVariable Long id){
        Topico topico = topicoService.buscarTopicoPorId(id);
        return ResponseEntity.ok(new TopicoResponseRec(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoResponseRec> actualizarTopicoPorId(@PathVariable Long id, @RequestBody @Valid TopicoRegistroReqRec trqr){
        Topico topico = topicoService.actualizarTopicoPorId(id, trqr);
        return ResponseEntity.ok(new TopicoResponseRec(topico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TopicoResponseRec> borrarTopicoPorId(@PathVariable Long id){
        topicoService.borrarTopicoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
