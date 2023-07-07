package com.banco.bancobackend.service;
import java.util.ArrayList;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.bancobackend.model.Gestor;
import com.banco.bancobackend.repositorio.GestorRepositorio;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class GestorService {
	
	@Autowired
	GestorRepositorio gestorRepository;
	
	public ArrayList<Gestor> leerGestores(){
		return (ArrayList<Gestor>)this.gestorRepository.findAll();
	}
	
	
	public Optional<Gestor>leerGestorPorId(Integer id){
		return this.gestorRepository.findById(id);
	}
	
	
	public Gestor guardarGestor(Gestor gestor) {
		String pass = gestor.getPassword();
		if(pass!= null) {
			BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
			pass = encoder.encode(pass);
			gestor.setPassword(pass);
		}else {
			Gestor gestorExistente = leerGestorPorId(gestor.getId()).orElse(null);
			if(gestorExistente != null) {
				gestor.setPassword(gestorExistente.getPassword());
			}
		}
		return this.gestorRepository.save(gestor);
	}
	
	
	//elimina un gestor por su id 
	public void borrarGestorPorId(Integer id) {
		this.gestorRepository.deleteById(id);
		
	}
	
	public Optional<Gestor>buscarPorCorreo(String correo){
		return this.gestorRepository.findFirstByCorreo(correo);
	}
	
	
	public Optional<Gestor>buscarPorCorreoYPass(String correo, String password){
		
		Optional<Gestor> gestor = buscarPorCorreo(correo);
		if(gestor.isPresent()) {
			Gestor gestorEncontrado = gestor.get();
			BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
			if(encoder.matches(password, gestorEncontrado.getPassword())) {
				return gestor;
		}
		

	
		}
		
		return null;
	
	}

	
	
	
}

