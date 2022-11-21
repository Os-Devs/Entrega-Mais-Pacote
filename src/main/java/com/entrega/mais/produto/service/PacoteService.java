package com.entrega.mais.produto.service;

import com.entrega.mais.produto.model.Pacote;
import com.entrega.mais.produto.repository.PacoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;

    public Optional<Pacote> encontraPacotePorId(Long id){
        return pacoteRepository.findById(id);
    }

    public List<Optional<Pacote>> encontraPacotesPorFornecedor(String fornecedor){
        return pacoteRepository.findByFornecedor(fornecedor);
    }

    public List<Optional<Pacote>> encontraPacotePorTamanho(String tamanho){
        return pacoteRepository.findByTamanho(tamanho);
    }

    @Transactional
    public Pacote salvarPacote (Pacote pacote){
        return pacoteRepository.save(pacote);
    }

    @Transactional
    public Pacote atualizarPacote (Long id, Pacote pacote_atualizado){

        if (pacoteRepository.findById(id).isPresent()){
            Pacote pacoteExistente = pacoteRepository.findById(id).get();

            pacoteExistente.setFornecedor(pacote_atualizado.getFornecedor());
            pacoteExistente.setTelefoneFornecedor(pacote_atualizado.getTelefoneFornecedor());
            pacoteExistente.setTamanho(pacote_atualizado.getTamanho());
            pacoteExistente.setObservacao(pacote_atualizado.getObservacao());
            pacoteExistente.setFornPagouFrete(pacote_atualizado.getFornPagouFrete());
            pacoteExistente.setQuemPagaTaxa(pacote_atualizado.getQuemPagaTaxa());

            Pacote pacotetualizada = pacoteRepository.save(pacoteExistente);

            return pacoteExistente;

        }else{

            return null;
        }

    }



}