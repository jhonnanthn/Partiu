package br.com.usjt.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.dao.UsuarioDAO;
import br.com.usjt.web.model.Usuario;

@Resource
public class UsuarioController {
	@Inject
	Result result;

	public void index() {
		result.redirectTo(this).login();
	}

	@Path("/login")
	public void login() {
		System.out.println("teste");
	}

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

	// Segundo Semestre
	@Path("/createUsuario")
	public void createUsuario(Usuario usuario) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		try {
			usuarioDAO.createUsuario(usuario);
			result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Usuario criado com sucesso").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// Segundo Semestre
	@Path("/updateUsuario")
	public void updateCliente(Usuario usuario) {
		UsuarioDAO clientDAO = new UsuarioDAO();
		try {
			clientDAO.updateCliente(usuario);
			result.use(Results.json()).withoutRoot().from("NOTIFICACAO: Alterado com sucesso").serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	// Não Utilizado
	@Path("/getUsuarios")
	public void getUsuarios() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			List<Usuario> usuarios = usuarioDAO.getUsuarios();
			result.use(Results.json()).withoutRoot().from(usuarios).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}

	@Path("/getUsuarioByEmail")
	public void getusuarioByEmail(String email) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		try {
			Usuario usuario = usuarioDAO.getusuarioByEmail(email);
			result.use(Results.json()).withoutRoot().from(usuario).serialize();
		} catch (Exception e) {
			result.use(Results.json()).withoutRoot().from("ERRO: " + e.getMessage()).serialize();
		}
	}
}
