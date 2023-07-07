package com.banco.bancobackend.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.banco.bancobackend.model.Mensaje;

public interface MensajeRepositorio extends JpaRepository <Mensaje, Integer>{
	

	

}
