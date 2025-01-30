package com.carteira.app.controller;

import com.carteira.app.config.JwtUtil;
import com.carteira.app.model.Usuario;
import com.carteira.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;



    @GetMapping("/{email}")
    public Usuario consultarUsuarioPorCpf(@PathVariable String email) {
        return usuarioService.consultarUsuarioPorEmail(email);
    }

    @GetMapping("/{contaOrigem}")
    public Usuario consultarUsuarioPorContaOrigem(@PathVariable String contaOrigem) {
        return usuarioService.consultarUsuarioPorContaOrigem(contaOrigem);
    }
}