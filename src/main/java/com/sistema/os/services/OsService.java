package com.sistema.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.os.domain.Cliente;
import com.sistema.os.domain.OS;
import com.sistema.os.domain.Tecnico;
import com.sistema.os.domain.enuns.Prioridade;
import com.sistema.os.domain.enuns.Status;
import com.sistema.os.dtos.OSDTO;
import com.sistema.os.repositories.OSRepository;
import com.sistema.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class OsService {

	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;

	public OS findById(Integer id) {

		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OS.class.getName()));
	}
	
	public List<OS> findAll(){
		return repository.findAll();
	}

	public OS create(@Valid OSDTO obj) {
		return fromDTO(obj);
	}
	
	public OS update(@Valid OSDTO obj) {
		findById(obj.getId());
		return fromDTO(obj);
	}
	
	private OS fromDTO(OSDTO obj) {
		OS newObj = new OS();
		newObj.setId(obj.getId());
		newObj.setObservacoes(obj.getObservacoes());
		
		// ERRO DA EXCEÇÃO
		try {
			newObj.setPrioridade(Prioridade.ToEnum(obj.getPrioridade().getCod()));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		try {
			newObj.setStatus(Status.ToEnum(obj.getStatus().getCod()));
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		Tecnico tec = tecnicoService.findById(obj.getTecnico());
		Cliente cli = clienteService.findById(obj.getCliente());
		
		newObj.setTecnico(tec);
		newObj.setCliente(cli);
		
		// ERRO DA EXCEÇÃO
		try {
			if(newObj.getStatus().getCod().equals(2)) {
				newObj.setDataFechamento(LocalDateTime.now());
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return repository.save(newObj);
	}

	

}






