package com.noobank.controller;

import com.noobank.model.Conta;
import com.noobank.repository.ContaRepository;
import com.noobank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    ContaService service;
    ContaRepository repository;

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody Conta NovaConta){
        Conta conta = service.inserir(NovaConta);
        return new ResponseEntity<>(conta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> listar(){
        List<Conta> contas = service.listar();
        return new ResponseEntity<>(contas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        Optional<Conta> conta = service.buscar(id);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTotal(@PathVariable Long id, @RequestBody Conta novaConta){
        Conta conta = service.atualizarTotal(id, novaConta);
        return new ResponseEntity<>(conta, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public void removerPorId(@PathVariable Long id) throws Exception {
        service.remover(id);
    }
}
