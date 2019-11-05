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

	//cria funcionario
	void createFuncionario(
			@Param("tipo") String tipo, 
			@Param("cpf") String cpf, 
			@Param("nome") String nome, 
			@Param("dta_nascimento") String dta_nascimento, 
			@Param("email") String email, 
			@Param("ddd") String ddd, 
			@Param("telefone") String telefone, 
			@Param("genero") String genero, 
			@Param("senha") String senha, 
			@Param("createEndereco") boolean createEndereco);
	
	//vincular funcionario ao restaurante
	void vincularFuncionarioRestaurante(
			@Param("cnpj") String cnpj);
	
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
	
	void updateItem(
			@Param("id") int id,
			@Param("cnpj_restaurante") long cnpj,
			@Param("categoria") String categoria,
			@Param("detalhe") String detalhe,
			@Param("nome") String nome,
			@Param("valor") double valor,
			@Param("status") String status
			);
	
	Item getItemById(
			 @Param("id") int id);
}
