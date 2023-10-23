package com.sistema.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.os.domain.Cliente;
import com.sistema.os.domain.OS;
import com.sistema.os.domain.Tecnico;
import com.sistema.os.domain.enuns.Prioridade;
import com.sistema.os.domain.enuns.Status;
import com.sistema.os.repositories.ClienteRepository;
import com.sistema.os.repositories.OSRepository;
import com.sistema.os.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OSRepository osRepository;

	public void instaciaDB() {

		Tecnico t1 = new Tecnico(null, "Tecnico Exemple", "804.474.190-99", "(11) 91111-1111");
		Cliente c1 = new Cliente(null, "Cliente Exemple", "215.295.400-97", "(22) 92222-2222");

		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));

	}

}