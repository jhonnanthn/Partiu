package br.com.usjt.partiu.web.controller;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.naming.NamingException;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.partiu.web.dao.UsuarioDao;

@Resource
@Path("/apis")
public class ApisController {

	@Inject
	private Result result;
	
	public ApisController(){
	}
	
	@Path("/api/usuarioById")
	public void usuarioById(String id_usuario) throws SQLException, NamingException {
		result.use(Results.json()).withoutRoot().from(id_usuario).serialize();
	}
	
	@Path("/api/teste")
	public void teste(){
		result.use(Results.json()).withoutRoot().from("está funcionando").serialize();
	}
}
