package com.getinfo.contratos.repository;

import com.getinfo.contratos.entity.Entregavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregavelRepository extends JpaRepository<Entregavel, Long> {

}
