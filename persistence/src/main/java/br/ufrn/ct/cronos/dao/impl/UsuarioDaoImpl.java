package br.ufrn.ct.cronos.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import br.ufrn.ct.cronos.dao.UsuarioDao;
import br.ufrn.ct.cronos.entity.Usuario;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class UsuarioDaoImpl extends AbstractDao<Long, Usuario> implements UsuarioDao {
	
	private static final String ID_FUNCIONARIO = "idFuncionario";
	private static final String LOGIN_USUARIO = "login";
	
	public UsuarioDaoImpl() {
		super();
	}
	
	@Override
	public Usuario getByFuncionario(Long idFuncionario){
		DetachedCriteria criteria = criarDetachedCriteria();
		criteria.add(Expression.eq(UsuarioDaoImpl.ID_FUNCIONARIO, idFuncionario));
		Usuario usuario = (Usuario) findUniqueByDetachedCriteria(criteria);
		return usuario;
	}
	
	@Override
	public Usuario getByLogin(String login){
		DetachedCriteria criteria = criarDetachedCriteria();
		criteria.add(Expression.eq(UsuarioDaoImpl.LOGIN_USUARIO, login));
		Usuario usuario = (Usuario) findUniqueByDetachedCriteria(criteria);
		return usuario;
	}
	
}
