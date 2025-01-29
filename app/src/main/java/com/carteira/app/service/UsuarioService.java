package com.carteira.app.service;

import com.carteira.app.model.Usuario;
import com.carteira.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private PasswordEncoder passwordEncoder;
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);
        }
        return new User(usuario.getEmail(), usuario.getSenha(), new ArrayList<>());
    }

    public Usuario criarUsuario(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        if (usuarioRepository.findByCpf(usuario.getCpf()) != null) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        usuario.setSaldo(BigDecimal.valueOf(1500));

        return usuarioRepository.save(usuario);
    }

    public Usuario consultarUsuarioPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }
}