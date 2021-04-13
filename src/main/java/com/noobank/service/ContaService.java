package com.noobank.service;

import com.noobank.entities.Conta;
import com.noobank.repository.ContaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    ContaRepository repository;

    public Conta inserir(Conta conta){
        return repository.save(conta);
    }

    public Optional<Conta> buscar(Long id){
        return repository.findById(id);
    }

    public List<Conta> listar(){
        return repository.findAll();
    }

    public Conta atualizarTotal(Long id, Conta novaConta){
        Optional<Conta> oldConta = repository.findById(id);
        Conta conta = oldConta.get();

        BeanUtils.copyProperties(novaConta, conta, "id");

        return repository.save(conta);
    }
}
