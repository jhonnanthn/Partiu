package br.com.usjt.web.controller;

import javax.servlet.http.HttpSession;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.UsuarioDAO;

@Resource
public class UsuarioController {

	@Inject
	Result result;
	
	public void index() {
		result.redirectTo(this).login();
	}

	@Path("/login")
	public void login() {}
	
	@Path("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
		result.redirectTo(this).login();
	}
	
	@Path("/teste")
	public void teste(HttpSession session, String matricula) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		System.out.println("teste");
		result.use(Results.json()).withoutRoot().from("success").serialize();
	}
}
