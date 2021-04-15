package com.noobank.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Conta {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(nullable = false)
    private Long numero;
    private Double saldo = 0.0;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany
    @JoinColumn(name = "extrato_id")
    private List<Extrato> extratos;

}
