package com.noobank.repository;

import com.noobank.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNome(String nome);

    @Query("FROM Cliente WHERE nome LIKE %:nome%")
    List<Cliente> consultarPorNome(String nome);

    @Query("FROM Cliente WHERE id = :id")
    Cliente clientePorId(Long id);
}
