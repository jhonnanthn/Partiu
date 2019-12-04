package br.com.usjt.web.dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.usjt.web.model.Item;
import br.com.usjt.web.model.PerfisSingleton;
import br.com.usjt.web.model.Restaurante;
import br.com.usjt.web.model.Usuario;
import br.com.usjt.web.service.RecomendacaoMapper;
import br.com.usjt.web.service.RestauranteMapper;
import br.com.usjt.web.service.UsuarioMapper;

public class RecomendacaoDAO {
	private SqlSessionFactory sqlSessionFactory;
	private final DecimalFormat df = new DecimalFormat("#.###");
	static PerfisSingleton singleton = PerfisSingleton.getInstance();

	public RecomendacaoDAO() {
		sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
	}

	public List<Restaurante> getRecomendacaoDiaSemana() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			// TODO mapper getRecomendacaoDiaSemana()
			RecomendacaoMapper recomendacaoMapper = session.getMapper(RecomendacaoMapper.class);
			List<Restaurante> restaurante = recomendacaoMapper.getRecomendacaoDiaSemana();
			return restaurante;
		} finally {
			session.close();
		}
	}

	public List<Restaurante> getRecomendacaoMaisVisitados() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RecomendacaoMapper recomendacaoMapper = session.getMapper(RecomendacaoMapper.class);
			List<Restaurante> restaurante = recomendacaoMapper.getRecomendacaoMaisVisitados();
			return restaurante;
		} finally {
			session.close();
		}
	}

	public List<Restaurante> getRecomendacaoVisitadoRecentemente(int idUsuario) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RecomendacaoMapper recomendacaoMapper = session.getMapper(RecomendacaoMapper.class);
			List<Restaurante> restaurante = recomendacaoMapper.getRecomendacaoVisitadoRecentemente(idUsuario);
			return restaurante;
		} finally {
			session.close();
		}
	}

	public List<Restaurante> getRecomendacaoEspecialidadeUsuario(int idUsuario) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RecomendacaoMapper recomendacaoMapper = session.getMapper(RecomendacaoMapper.class);
			List<Restaurante> restaurante = recomendacaoMapper.getRecomendacaoEspecialidadeUsuario(idUsuario);
			return restaurante;
		} finally {
			session.close();
		}
	}

	public List<Restaurante> getRecomendacaoRestauranteAvaliado() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RecomendacaoMapper recomendacaoMapper = session.getMapper(RecomendacaoMapper.class);
			List<Restaurante> restaurantes = recomendacaoMapper.getRecomendacaoRestauranteAvaliado();
			System.out.println(restaurantes.size());
			return restaurantes;
		} finally {
			session.close();
		}
	}

	public void getAllPerfis() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			System.out.println("Pegando Perfis Todos Usuarios");
			PerfisSingleton singleton = PerfisSingleton.getInstance();
			RecomendacaoMapper recomendacaoMapper = session.getMapper(RecomendacaoMapper.class);
			UsuarioMapper usuarioMapper = session.getMapper(UsuarioMapper.class);

			List<Usuario> usuarios = usuarioMapper.getIdsClientes();
			for (Usuario u : usuarios) {
				List<Item> itens = recomendacaoMapper.getScoreByEspecialidadeUsuario(u.getId());
				HashMap<String, Double> perfil = calculatePerfil(itens);
				singleton.putPerfil(u.getId(), perfil);
				System.out.println(" -- Perfil Usuario " + u.getId() + " Feito");
			}
		} finally {
			session.close();
		}
	}

	public List<Restaurante> restaurantesByScoreContent(int idUsuario) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RecomendacaoMapper recomendacaoMapper = session.getMapper(RecomendacaoMapper.class);
			RestauranteMapper restauranteMapper = session.getMapper(RestauranteMapper.class);

			HashMap<String, Double> perfil;
			PerfisSingleton perfisSingleton = PerfisSingleton.getInstance();

			List<Item> itens = recomendacaoMapper.getScoreByEspecialidadeUsuario(idUsuario);
//			perfil = perfisSingleton.getPerfil(idUsuario);
			perfil = calculatePerfil(itens);
			perfisSingleton.putPerfil(idUsuario, perfil);

			HashMap<String, List<String>> restaurantesFormatados;
//			if(perfisSingleton.getRestaurantes() == null) {
//			System.out.println("Restaurantes Null");
			List<Restaurante> restaurantesNaoFormatados = restauranteMapper.getRestaurantesEspecialidades();
			restaurantesFormatados = new HashMap<String, List<String>>();
			for (Restaurante r : restaurantesNaoFormatados) {
				if (restaurantesFormatados.containsKey(r.getCnpj())) {
					restaurantesFormatados.get(r.getCnpj()).add(r.getEspecialidade());
				} else {
					restaurantesFormatados.put(r.getCnpj(), new ArrayList<String>());
					restaurantesFormatados.get(r.getCnpj()).add(r.getEspecialidade());
				}
			}
