package br.com.senai.senaiapi.controller;

import java.net.URI;
import java.util.Map;

import org.json.JSONObject;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.senai.senaiapi.entity.Motorista;
import br.com.senai.senaiapi.service.MotoristaService;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {
	
	@Autowired
	private MotoristaService service;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private MapConverter mapConverter;
	
	@PostMapping
	public ResponseEntity<?> inserir(
			@RequestBody
			Map<String, Object> motoristaMap){
		Motorista novoMotorista = mapper
				.convertValue(motoristaMap, Motorista.class);		
		Motorista motoristaSalvo = service.inserir(novoMotorista);
		return ResponseEntity.created(
					URI.create(
							"/motoristas/id/" + motoristaSalvo.getId()
					)
				).build();
	}
	
	@PutMapping
	public ResponseEntity<?> alterar(
			@RequestBody 
			Map<String, Object> motoristaMap){
		Motorista motoristaSalvo = mapper
				.convertValue(motoristaMap, Motorista.class);
		Motorista motoristaAtualizado = service.alterar(motoristaSalvo);
		return ResponseEntity.ok(mapConverter.toJsonMap(motoristaAtualizado));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> buscarPor(
			@PathVariable("id") Integer id) 
					throws JsonProcessingException{
		Motorista motoristaEncontrado = service.buscarPor(id);
		String json = mapper.writeValueAsString(motoristaEncontrado);
		JSONObject jsonObj = new JSONObject(json);
		return ResponseEntity.ok(jsonObj.toMap());		
	}
	
	@GetMapping(value = "/nome/{nome}")
	public ResponseEntity<?> listarPor(
			@PathVariable(name = "nome")
			String nome){
		return ResponseEntity.ok(mapConverter
				.toJsonList(service.listarPor(nome)));
	}
	
	@DeleteMapping(value = "/id/{id}")
	public ResponseEntity<?> excluirPor(
			@PathVariable(name = "id")
			Integer id){
		this.service.excluirPor(id);
		return ResponseEntity.noContent().build();
	}
	
}
