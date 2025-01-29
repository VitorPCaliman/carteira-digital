package com.carteira.app.service;

import com.carteira.app.model.Transferencia;
import com.carteira.app.model.Usuario;
import com.carteira.app.repository.TransferenciaRepository;
import com.carteira.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Transferencia agendarTransferencia(Transferencia transferencia) {
        if (!transferencia.getContaOrigem().matches("\\d{10}") || !transferencia.getContaDestino().matches("\\d{10}")) {
            throw new IllegalArgumentException("Conta de origem ou destino inválida.");
        }

        Usuario usuario = usuarioRepository.findByContaOrigem(transferencia.getContaOrigem());
        if (usuario == null || usuario.getSaldo().compareTo(transferencia.getValor()) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }

        BigDecimal taxa = calcularTaxa(transferencia.getDataTransferencia(), transferencia.getValor());
        transferencia.setTaxa(taxa);

        atualizarSaldo(usuario, transferencia, taxa);

        transferencia.setDataAgendamento(LocalDate.now());

        return transferenciaRepository.save(transferencia);
    }

    private void atualizarSaldo ( Usuario usuario, Transferencia transferencia, BigDecimal taxa){
        BigDecimal valorDebitado = transferencia.getValor().add(taxa);
        BigDecimal saldoAtualizado = usuario.getSaldo().subtract(valorDebitado);

        usuario.setSaldo(saldoAtualizado);
        usuarioRepository.save(usuario);
    }

    private BigDecimal calcularTaxa(LocalDate dataTransferencia, BigDecimal valor) {
        long dias = ChronoUnit.DAYS.between(LocalDate.now(), dataTransferencia);

        if (dias == 0) {
            return valor.multiply(new BigDecimal("0.025")).add(new BigDecimal("3.00"));
        } else if (dias >= 1 && dias <= 10) {
            return new BigDecimal("12.00");
        } else if (dias >= 11 && dias <= 20) {
            return valor.multiply(new BigDecimal("0.082"));
        } else if (dias >= 21 && dias <= 30) {
            return valor.multiply(new BigDecimal("0.069"));
        } else if (dias >= 31 && dias <= 40) {
            return valor.multiply(new BigDecimal("0.047"));
        } else if (dias >= 41 && dias <= 50) {
            return valor.multiply(new BigDecimal("0.017"));
        } else {
            throw new IllegalArgumentException("Não há taxa aplicável para esta data de transferência.");
        }
    }

    public List<Transferencia> consultarExtrato(String contaOrigem) {
        return transferenciaRepository.findByContaOrigem(contaOrigem);
    }
}