package com.rf.fintech.controller;

import com.rf.fintech.model.Transacao;
import com.rf.fintech.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @GetMapping
    public ResponseEntity<List<Transacao>> listarTransacoes() {
        return ResponseEntity.ok(transacaoService.listarTransacoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> buscarTransacao(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(transacaoService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Transacao> criarTransacao(@RequestParam Long contaId, @RequestBody Transacao transacao) {
        Transacao nova = transacaoService.criarTransacao(contaId, transacao);
        return ResponseEntity.status(201).body(nova);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transacao> atualizarTransacao(@PathVariable Long id, @RequestBody Transacao transacao) {
        try {
            Transacao atualizada = transacaoService.atualizarTransacao(id, transacao);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTransacao(@PathVariable Long id) {
        try {
            transacaoService.deletarTransacao(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
