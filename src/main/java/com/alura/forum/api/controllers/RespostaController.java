package com.alura.forum.api.controllers;
import com.alura.forum.api.models.dto.resposta.RespostaDtoGet;
import com.alura.forum.api.models.dto.resposta.RespostaDtoPost;
import com.alura.forum.api.models.dto.resposta.RespostaDtoPut;
import com.alura.forum.api.services.RespostaService;
import com.alura.forum.api.services.TopicoService;
import com.alura.forum.api.services.UUIDService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/topico/{topicoId}/resposta")
public class RespostaController {
    @Autowired
    private RespostaService respostaService;
    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public ResponseEntity<List<RespostaDtoGet>> getRespostas(@PathVariable String topicoId) {
        UUID uuid = UUIDService.valideUUID(topicoId);
        return ResponseEntity.ok(respostaService.getRespostas(uuid));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespostaDtoGet> getResposta(
            @PathVariable String topicoId,
            @PathVariable String id) {
        UUID uuid = UUIDService.valideUUID(id);
        return ResponseEntity.ok(respostaService.getResposta(uuid));
    }

    @PostMapping
    public ResponseEntity<RespostaDtoGet> postResposta(@RequestBody @Valid RespostaDtoPost respostaDtoPost,
                                                       @PathVariable String topicoId) {
        var resposta = respostaService.createResposta(respostaDtoPost);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(resposta.id())
                .toUri();
        return ResponseEntity.created(uri).body(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespostaDtoGet> putResposta(@RequestBody RespostaDtoPut respostaDtoPut,
                                                      @PathVariable String id,
                                                      @PathVariable String topicoId) {
        UUID uuid = UUIDService.valideUUID(id);
        var resposta = respostaService.updateResposta(uuid, respostaDtoPut);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResposta(@PathVariable String topicoId,
                                               @PathVariable String id) {
        UUID uuid = UUIDService.valideUUID(id);
        respostaService.deleteResposta(uuid);
        return ResponseEntity.noContent().build();
    }


}
