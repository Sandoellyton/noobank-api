package com.noobank.controller;

import com.noobank.model.Extrato;
import com.noobank.service.ExtratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/extratos")
public class ExtratoController {

    @Autowired
    ExtratoService service;

    @GetMapping
    public ResponseEntity<?> listar(){
        List<Extrato> extratos = service.listar();
        return new ResponseEntity<>(extratos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){
        Optional<Extrato> extrato = service.buscar(id);
        return new ResponseEntity<>(extrato, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody Extrato extrato){
        service.inserir(extrato);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
