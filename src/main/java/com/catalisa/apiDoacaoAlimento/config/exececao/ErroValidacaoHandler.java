package com.catalisa.apiDoacaoAlimento.config.exececao;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroValidacaoHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosErroValidacao>> tratarErro400(MethodArgumentNotValidException ex) {
        List<DadosErroValidacao> erros = new ArrayList<>();
        for (FieldError erro : ex.getFieldErrors()) {
            erros.add(new DadosErroValidacao(erro.getField(), erro.getDefaultMessage()));
        }
        return ResponseEntity.badRequest().body(erros);
    }

    public static class DadosErroValidacao {
        private String campo;
        private String mensagem;

        public DadosErroValidacao(String campo, String mensagem) {
            this.campo = campo;
            this.mensagem = mensagem;
        }

        public String getCampo() {
            return campo;
        }

        public String getMensagem() {
            return mensagem;
        }
    }
}
