package com.banco.bancobackend.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banco.bancobackend.model.Transferencia;
import com.banco.bancobackend.service.TransferenciaService;

@RestController
//http://localhost:8080/gestor
@RequestMapping("/transferencia")

@CrossOrigin(origins = "http://localhost:4200")


public class TransferenciaController {
	
	@Autowired
	TransferenciaService transferenciaService;

	
	@GetMapping()
	public ArrayList<Transferencia> obtenerTransferencia(){
		return this.transferenciaService.leerTransferencias();
	}
	
	@GetMapping(path = "/{id}")
	public Optional <Transferencia> obtenerTransferenciaPorId(@PathVariable("id") Integer id){
		return this.transferenciaService.leerTransferenciaPorId(id);
	}
	

	
	@PostMapping()
	public Transferencia guardarTransferencia(@RequestBody Transferencia transferencia) {
		return this.transferenciaService.guardarTransferencia(transferencia);
	}
	
	@GetMapping("/ordenante/{id}")
	public List<Transferencia> getTransferenciasEnviadas(@PathVariable Long id) {
		return transferenciaService.getTransferenciasByClienteOrdenante(id);
	}

	

	@GetMapping("/beneficiario/{id}")
	public List<Transferencia> getTransferenciasRecibidas(@PathVariable Long id) {
		return transferenciaService.getTransferenciasByClienteBeneficiario(id);
	}
	
	@DeleteMapping(path = "/{id}")
	public void borrarTransferencia(@PathVariable("id") Integer id){
		this.transferenciaService.borrarTransferenciaPorId(id);
	}
	
	
}
