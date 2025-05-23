package com.getinfo.contratos.repository;

import com.getinfo.contratos.entity.Empresa;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    boolean existsByCnpj(String cnpj);
    Optional<Empresa> findByCnpj(String cnpj);

}
