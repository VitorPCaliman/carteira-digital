package com.carteira.app.repository;

import com.carteira.app.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByContaOrigem(String contaOrigem);
}