package com.catalisa.apiDoacaoAlimento.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AutenticationDTO {

    private String login;


    private String senha;
}
