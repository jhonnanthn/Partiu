package br.com.usjt.web.controller;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Options;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.UsuarioDAO;
import br.com.usjt.web.model.Usuario;

@Resource
public class AdminController {

	@Inject
	Result result;
	
	

	// createAvaliacao informada pelo usuário
	// retorna a avaliacao criada

	@Options("/*")
	@Path("/*")
	public void options() {
		result.use(Results.status()).header("Access-Control-Allow-Origin", "*");
	}

	@Path("/loginAdmin")
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

}
