package com.catalisa.apiDoacaoAlimento.model.dto;

import com.catalisa.apiDoacaoAlimento.model.enuns.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDto {
    private String login;


    private String senha;

    private UserRole role;
}
