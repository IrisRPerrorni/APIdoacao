package com.catalisa.apiDoacaoAlimento.service;

import com.catalisa.apiDoacaoAlimento.controller.DoacaoController;
import com.catalisa.apiDoacaoAlimento.model.DoacaoModel;
import com.catalisa.apiDoacaoAlimento.model.Endereco;
import com.catalisa.apiDoacaoAlimento.repository.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DoacaoService {

    @Autowired
    DoacaoRepository doacaoRepository;

    public DoacaoModel cadastroDoacao(DoacaoModel doacaoModel) {
        return doacaoRepository.save(doacaoModel);

    }

    public List<DoacaoModel> encontrarDoacoesPorAlimento(String alimento) {
        return doacaoRepository.findByAlimento(alimento);
    }

    public List<DoacaoModel> encontrarDoacoesPorCidade(String cidade) {
        return doacaoRepository.findByEnderecoCidade(cidade);
    }

    public List<DoacaoModel> encontrarDoacoesPorNome(String nome) {
        return doacaoRepository.findByNome(nome);
    }

    public Optional<DoacaoModel> exibirPorId(Long id) {
        return doacaoRepository.findById(id);
    }

    public List<DoacaoModel> exibirDoacoes() {
        return doacaoRepository.findAll();
    }

    public void deletar(DoacaoModel doacaoModel) {

        doacaoRepository.delete(doacaoModel);
    }

    public DoacaoModel atualizar(Long id, DoacaoModel doacaoModel) {
        DoacaoModel doacao = exibirPorId(id).get();

        if (doacaoModel.getNome() != null) {
            doacao.setNome(doacaoModel.getNome());
        }
        if (doacaoModel.getTelefone() != null) {
            doacao.setTelefone(doacaoModel.getTelefone());
        }
        if (doacaoModel.getAlimento() != null) {
            doacao.setAlimento(doacaoModel.getAlimento());
        }
        if (doacaoModel.getEndereco() != null) {
            Endereco atualizarEndereco = doacaoModel.getEndereco();
            if (atualizarEndereco.getCep() != null) {
                doacao.getEndereco().setCep(atualizarEndereco.getCep());
            }
            if (atualizarEndereco.getLogradouro() != null) {
                doacao.getEndereco().setLogradouro(atualizarEndereco.getLogradouro());
            }
            if (atualizarEndereco.getBairro() != null) {
                doacao.getEndereco().setBairro(atualizarEndereco.getBairro());
            }
            if (atualizarEndereco.getCidade() != null) {
                doacao.getEndereco().setCidade(atualizarEndereco.getCidade());
            }
            if (atualizarEndereco.getNumero() != null) {
                doacao.getEndereco().setNumero(atualizarEndereco.getNumero());
            }
            if (atualizarEndereco.getComplemento() != null) {
                doacao.getEndereco().setComplemento(atualizarEndereco.getComplemento());
            }
        }
        if (doacaoModel.getQuantidade() != 0) {
            doacao.setQuantidade(doacaoModel.getQuantidade());
        }
        if (doacaoModel.getMedida() != null){
            doacao.setMedida(doacaoModel.getMedida());
        }

        return doacaoRepository.save(doacao);
    }

}
