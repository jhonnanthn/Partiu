<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c"     uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="login-body">
	<div class="container">
		<c:if test="${sistemasPermitidos ne null}">
			
		</c:if>
		<c:if test="${sistemasPermitidos eq null}">
			<form class="form-signin" action="${linkTo[MaxController].login}" method="post">
			<h1 style="text-align: center; padding: 10px; font-size: 80px">
				<strong> MA<span style="color: #FF6C60">X</span></strong>
			</h1>
			<div class="login-wrap">
				<label for="name">Login : </label> <input type="text"
					class="form-control" placeholder="Login" required autofocus
					name="login" id="login"> <label for="senha">Senha : </label> <input
					type="password" class="form-control" placeholder="Senha" required
					name="senha"> <input name="acao" value="0" type="hidden">
				<c:if test="${erroLogin ne null}">
					<div class="alert alert-dismissable alert-danger" id="msg-return">${erroLogin}</div>
				</c:if>
				<button class="btn btn-lg btn-login btn-block">Acessar</button>
				<div style="position: absolute; right: 5px; bottom: 5px">
					<img src="/max/assets/img/logo/logo-01.png" width="100">
				</div>
			</div>
		</form>
		</c:if>
	</div>
</div>
<script>
	$(".btn-login").click(function(e) {
		localStorage.setItem('portalJavaLogin', $("#login").val());
	});
</script>