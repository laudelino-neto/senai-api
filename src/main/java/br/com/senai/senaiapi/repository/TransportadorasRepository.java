package br.com.senai.senaiapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.senai.senaiapi.entity.Transportadora;

public interface TransportadorasRepository 
		extends JpaRepository<Transportadora, Integer>{
	
	@Query(value = 
			"SELECT t "
			+ "FROM Transportadora t "
			+ "WHERE Upper(t.nomeFantasia) LIKE Upper(:nome)")
	public List<Transportadora> listarPor(
			@Param("nome")
			String nomeFantasia);
	
	@Query(value = 
			"SELECT t "
			+ "FROM Transportadora t "
			+ "WHERE t.id = :id")
	public Transportadora buscarPor(@Param("id") Integer id);
	
}
