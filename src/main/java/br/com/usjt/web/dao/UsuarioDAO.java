package br.com.usjt.web.dao;

import org.apache.ibatis.session.SqlSessionFactory;

public class UsuarioDAO {
	private SqlSessionFactory intranetSqlSessionFactory;

	public UsuarioDAO() {
		intranetSqlSessionFactory = IntranetConnectionFactory.getSqlSessionFactory();
	}
}
