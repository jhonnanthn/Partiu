package br.com.usjt.partiu.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.usjt.partiu.web.service.UsuarioMapper;

public class UsuarioDao {

	private SqlSessionFactory intranetSqlSessionFactory;
	private SqlSession intranetSession;
	private UsuarioMapper intranetMapper;

	public UsuarioDao() {
		intranetSqlSessionFactory = IntranetConnectionFactory.getSqlSessionFactory();
		intranetSession = intranetSqlSessionFactory.openSession();
		intranetMapper = intranetSession.getMapper(UsuarioMapper.class);
	}
	
	public void addAcessoLog(int idUsuario, String mensagem) {
//		intranetMapper.addAcessoLog(idUsuario, mensagem);
		intranetSession.commit();
	}
}
