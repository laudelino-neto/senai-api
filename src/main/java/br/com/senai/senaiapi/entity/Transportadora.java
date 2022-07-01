package br.com.senai.senaiapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "Transportadora")
@Table(name = "transportadoras")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Transportadora {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Column(name = "razao_social")
	@NotEmpty(message = "A razão social é obrigatória")
	private String razaoSocial;
	
	@Column(name = "nome_fantasia")
	@NotEmpty(message = "O nome fantasia é obrigatório")
	private String nomeFantasia;
	
	@Column(name = "cnpj")
	@NotEmpty(message = "O cnpj é obrigatório")
	private String cnpj;
	
}
