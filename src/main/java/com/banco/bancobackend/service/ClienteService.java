package com.banco.bancobackend.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banco.bancobackend.model.Cliente;
import com.banco.bancobackend.model.Gestor;
import com.banco.bancobackend.repositorio.ClienteRepositorio;
import com.banco.bancobackend.repositorio.GestorRepositorio;


@Service
public class ClienteService {
	
		@Autowired
		private ClienteRepositorio clienteRepository;
			
		public ArrayList<Cliente> leerclientes(){
			return (ArrayList<Cliente>)this.clienteRepository.findAll();
		}
		
		
		public Optional<Cliente>leerClientePorId(Integer id){
			return this.clienteRepository.findById(id);
		}
		
		private String obtenerPasswordActual (Cliente cliente) {
			Cliente clienteGuardado = leerClientePorId(cliente.getId()).orElse(null);
			if(clienteGuardado != null) {
				return clienteGuardado.getPassword();
			}
			return null;
		}
		
		
		public Cliente guardarClienteSinActaualizarPassword(Cliente cliente) {
			String passGuardad= obtenerPasswordActual(cliente);
			cliente.setPassword(passGuardad);
			return this.clienteRepository.save(cliente);
		}
		
		
		//elimina un gestor por su id 
		public void borrarClientePorId(Integer id) {
			this.clienteRepository.deleteById(id);
			
		}
		
		public Optional<Cliente>buscarPorCorreo(String correo){
			return this.clienteRepository.findFirstByCorreo(correo);
		}
		
		
		public Optional<Cliente>buscarPorCorreoYPass(String correo, String password){
			return this.clienteRepository.findFirstByCorreoAndPassword(correo, password);
		}
		
		

}
