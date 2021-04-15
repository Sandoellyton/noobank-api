package com.noobank.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Cliente {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(nullable = false)
    private String nome;

}
