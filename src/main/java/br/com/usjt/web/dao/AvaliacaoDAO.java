package br.com.usjt.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import br.com.usjt.web.model.Avaliacao;
import br.com.usjt.web.service.AvaliacaoMapper;

public class AvaliacaoDAO {
	private SqlSessionFactory sqlSessionFactory;

	public AvaliacaoDAO() {
		sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
	}

	// cria Avaliacao
	public void createAvaliacao(int idCliente, int idComanda, int avEstabelecimento, int avFuncionario,
			String descEstabelecimento, String descFuncionario) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			AvaliacaoMapper avaliacaoMapper = session.getMapper(AvaliacaoMapper.class);
			avaliacaoMapper.createAvaliacao(idCliente, idComanda, avEstabelecimento, avFuncionario, descEstabelecimento,
					descFuncionario);
			session.commit();
		} finally {
			session.close();
		}
	}

	public List<Avaliacao> getAvaliacaoByCnpj(String cnpj) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			AvaliacaoMapper avaliacaoMapper = session.getMapper(AvaliacaoMapper.class);
			List<Avaliacao> avaliacao = avaliacaoMapper.getAvaliacaoByCnpj(cnpj);
			return avaliacao;
		} finally {
			session.close();
		}
	}
	
	public List<Avaliacao> getAvaliacoesTodas() {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			AvaliacaoMapper avaliacaoMapper = session.getMapper(AvaliacaoMapper.class);
			List<Avaliacao> avaliacao = avaliacaoMapper.getAvaliacoesTodas();
			return avaliacao;
		} finally {
			session.close();
		}
	}
}
