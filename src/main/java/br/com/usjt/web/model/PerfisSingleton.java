package br.com.usjt.web.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.caelum.vraptor.ioc.ApplicationScoped;

@ApplicationScoped
public final class PerfisSingleton {

	private static PerfisSingleton singleton;
	private HashMap<Integer, HashMap<String, Double>> perfis;
	private HashMap<String, List<String>> restaurantes;

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
	
	public HashMap<String, List<String>> getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(HashMap<String, List<String>> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public static void printPerfil(HashMap<String,Double> perfil) {
		Iterator it = perfil.entrySet().iterator();
		System.out.println("Print Perfil");
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey()+" : "+pair.getValue());
			it.remove();
		}
	}
	
}