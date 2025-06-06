package com.getinfo.contratos.repository;

import com.getinfo.contratos.entity.Agregado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgregadoRepository extends JpaRepository<Agregado,Long> {
    
}
