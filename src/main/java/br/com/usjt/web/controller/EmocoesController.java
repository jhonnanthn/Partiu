package br.com.usjt.web.controller;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import com.google.inject.Inject;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.usjt.web.model.Usuario;

@Resource
public class EmocoesController {
	
	

	@Inject
	Result result;

	@Path("/test")
	public void test(String email, String senha) {
		
		PythonInterpreter interp = new PythonInterpreter();
		PyObject po;
		result.use(Results.status()).header("Access-Control-Allow-Origin", "*");
		interp.exec("import sys");
        interp.exec("from java.util import Random");
        interp.exec("a = Random().nextDouble()*100");
        interp.set("b", Integer.parseInt("139"));
        
        //print sai no console java
        interp.exec("print 'duahduha'");
        interp.exec("x = 2+2");
        
        PyObject x = interp.get("x");
        PyObject a = interp.get("a");
        Usuario u = new Usuario();
        

        interp.close();
		result.use(Results.json()).withoutRoot().from("x:"+x+", a:"+a+" b:"+interp.get("b")).serialize();
	}
}
