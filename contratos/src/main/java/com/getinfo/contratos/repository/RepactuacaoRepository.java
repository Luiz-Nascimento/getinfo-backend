package com.getinfo.contratos.repository;

import com.getinfo.contratos.entity.Repactuacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepactuacaoRepository extends JpaRepository<Repactuacao, Long> {

}
