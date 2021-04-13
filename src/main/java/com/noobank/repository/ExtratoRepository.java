package com.noobank.repository;

import com.noobank.entities.Extrato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtratoRepository extends JpaRepository<Extrato, Long> {
}
