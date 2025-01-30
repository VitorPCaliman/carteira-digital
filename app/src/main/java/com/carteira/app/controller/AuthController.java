package com.carteira.app.controller;

import com.carteira.app.config.JwtUtil;
import com.carteira.app.model.Usuario;
import com.carteira.app.model.dto.LoginRequestDTO;
import com.carteira.app.model.dto.LoginResponseDTO;
import com.carteira.app.model.dto.RegisterRequestDTO;
import com.carteira.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = new LoginResponseDTO();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha()));
        final UserDetails userDetails = usuarioService.loadUserByUsername(request.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        response.setName(userDetails.getUsername());
        response.setToken(jwt);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> criarUsuario(@RequestBody RegisterRequestDTO usuario) {
        LoginResponseDTO response = new LoginResponseDTO();
        Usuario novoUsuario = usuarioService.criarUsuario(usuario);
        UserDetails userDetails = usuarioService.loadUserByUsername(novoUsuario.getEmail());
        String jwt = jwtUtil.generateToken(userDetails);

        response.setName(userDetails.getUsername());
        response.setToken(jwt);
        return ResponseEntity.ok(response);
    }
}