package com.banco.bancobackend.service;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.banco.bancobackend.model.Mensaje;
import com.banco.bancobackend.repositorio.MensajeRepositorio;

@Service
public class MensajeService {
	
	@Autowired
	MensajeRepositorio mensajeRepository;
		
	public ArrayList<Mensaje> leerMensaje(){
		return (ArrayList<Mensaje>)this.mensajeRepository.findAll();
	}
	
	
	public Optional<Mensaje>leerMensajePorId(Integer id){
		return this.mensajeRepository.findById(id);
	}
	
	
	public Mensaje guardarMensaje(Mensaje mensaje) {
		return this.mensajeRepository.save(mensaje);
	}
	
	
	//elimina un gestor por su id 
	public void borrarMensajePorId(Integer id) {
		this.mensajeRepository.deleteById(id);
		
	}
	

}
