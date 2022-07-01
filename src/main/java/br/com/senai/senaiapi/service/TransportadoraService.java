package br.com.senai.senaiapi.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.google.common.base.Preconditions;

import br.com.senai.senaiapi.controller.RegistroNaoEncontradoException;
import br.com.senai.senaiapi.entity.Transportadora;
import br.com.senai.senaiapi.repository.TransportadorasRepository;

@Service
@Validated
public class TransportadoraService {
	
	@Autowired
	private TransportadorasRepository repository;
	
	public Transportadora inserir(
			@Valid
			@NotNull(message = "A transportadora não pode ser nula")
			Transportadora novaTransportadora) {
		Preconditions.checkArgument(novaTransportadora.getId() == null, 
				"O id deve ser nulo");
		Transportadora transportadoraSalva = 
				repository.save(novaTransportadora);
		return transportadoraSalva;
	}
	
	public Transportadora alterar(
			@Valid
			@NotNull(message = "A transportadora não pode ser nula")
			Transportadora transportadoraSalva) {
		Preconditions.checkArgument(transportadoraSalva.getId() != null, 
				"O id é obrigatório");
		Transportadora transportadoraAtualizada = 
				repository.save(transportadoraSalva);
		return transportadoraAtualizada;
	}
	
	public Transportadora buscarPor(
			@NotNull(message = "O id é obrigatório")
			Integer id) {
		Transportadora transportadoraEncontrada = 
				repository.buscarPor(id);
		if (transportadoraEncontrada == null) {
			throw new RegistroNaoEncontradoException(
					"Não foi encontrada transportadora");
		}
		return transportadoraEncontrada;
	}
	
	public List<Transportadora> listarPor(
			@NotEmpty(message = "A nome não pode ser nulo")
			String nomeFantasia){
		return repository.listarPor("%" + nomeFantasia + "%");
	}
	
	public void excluirPor(
			@NotNull(message = "O id é obrigatório")
			Integer id) {
		this.repository.deleteById(id);
	}

}
