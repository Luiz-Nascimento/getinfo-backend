package com.getinfo.contratos.repository;

import com.getinfo.contratos.entity.Aditivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AditivoRepository extends JpaRepository<Aditivo, Long> {
    
}
