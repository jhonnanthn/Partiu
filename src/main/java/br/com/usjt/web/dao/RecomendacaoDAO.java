package br.com.usjt.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.usjt.web.model.Restaurante;
import br.com.usjt.web.service.EnderecoMapper;
import br.com.usjt.web.service.RecomendacaoMapper;
import br.com.usjt.web.service.RestauranteMapper;
public class RecomendacaoDAO {
	private SqlSessionFactory sqlSessionFactory;

	public RecomendacaoDAO() {
		sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
	}
	
	public List<Restaurante> getRecomendacaoDiaSemana() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			//TODO mapper getRecomendacaoDiaSemana()
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

	public List<Restaurante> getRecomendacaoEspecidadeUsuario(int idUsuario) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			RecomendacaoMapper recomendacaoMapper = session.getMapper(RecomendacaoMapper.class);
			List<Restaurante> restaurante = recomendacaoMapper.getRecomendacaoEspecidadeUsuario(idUsuario);
			return restaurante;
		} finally {
			session.close();
		}
	}
	
}
