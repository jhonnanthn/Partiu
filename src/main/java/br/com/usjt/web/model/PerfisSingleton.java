package br.com.usjt.web.model;

import java.util.HashMap;

import br.com.caelum.vraptor.ioc.ApplicationScoped;

@ApplicationScoped
public final class PerfisSingleton {

	private static PerfisSingleton singleton;
	private HashMap<Integer, HashMap<String, Double>> perfis;

	// <idUsuario, perfil<especialidade, score>>
	public PerfisSingleton() {
		System.out.println("Singleton Perfis Criado");
		this.perfis = new HashMap<Integer, HashMap<String, Double>>();
	}

	public static synchronized PerfisSingleton getInstance() {
		if (singleton == null)
			singleton = new PerfisSingleton();
		return singleton;
	}

	public HashMap<Integer, HashMap<String, Double>> getPerfis() {
		return perfis;
	}

	public void putPerfil(int idUsuario, HashMap<String, Double> perfil) {
		perfis.put(idUsuario, perfil);
	}

	public HashMap<String, Double> getPerfil(int idUsuario) {
		return perfis.get(idUsuario);
	}
	
}