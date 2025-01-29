package com.carteira.app.repository;

import com.carteira.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByContaOrigem(String contaOrigem);
    Usuario findByCpf(String cpf);
    Usuario findByEmail(String email);
}