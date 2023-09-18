package com.catalisa.apiDoacaoAlimento.model;
import com.catalisa.apiDoacaoAlimento.model.enuns.UnidadeDeMedida;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;


@Table(name = "doacao")
@Entity(name = "doacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    @Pattern(regexp = "^[0-9]{10}$", message = "Número de telefone inválido")
    private String telefone;

    @Embedded
    private Endereco endereco;

    @NotNull
    private String alimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @Future
    private LocalDate dataDeValidade;

    @NotNull
    private double quantidade;

    @Enumerated(EnumType.STRING)
    private UnidadeDeMedida medida;

    public DoacaoModel(String nome, String telefone, Endereco endereco, String alimento, LocalDate dataDeValidade, double quantidade, UnidadeDeMedida medida) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.alimento = alimento;
        this.dataDeValidade = dataDeValidade;
        this.quantidade = quantidade;
        this.medida = medida;
    }
}
