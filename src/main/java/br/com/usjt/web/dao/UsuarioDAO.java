package br.com.usjt.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import br.com.usjt.web.model.Usuario;
import br.com.usjt.web.service.UsuarioMapper;

public class UsuarioDAO {
	private SqlSessionFactory sqlSessionFactory;

	public UsuarioDAO() {
		sqlSessionFactory = ConnectionFactory.getSqlSessionFactory();
	}
	
	public void createUsuario(Usuario usuario) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper UsuarioMapper = session.getMapper(UsuarioMapper.class);
			UsuarioMapper.createUsuario(usuario);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	public void updateUsuario(Usuario usuario) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper UsuarioMapper = session.getMapper(UsuarioMapper.class);
			UsuarioMapper.updateUsuario(usuario);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	public List<Usuario> getUsuarioByParameter(String parametro, String tipo) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper UsuarioMapper = session.getMapper(UsuarioMapper.class);
			List<Usuario> Usuario = UsuarioMapper.getUsuarioByParameter(parametro, tipo);
			return Usuario;
		} finally {
			session.close();
		}
	}
	
	public Usuario getUsuarioOnLogin(String email, String senha) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			UsuarioMapper UsuarioMapper = session.getMapper(UsuarioMapper.class);
			Usuario Usuario = UsuarioMapper.getUsuarioOnLogin(email, senha);
			return Usuario;
		} finally {
			session.close();
		}
	}
}
