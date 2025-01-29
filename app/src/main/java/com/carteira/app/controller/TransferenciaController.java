package com.carteira.app.controller;

import com.carteira.app.model.Transferencia;
import com.carteira.app.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @PostMapping
    public Transferencia agendarTransferencia(@RequestBody Transferencia transferencia) {
        return transferenciaService.agendarTransferencia(transferencia);
    }

    @GetMapping("/extrato/{contaOrigem}")
    public List<Transferencia> consultarExtrato(@PathVariable String contaOrigem) {
        return transferenciaService.consultarExtrato(contaOrigem);
    }
}

