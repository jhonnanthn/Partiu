package br.com.usjt.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import br.com.usjt.web.model.Item;
import br.com.usjt.web.model.Restaurante;

public interface RestauranteMapper {
	//nao usado
	void createRestaurante(
			@Param("restaurante") Restaurante restaurante);
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

}
