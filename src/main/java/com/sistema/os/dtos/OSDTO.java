package com.sistema.os.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistema.os.domain.OS;
import com.sistema.os.domain.enuns.Prioridade;
import com.sistema.os.domain.enuns.Status;

import jakarta.validation.constraints.NotEmpty;

public class OSDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;
	
	private Integer prioridade;
	
	@NotEmpty(message = "O campo OBSERVAÇÕES é obrigatório!")
	private String observacoes;
	private Integer status;
	private Integer tecnico;
	private Integer cliente;
	
	public OSDTO() {
		super();
	}

	public OSDTO(OS obj) {
		
		super();
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		
		// ERRO DA EXCEÇÃO
		try {
			this.prioridade = obj.getPrioridade().getCod();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		this.observacoes = obj.getObservacoes();
		try {
			this.status = obj.getStatus().getCod();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Prioridade getPrioridade() throws IllegalAccessException {
		return Prioridade.ToEnum(this.prioridade);
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Status getStatus() throws IllegalAccessException {
		return Status.ToEnum(this.status);
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	
	
}
