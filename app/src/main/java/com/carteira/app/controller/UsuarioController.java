package com.carteira.app.controller;

import com.carteira.app.model.Usuario;
import com.carteira.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.criarUsuario(usuario);
    }

    @GetMapping("/{cpf}")
    public Usuario consultarUsuarioPorCpf(@PathVariable String cpf) {
        return usuarioService.consultarUsuarioPorCpf(cpf);
    }
}