package com.banco.bancobackend.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.banco.bancobackend.model.Transferencia;

public interface TransferenciaRepositorio extends JpaRepository <Transferencia, Integer>{
	

	List<Transferencia> findByOrdenanteId(Long id);
	List<Transferencia> findByBeneficiarioId(Long id);

}
