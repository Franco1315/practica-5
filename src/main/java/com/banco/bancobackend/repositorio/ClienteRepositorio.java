package com.banco.bancobackend.repositorio;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banco.bancobackend.model.Cliente;


public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
	
	public Optional<Cliente> findFirstByCorreo(String correo);
	
	public Optional<Cliente> findFirstByCorreoAndPassword(String correo, String password);

}
