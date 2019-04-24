package br.com.usjt.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import br.com.usjt.web.model.Restaurante;

public interface RestauranteMapper {

	void createRestaurante(
			@Param("restaurante") Restaurante restaurante);
	
	Restaurante getRestauranteByIdGarcom(
			@Param("idGarcom")int idGarcom);
	
	int getRestauranteMesas(
			@Param("idGarcom")int idGarcom);

	List<Restaurante> getRestaurante();
}
