package br.com.usjt.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import br.com.usjt.web.model.Item;
import br.com.usjt.web.model.Restaurante;

public interface RestauranteMapper {

	void createRestaurante(
			@Param("cnpj") String cnpj,
			@Param("codigoComanda") String codigoComanda,
			@Param("razaoSocial") String razaoSocial,
			@Param("nomeFantasia") String nomeFantasia,
			@Param("qntMesas") String qntMesas,
			@Param("logotipo") String logotipo,
			@Param("descricao") String descricao,
			@Param("status") String status);
	
	//retorna restaurante referente a um garcom
	Restaurante getRestauranteByIdGarcom(
			@Param("idGarcom")int idGarcom);
	
	//retorna qtd de mesas do restaurante (int)
	int getRestauranteMesas(
			@Param("idGarcom")int idGarcom);

	//retorna todos os restaurante
	List<Restaurante> getRestaurantes();
	
	//Lista itens do restaurante
	List<Item> getItensRestaurante(
			 @Param("cnpj") long cnpj);
	
	Restaurante getRestauranteByCnpj(
			 @Param("cnpj") long cnpj);
	
	List<Restaurante> getHorarioRestauranteByCnpj(
			 @Param("cnpj") long cnpj);
	List<Restaurante> getRestaurantesEspecialidades();
	
	void updateRestaurante(
			@Param("cnpj") String cnpj, 
			@Param("codigoComanda") String codigoComanda, 
			@Param("razaoSocial") String razaoSocial, 
			@Param("nomeFantasia") String nomeFantasia, 
			@Param("qntMesas") String qntMesas,
			@Param("logotipo") String logotipo, 
			@Param("descricao") String descricao, 
			@Param("status") String status);
}
