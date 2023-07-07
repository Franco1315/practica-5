package com.banco.bancobackend.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banco.bancobackend.model.Cliente;
import com.banco.bancobackend.model.Gestor;
import com.banco.bancobackend.service.ClienteService;
import com.banco.bancobackend.service.GestorService;

@RestController
// http://localhost:8080/gestor
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	

	@GetMapping()
	public ArrayList<Cliente> obtenerClientes(){
		return this.clienteService.leerclientes();
	}
	
	@GetMapping(path = "/{id}")
	public Optional <Cliente> obtenerCliente(@PathVariable("id") Integer id){
		return this.clienteService.leerClientePorId(id);
	}
	
	@GetMapping(path = "/correo/{email}")
	public Optional <Cliente> obtenerClientePorCorreo(@PathVariable("email") String email){
		return this.clienteService.buscarPorCorreo(email);
	}
	
	@GetMapping(path = "/login")
	public Optional <Cliente> loguearCliente(@RequestParam("correo")String correo, @RequestParam("pass") String password){
		return this.clienteService.buscarPorCorreoYPass(correo, password);
	}
	
	@PostMapping()
	public Cliente guardarCliente(@RequestBody Cliente cliente) {
		return this.clienteService.guardarClienteSinActaualizarPassword(cliente);
	}
	
	
	@DeleteMapping(path = "/{id}")
	public void borrarCliente(@PathVariable("id") Integer id){
		
		this.clienteService.borrarClientePorId(id);
	}
}
