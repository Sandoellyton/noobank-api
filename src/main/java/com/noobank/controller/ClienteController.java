package com.noobank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noobank.model.Cliente;
import com.noobank.repository.ClienteRepository;
import com.noobank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;
    @Autowired
    private ClienteRepository repository;

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody Cliente cliente){
        service.inserir(cliente);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> listar(){
        List<Cliente> cliente = service.listar();
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/por-nome")
    public ResponseEntity<?> buscarPorNome(Long id) {
        Cliente cliente = repository.clientePorId(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        Optional<Cliente> cliente = service.buscar(id);

        if (cliente.isPresent()){
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizacaoTotal(@PathVariable Long id, @RequestBody Cliente novoCliente){
        Cliente cliente = service.atualizacaoTotal(id, novoCliente);

        if (cliente != null) {
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizacaoParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        Optional<Cliente> clienteAtual = service.buscar(id);
        Cliente cliente = clienteAtual.get();

        if(cliente == null){
            return ResponseEntity.notFound().build();
        }
        merge(campos, clienteAtual);

        return atualizacaoTotal(id, cliente);
    }
    private void merge(Map<String, Object> dadosOrigem, Optional<Cliente> clienteDestino){
        ObjectMapper objectMapper = new ObjectMapper();
        Cliente clienteOrigem = objectMapper.convertValue(dadosOrigem, Cliente.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) ->{
            Field field = ReflectionUtils.findField(Cliente.class, nomePropriedade);
            field.setAccessible(true);
            Object novoValor = ReflectionUtils.getField(field, clienteOrigem);
            ReflectionUtils.setField(field, clienteDestino, novoValor);
        });
    }
}
