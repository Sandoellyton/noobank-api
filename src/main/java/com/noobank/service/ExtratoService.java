package com.noobank.service;

import com.noobank.entities.Extrato;
import com.noobank.repository.ExtratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtratoService {

    @Autowired
    private ExtratoRepository repository;

    public List<Extrato> listar(){
        return repository.findAll();
    }

    public Optional<Extrato> buscar(Long id){
        return repository.findById(id);
    }

    public Extrato inserir(Extrato extrato){
        return repository.save(extrato);
    }

}
