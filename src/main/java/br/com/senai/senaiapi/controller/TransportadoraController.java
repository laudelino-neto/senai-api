package br.com.senai.senaiapi.controller;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.senai.senaiapi.entity.Transportadora;
import br.com.senai.senaiapi.service.TransportadoraService;

@RestController
@RequestMapping("/transportadoras")
public class TransportadoraController {
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private MapConverter mapConverter;
	
	@Autowired
	private TransportadoraService service;
	
	@PostMapping
	public ResponseEntity<?> inserir(
			@RequestBody
			Map<String, Object> transportadoraMap){
		Transportadora novaTransportadora = mapper.convertValue(
				transportadoraMap, Transportadora.class);
		Transportadora transportadoraSalva = 
				service.inserir(novaTransportadora);
		return ResponseEntity.created(
					URI.create("/transportadoras/id/" 
							+ transportadoraSalva.getId())
				).build();
	}
	
	@PutMapping
	public ResponseEntity<?> alterar(
			@RequestBody 
			Map<String, Object> transportadoraMap){
		Transportadora transportadoraSalva = mapper.convertValue(
				transportadoraMap, Transportadora.class);
		Transportadora transportadoraAtualizada = 
				service.alterar(transportadoraSalva);
		return ResponseEntity.ok(mapConverter
				.toJsonMap(transportadoraAtualizada));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(
			@PathVariable(name = "id")
			Integer id){
		return ResponseEntity.ok(mapConverter
				.toJsonMap(service.buscarPor(id)));
	}
	
	@GetMapping("/nome-fantasia/{nome-fantasia}")
	public ResponseEntity<?> listarPor(
			@PathVariable(name = "nome-fantasia")
			String nomeFantasia){
		return ResponseEntity.ok(mapConverter.toJsonList(
				service.listarPor(nomeFantasia)));
	}
	
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> excluirPor(
			@PathVariable(name = "id")
			Integer id){
		this.service.excluirPor(id);
		return ResponseEntity.noContent().build();
	}

}
