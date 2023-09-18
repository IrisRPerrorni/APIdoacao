package com.catalisa.apiDoacaoAlimento.model.dto;

import lombok.Data;

@Data
public class LoginRespostaDTO {

    private String token;

    public LoginRespostaDTO(String token) {
        this.token = token;
    }

}
