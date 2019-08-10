package br.com.usjt.web.controller;

import java.util.List;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.RecomendacaoDAO;
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
		result.use(Results.json()).withoutRoot().from("Endereco Inválido").serialize();
	}
	
	@Path("/getPerfis")
	public void getPerfis() {
		long startTime = System.currentTimeMillis();
		PerfisSingleton singleton = PerfisSingleton.getInstance();
		RecomendacaoDAO dao = new RecomendacaoDAO();
		dao.getAllPerfis();
		long endTime = System.currentTimeMillis();
		result.use(Results.json()).withoutRoot().from("Pefis Inicializados em "+(endTime-startTime)+"ms").serialize();
	}

	
	// verifica se login existe
	// retorna usuario logado ou notificação de usuario invalido (email ou senha errado)
	@Path("/login")
	public void login(String email, String senha) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			Usuario usuario = usuarioDAO.getUsuarioOnLogin(email, senha);
			if (usuario != null) {
				result.use(Results.json()).withoutRoot().from(usuario).serialize();
			}else {
				result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Usuario inválido!").serialize();
			}
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}

	//Nao Usado
	@Path("/logout")
	public void logout() {
	}
	
	//Segundo Semestre
	@Path("/createUsuario")
	public void createUsuario(String tipo, String cpf, String nome, String dta_nascimento, String email, String ddd, String telefone, 
			String genero, String senha, String logradouro, String numero, String complemento, String bairro, String cidade,
			String uf, String cep) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			usuarioDAO.createEndereco(logradouro, numero, complemento, bairro, cidade, uf, cep);
			usuarioDAO.createUsuario(tipo, cpf, nome, dta_nascimento, email, ddd, telefone, genero, senha);
			result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Usuario criado com sucesso").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}

	//Segundo Semestre
	@Path("/updateUsuario")
	public void updateUsuario(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			usuarioDAO.updateUsuario(usuario);
			result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Alterado com sucesso").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: "+e.getMessage()).serialize();
		}
	}
	
	// get usuario por parametro e valor
	@Path("/getUsuario")
	public void getUsuarioByParameter(String variavel, String valor) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			List<Usuario> usuario = usuarioDAO.getUsuarioByParameter(variavel, valor);
			result.use(Results.json()).withoutRoot().from(usuario).include("endereco").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: ID inexistente para Usuário; ID: "+valor+";	\n"+e.getMessage()).serialize();
		}
	}
}
