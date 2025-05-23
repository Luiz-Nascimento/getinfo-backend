package com.getinfo.contratos.repository;

import com.getinfo.contratos.entity.Empresa;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    Optional<Empresa> findByCnpj(String cnpj);

    @Query(value = "SELECT * FROM empresa WHERE id = :id", nativeQuery = true)
    Optional<Empresa> findByIdDeleted(@Param("id") Long id);

}
