package com.catalisa.apiDoacaoAlimento.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @NotNull
    private String logradouro;
    @NotNull
    private String bairro;
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    private String cep;
    @NotNull
    private String numero;
    @NotNull
    private String complemento;
    @NotNull
    private String cidade;
    @NotNull
    private String uf;




}
