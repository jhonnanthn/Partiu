package br.com.usjt.web.controller;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.RecomendacaoDAO;
import br.com.usjt.web.dao.RestauranteDAO;
import br.com.usjt.web.dao.UsuarioDAO;
import br.com.usjt.web.model.PerfisSingleton;
import br.com.usjt.web.model.Usuario;

@Resource
public class UsuarioController {

	@Inject
	Result result;
	
	// Teste para ver se webservice está rodando; Deve retornar "Endereco Invalido"
	@Path("/index")
	public void index() {
		result.use(Results.status()).header("Access-Control-Allow-Origin", "*");
		result.use(Results.json()).withoutRoot().from("Endereco Inválido").serialize();
	}

	@Path("/getPerfis")
	public void getPerfis() {
		long startTime = System.currentTimeMillis();
		PerfisSingleton singleton = PerfisSingleton.getInstance();
		RecomendacaoDAO dao = new RecomendacaoDAO();
		dao.getAllPerfis();
		long endTime = System.currentTimeMillis();
		result.use(Results.json()).withoutRoot().from("Pefis Inicializados em " + (endTime - startTime) + "ms")
				.serialize();
	}

	// verifica se login existe
	// retorna usuario logado ou notificação de usuario invalido (email ou senha
	// errado)
	@Path("/login")
	public void login(String email, String senha) {		
		result.use(Results.status()).header("Access-Control-Allow-Origin", "*");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			Usuario usuario = usuarioDAO.getUsuarioOnLogin(email, senha);
			if (usuario != null) {
				result.use(Results.json()).withoutRoot().from(usuario).serialize();
			} else {
				result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Usuario inválido!").serialize();
			}
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// Nao Usado
	@Path("/logout")
	public void logout() {
	}

	// Segundo Semestre
	@Path("/createUsuario")
	public void createUsuario(String tipo, String cpf, String nome, String dta_nascimento, String email, String ddd,
			String telefone, String genero, String senha, String logradouro, String numero, String complemento,
			String bairro, String cidade, String uf, String cep, String cnpj) {
		result.use(Results.status()).header("Access-Control-Allow-Origin", "*");
		boolean createEndereco = false;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		try {
			if(!(logradouro == null && numero == null && complemento == null &&
					bairro == null && cidade == null && cidade == null && uf == null && cep == null)) {
				usuarioDAO.createEndereco(logradouro, numero, complemento, bairro, cidade, uf, cep);
				createEndereco = true;
			}
			usuarioDAO.createUsuario(tipo, cpf, nome, dta_nascimento, email, ddd, telefone, genero, senha, createEndereco);

			if(!tipo.contentEquals("cliente")){
				restauranteDAO.vincularFuncionarioRestaurante(cnpj);
			}
			result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Usuario criado com sucesso").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}
	
	@Path("/updateUsuario")
	public void updateUsuario(String id, String idEndereco, String tipo, String cpf, String nome, String dta_nascimento, String email, String ddd,
			String telefone, String genero, String senha, String logradouro, String numero, String complemento,
			String bairro, String cidade, String uf, String cep, String cnpj, String status) {
		result.use(Results.status()).header("Access-Control-Allow-Origin", "*");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		try {	
			
			usuarioDAO.updateUsuario(id, tipo, cpf, nome, dta_nascimento, email, ddd, telefone, genero, senha);
			if(!(idEndereco=="" || idEndereco == null))
			usuarioDAO.updateEndereco(idEndereco, logradouro, numero, complemento, bairro, cidade, uf, cep);

			if(!tipo.contentEquals("cliente")){
				restauranteDAO.updateVincularFuncionarioRestaurante(id, status);
			}
			Usuario usuario = usuarioDAO.getUsuarioByParameter("id", id).get(0);
			result.use(Results.json()).withoutRoot().from(usuario).include("endereco").serialize();
		} catch (Exception e) {
			e.printStackTrace();
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}


	@Path("/updateStatusFuncionario")
	public void updateStatusFuncionario(String id, String status, String cnpj) {
		result.use(Results.status()).header("Access-Control-Allow-Origin", "*");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		RestauranteDAO restauranteDAO = new RestauranteDAO();
		try {	
			restauranteDAO.updateVincularFuncionarioRestaurante(id, status);
			List<Usuario> funcs = usuarioDAO.getUsuarioByParameter("cnpj", cnpj);
			result.use(Results.json()).withoutRoot().from(funcs).serialize();
		} catch (Exception e) {
			e.printStackTrace();
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}
	
	// get usuario por parametro e valor
	@Path("/getUsuario")
	public void getUsuarioByParameter(String variavel, String valor) {
		result.use(Results.status()).header("Access-Control-Allow-Origin", "*");
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			List<Usuario> usuario = usuarioDAO.getUsuarioByParameter(variavel, valor);
			result.use(Results.json()).withoutRoot().from(usuario).include("endereco").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot()
					.from("ERRO: ID inexistente para Usuário; ID: " + valor + ";	\n" + e.getMessage()).serialize();
		}
	}
}
