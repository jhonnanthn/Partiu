package br.com.usjt.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;

@Intercepts
public class AutenticacaoInterceptor implements Interceptor {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private final HttpSession session;
	private final Result result;

	public AutenticacaoInterceptor(HttpSession session, Result result, HttpServletRequest request,
			HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = session;
		this.result = result;
//		System.out.println("Intercept Construtor");
	}

	@Override
	public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance)
			throws InterceptionException {
//		result.redirectTo(UsuarioController.class).index();

		String origin = request.getHeader("origin") != null ? request.getHeader("origin") : "*";
		System.out.println(origin);

		response.addHeader("Access-Control-Allow-Origin", origin);
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Expose-Headers", "Content-Type, Location");
//		System.out.println("Intercept intercept");
	}

	@Override
	public boolean accepts(ResourceMethod method) {

//		Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
//		if (usuarioLogado == null && method.containsAnnotation(Restrito.class)) {
//			return true;
//		} else {
//			return false;
//		}
		
//		System.out.println("Intercept Accept");
		return false;
	}

}
