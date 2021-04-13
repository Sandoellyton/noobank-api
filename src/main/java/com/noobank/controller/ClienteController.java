package com.noobank.controller;

import ch.qos.logback.core.net.server.Client;
import com.noobank.entities.Cliente;
import com.noobank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        Optional<Cliente> cliente = service.buscar(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizacaoTotal(@PathVariable Long id, @RequestBody Cliente novoCliente){
        Cliente cliente = service.atualizacaoTotal(id, novoCliente);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
}
