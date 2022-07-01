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
import br.com.senai.senaiapi.entity.Motorista;
import br.com.senai.senaiapi.repository.MotoristasRepository;

@Service
@Validated
public class MotoristaService {
	
	@Autowired
	private MotoristasRepository repository;
	
	public Motorista inserir(
			@Valid
			@NotNull(message = "O novo motorista é obrigatório")
			Motorista novoMotorista) {
		Preconditions.checkArgument(novoMotorista.getId() == null,
				"O id do motorista deve ser nulo");
		Motorista motoristaSalvo = repository.save(novoMotorista);
		return motoristaSalvo;
	}
	
	public Motorista alterar(
			@Valid
			@NotNull(message = "O motorista é obrigatório")
			Motorista motoristaSalvo) {
		Preconditions.checkArgument(motoristaSalvo.getId() != null,
				"O id do motorista não deve ser nulo");
		Motorista motoristaAtualizado = 
				repository.save(motoristaSalvo);
		return motoristaAtualizado;
	}
	
	public void excluirPor(
			@NotNull(message = "O id para exclusão não pode ser nulo")
			Integer id) {
		this.repository.deleteById(id);
	}
	
	public Motorista buscarPor(
			@NotNull(message = "O id para busca não pode ser nulo")
			Integer id) {
		Motorista motoristaEncontrado = repository.buscarPor(id);
		if (motoristaEncontrado == null) {
			throw new RegistroNaoEncontradoException(
					"Não foi encontrado o motorista");
		}
		return motoristaEncontrado;
	}
	
	public List<Motorista> listarPor(
			@NotEmpty(message = "O nome é obrigatório")
			String nome){
		return repository.listarPor("%" + nome + "%");
	}

}
