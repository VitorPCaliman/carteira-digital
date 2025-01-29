package com.carteira.app.service;

import com.carteira.app.model.Usuario;
import com.carteira.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario criarUsuario(Usuario usuario) {
        if (usuarioRepository.findByCpf(usuario.getCpf()) != null) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        usuario.setSaldo(BigDecimal.valueOf(1000));

        return usuarioRepository.save(usuario);
    }

    public Usuario consultarUsuarioPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }
}