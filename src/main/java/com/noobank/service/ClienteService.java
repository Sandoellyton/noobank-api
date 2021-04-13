package com.noobank.service;

import com.noobank.entities.Cliente;
import com.noobank.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente inserir(Cliente cliente){
        return repository.save(cliente);
    }

    public List<Cliente> listar(){
        return repository.findAll();
    }

    public Optional<Cliente> buscar(Long id){
        return repository.findById(id);
    }

    public void remover(Long id){
        repository.deleteById(id);
    }

    public Cliente atualizacaoTotal(Long id, Cliente novoCliente){
        Optional<Cliente> oldCliente = repository.findById(id);
        Cliente cliente = oldCliente.get();

        BeanUtils.copyProperties(novoCliente, cliente, "id");
        return repository.save(cliente);
    }
}
