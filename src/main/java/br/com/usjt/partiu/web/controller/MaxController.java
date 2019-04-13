package br.com.usjt.partiu.web.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
@Resource
public class MaxController {

	@Inject
	private Result result;
	
	@Path("/")
	public void index() {

	}

	@Path("/login")
	public void login(HttpServletRequest request, HttpServletResponse response, String login, String senha ){
		result.redirectTo(this).index();
	}
}
