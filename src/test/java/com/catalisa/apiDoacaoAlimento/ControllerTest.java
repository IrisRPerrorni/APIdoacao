package com.catalisa.apiDoacaoAlimento;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;


import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.catalisa.apiDoacaoAlimento.config.security.SecurityFilter;
import com.catalisa.apiDoacaoAlimento.config.security.TokenService;
import com.catalisa.apiDoacaoAlimento.controller.DoacaoController;
import com.catalisa.apiDoacaoAlimento.model.DoacaoModel;
import com.catalisa.apiDoacaoAlimento.model.Endereco;
import com.catalisa.apiDoacaoAlimento.model.enuns.UnidadeDeMedida;
import com.catalisa.apiDoacaoAlimento.service.DoacaoService;

import org.junit.jupiter.api.BeforeEach;


import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;






import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@WebMvcTest(controllers = DoacaoController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DoacaoService doacaoService;

    @MockBean
    private SecurityFilter securityFilter;

    @MockBean
    private TokenService tokenService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void cadastrarDoacoesTest() throws Exception {
        Endereco endereco = new Endereco("Rua 500", "Centro", "12345668",
                "123", "Complemento", "Cidade", "SP");

        String dataDeValidadeString = "25/10/2026";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataDeValidade = LocalDate.parse(dataDeValidadeString, formatter);


        DoacaoModel novaDoacao = new DoacaoModel("João", "4799663501", endereco,
                "arroz", dataDeValidade, 5.0, UnidadeDeMedida.KG);
        when(doacaoService.cadastroDoacao(any(DoacaoModel.class))).thenReturn(novaDoacao);

        this.mockMvc.perform(post("/api/doacoes")
                        .with(jwt())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(novaDoacao)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("João"))
                .andExpect(jsonPath("$.telefone").value("4799663501"))
                .andExpect(jsonPath("$.endereco.logradouro").value("Rua 500"))
                .andExpect(jsonPath("$.endereco.bairro").value("Centro"))
                .andExpect(jsonPath("$.endereco.cep").value("12345668"))
                .andExpect(jsonPath("$.endereco.numero").value("123"))
                .andExpect(jsonPath("$.endereco.complemento").value("Complemento"))
                .andExpect(jsonPath("$.endereco.cidade").value("Cidade"))
                .andExpect(jsonPath("$.endereco.uf").value("SP"))
                .andExpect(jsonPath("$.alimento").value("arroz"))
                .andExpect(jsonPath("$.dataDeValidade").value("25/10/2026"))
                .andExpect(jsonPath("$.quantidade").value(5.0))
                .andExpect(jsonPath("$.medida").value("KG"));
    }

    @Test
    public void deletarDoacaoTest() throws Exception {
        Long contaId = 1L;
        Endereco endereco = new Endereco("Rua 500", "Centro", "12345668",
                "123", "Complemento", "Cidade", "SP");

        String dataDeValidadeString = "25/10/2026";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataDeValidade = LocalDate.parse(dataDeValidadeString, formatter);


        DoacaoModel deletar = new DoacaoModel("João", "4799663501", endereco,
                "arroz", dataDeValidade, 5.0, UnidadeDeMedida.KG);


        Optional<DoacaoModel> doacaoModelOptionalOptional = Optional.of(deletar);
        when(doacaoService.exibirPorId(contaId)).thenReturn(doacaoModelOptionalOptional);
        mockMvc.perform(delete("/api/doacoes/{id}", 1L)
                        .contentType("application/json")
                        .with(jwt()))
                .andExpect(status().isOk())
                .andExpect(content().string("deletado com sucesso"));

    }


    }







