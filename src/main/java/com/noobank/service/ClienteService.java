package com.noobank.service;

import com.noobank.model.Cliente;
import com.noobank.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

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

    public void remover(Long id) throws Exception {
        try {
            repository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            throw new Exception("Entidade não encontrada");
        } catch (DataIntegrityViolationException e) {
            throw new Exception("Entidade está em uso, não pode ser removiada");
        }
    }

    public Cliente atualizacaoTotal(Long id, Cliente novoCliente){
        Optional<Cliente> oldCliente = repository.findById(id);
        Cliente cliente = oldCliente.get();

        BeanUtils.copyProperties(novoCliente, cliente, "id");
        return repository.save(cliente);
    }
}
