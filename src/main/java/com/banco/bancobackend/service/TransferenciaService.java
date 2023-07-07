package com.banco.bancobackend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.banco.bancobackend.model.Cliente;

import com.banco.bancobackend.model.Transferencia;
import com.banco.bancobackend.repositorio.TransferenciaRepositorio;

@CrossOrigin(origins = "http://localhost:4200")

@Service
public class TransferenciaService {
	
	@Autowired
	TransferenciaRepositorio transferenciaRepositorio;
	
	@Autowired
	ClienteService clienteService; 
	

	
	public ArrayList<Transferencia> leerTransferencias(){
		return(ArrayList<Transferencia>)this.transferenciaRepositorio.findAll();
	}
	
	public Optional<Transferencia>leerTransferenciaPorId(Integer id){
		return this.transferenciaRepositorio.findById(id);
	}
	
	public Transferencia guardarTransferencia(Transferencia transferencia ) {
		
		this.transferenciaRepositorio.save(transferencia);
		
		Double importe = transferencia.getImporte();
		
		
		Cliente ordenante = transferencia.getOrdenante();
		ordenante = clienteService.leerClientePorId(ordenante.getId()).orElse(null);
		
		Double saldoOrdenante = ordenante.getSaldo();
		ordenante.setSaldo(saldoOrdenante - importe);
		
		Cliente beneficiario = transferencia.getBeneficiario();
		
		beneficiario = clienteService.leerClientePorId(beneficiario.getId()).orElse(null);
		
		Double saldoBeneficiario = beneficiario.getSaldo();
	
	

		
		
		beneficiario.setSaldo(saldoBeneficiario + importe);
		
		clienteService.guardarClienteSinActaualizarPassword(ordenante);
		clienteService.guardarClienteSinActaualizarPassword(beneficiario);
		
		
		
		return this.transferenciaRepositorio.save(transferencia);
	}
	
	public void borrarTransferenciaPorId(Integer id) {
		this.transferenciaRepositorio.deleteById(id);
		
	}
	
	public List<Transferencia> getTransferenciasByClienteOrdenante(Long id) {
		return transferenciaRepositorio.findByOrdenanteId(id);
	}
	
	public List<Transferencia> getTransferenciasByClienteBeneficiario(Long id) {
		return transferenciaRepositorio.findByBeneficiarioId(id);
	}
	
	
	
	
}
