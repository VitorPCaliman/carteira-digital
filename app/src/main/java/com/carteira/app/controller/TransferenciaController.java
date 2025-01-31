package com.carteira.app.controller;

import com.carteira.app.exception.TransferenciaException;
import com.carteira.app.model.Transferencia;
import com.carteira.app.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public ResponseEntity<Transferencia> agendarTransferencia(@RequestBody Transferencia transferencia) {
        return ResponseEntity.ok(transferenciaService.agendarTransferencia(transferencia));
    }

    @GetMapping("/extrato/{contaOrigem}")
    public List<Transferencia> consultarExtrato(@PathVariable String contaOrigem) {
        return transferenciaService.consultarExtrato(contaOrigem);
    }
}