//				perfisSingleton.setRestaurantes(restaurantesFormatados);

//			}else {
//				restaurantesFormatados = perfisSingleton.getRestaurantes();
//				System.out.println("Restaurantes Existem");
//			}
			List<String> listCnpj = new ArrayList<String>();
			if (!perfil.isEmpty()) {
				listCnpj = restaurantesByScoreContent(perfil, restaurantesFormatados);
			}
			return recomendacaoMapper.getRestaurantesByCnpjs(listCnpj);
		} finally {
			session.close();
		}
	}

	// getIdsClientes
	public static HashMap<String, Double> calculatePerfil(List<Item> itens) {
		HashMap<String, Double> perfil = new HashMap<String, Double>();
		// tratando para ser (0.0 - 1.0)
		double total = 0;
		for (Item i : itens) {
			total += i.getScore();

		}
		for (Item i : itens) {
			i.setScore(i.getScore() / total);
			perfil.put(i.getEspecialidade(), i.getScore());
		}
		return perfil;
	}

	private List<String> restaurantesByScoreContent(HashMap<String, Double> perfil,
			HashMap<String, List<String>> restaurantes) {
		try {
			HashMap<String, Double> restauranteScore = new HashMap<String, Double>();
			Iterator it = restaurantes.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry pair = (Map.Entry) it.next();
				Double score = 0.0;
				for (String especialidade : (List<String>) pair.getValue()) {
					if (perfil.containsKey(especialidade))
						score += 1 * perfil.get(especialidade);
				}
//				System.out.println(pair.getKey() + ": " + score);
				restauranteScore.put((String) pair.getKey(), score);
				it.remove();
			}
			restauranteScore = sortHashMapByValues(restauranteScore);
			List<String> cnpjs = new ArrayList<>();
			Iterator it2 = restauranteScore.entrySet().iterator();
			int count = 0;
			while (it2.hasNext() && count < 8) {
				Map.Entry pair = (Map.Entry) it2.next();
				cnpjs.add((String) pair.getKey());
//				System.out.println(((String) pair.getKey()) + " : " + pair.getValue());
				it2.remove();
				count++;
			}
			return cnpjs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public LinkedHashMap<String, Double> sortHashMapByValues(HashMap<String, Double> passedMap) {
		List<String> mapKeys = new ArrayList<>(passedMap.keySet());
		List<Double> mapValues = new ArrayList<>(passedMap.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);
		Collections.reverse(mapValues);

		LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<>();

		Iterator<Double> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Double val = valueIt.next();
			Iterator<String> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				String key = keyIt.next();
				Double comp1 = passedMap.get(key);
				Double comp2 = val;

				if (comp1.equals(comp2)) {
					keyIt.remove();
					sortedMap.put(key, val);
					break;
				}
			}
		}
		return sortedMap;
	}

	public int collabFiltering(int idUsuario) {

		getAllPerfis();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			HashMap<Integer, HashMap<String, Double>> perfis = singleton.getPerfis();
			HashMap<String, Double> perfil = singleton.getPerfil(idUsuario);

			System.out.println("Fazendo comparacoes id = " + idUsuario);

			Iterator it1 = perfis.entrySet().iterator();
			System.out.println("Size perfis: " + perfis.size());
			int maiorId = 0;
			double maiorScore = 0.0;
			while (it1.hasNext()) {
				Map.Entry<Integer, HashMap<String, Double>> par1 = (Entry<Integer, HashMap<String, Double>>) it1.next();

				// ----------------
				double score = 0;

				Iterator<Entry<String, Double>> it = par1.getValue().entrySet().iterator();
				while (it.hasNext()) {
					HashMap.Entry par2 = (Map.Entry) it.next();
					if (perfil.containsKey(par2.getKey())) {
						try {
							score += perfil.get(par2.getKey())
									- diff((Double) par2.getValue(), perfil.get(par2.getKey()));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
//					it.remove(); // avoids a ConcurrentModificationExceptio
				}

				System.out.print(("Vendo id " + par1.getKey()));
				System.out.println(" | ScoreSimilaridade: " + df.format(score));
				if (score > maiorScore && par1.getKey() != idUsuario) {
					maiorScore = score;
					maiorId = par1.getKey();
				}
				// ------------------------
//				it1.remove();
			}
			System.out.println("Maior Score: id=" + maiorId + ", score=" + df.format(maiorScore));
			// ----------------
			return maiorId;
		} finally {
			session.close();
		}
	}

	private static double diff(Double x, Double y) {
		if (y == null) {
			return x;
		} else {
			return (x < y) ? y - x : x - y;
		}
	}

}
