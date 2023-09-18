package com.catalisa.apiDoacaoAlimento.controller;

import com.catalisa.apiDoacaoAlimento.model.DoacaoModel;
import com.catalisa.apiDoacaoAlimento.service.DoacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doacoes")
@Tag(name = "Doacao")
public class DoacaoController {

    @Autowired
    DoacaoService doacaoService;



    @Operation(summary = "Busca doacão por cidade", method = "POST")
    @ApiResponses(value = @ApiResponse(responseCode = "201", description = "Cadastro realizado com sucesso"))
    @PostMapping
    public ResponseEntity cadastrarDoacoes(@RequestBody @Valid DoacaoModel novaDoacao) {
        doacaoService.cadastroDoacao(novaDoacao);

        return ResponseEntity.status(HttpStatus.CREATED).body(novaDoacao);

    }


    @Operation(summary = "Busca todas doações", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"))
    @GetMapping
    public ResponseEntity<List<DoacaoModel>> exibirTodasDoacoes() {
        List<DoacaoModel> produtos = doacaoService.exibirDoacoes();
        return ResponseEntity.ok(produtos);
    }


    @Operation(summary = "Busca doacão por alimento", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"))
    @GetMapping(value = "buscarPorAlimento")
    public ResponseEntity<List<DoacaoModel>> buscarPorAlimento(@RequestParam(name = "alimento") String alimento) {
        List<DoacaoModel> doacoes = doacaoService.encontrarDoacoesPorAlimento(alimento);
        return new ResponseEntity<>(doacoes, HttpStatus.OK);
    }


    @Operation(summary = "Busca doacão por cidade", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"))
    @GetMapping("/buscarPorCidade")
    public ResponseEntity<List<DoacaoModel>> buscarPorCidade(@RequestParam("cidade") String cidade) {
        List<DoacaoModel> doacoes = doacaoService.encontrarDoacoesPorCidade(cidade);
        return new ResponseEntity<>(doacoes, HttpStatus.OK);
    }


    @Operation(summary = "Busca doacão por nome de doador", method = "GET")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"))
    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<DoacaoModel>> buscarPorNome(@RequestParam("nome") String nome) {
        List<DoacaoModel> doacoes = doacaoService.encontrarDoacoesPorNome(nome);
        return new ResponseEntity<>(doacoes, HttpStatus.OK);
    }


    @Operation(summary = "Busca doacão pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ID encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Id não encontrado")
    })
    public ResponseEntity<Object> listarPorId(@PathVariable(value = "id") Long id) {
        Optional<DoacaoModel> doacaoID = doacaoService.exibirPorId(id);
        if (!doacaoID.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(doacaoID.get());
    }

    @Operation(summary = "Delecao de produto pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deletarProduto(@PathVariable(value = "id") Long id) {
        Optional<DoacaoModel> doacaoOptional = doacaoService.exibirPorId(id);
        if (!doacaoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado");

        }
        doacaoService.deletar(doacaoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("deletado com sucesso");
    }


    @Operation(summary = "Altera as informações ", method = "PUT")
    @ApiResponses(value = @ApiResponse(responseCode = "200", description = "Informações Atualizadas"))
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity atualizarDados(@PathVariable Long id, @RequestBody DoacaoModel doacaoModell) {
        var doacaoAtualizada = doacaoService.atualizar(id, doacaoModell);
        return ResponseEntity.ok(doacaoAtualizada);

    }


}
