package com.carteira.app.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class RegisterRequestDTO {

    private String nome;
    private String cpf;
    private String contaOrigem;
    private BigDecimal saldo;
    private String email;
    private String senha;

}
