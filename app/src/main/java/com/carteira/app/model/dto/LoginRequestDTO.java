package com.carteira.app.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO{

    private String email;
    private String senha;
}
