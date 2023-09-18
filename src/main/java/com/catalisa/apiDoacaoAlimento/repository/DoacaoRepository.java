package com.catalisa.apiDoacaoAlimento.repository;

import com.catalisa.apiDoacaoAlimento.model.DoacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoacaoRepository extends JpaRepository<DoacaoModel, Long> {

    List<DoacaoModel> findByAlimento(String alimento);
    List<DoacaoModel> findByEnderecoCidade(String cidade);

    List<DoacaoModel> findByNome(String nome);



}
