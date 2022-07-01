package br.com.senai.senaiapi.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity(name = "Motorista")
@Table(name = "motoristas")
public class Motorista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome_completo")
	@NotEmpty(message = "O nome do motorista é obrigatório")
	private String nomeCompleto;
	
	@Column(name = "cnh")
	@NotEmpty(message = "A CNH do motorista é obrigatória")
	private String cnh;
	
	@Column(name = "dt_validade")
	@NotNull(message = "A data de validade da CNH é obrigatória")
	private LocalDate dataDeValidade;

}
