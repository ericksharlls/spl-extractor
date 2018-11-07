package br.ufrn.ct.cronos.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import br.ufrn.ct.cronos.dao.PermissaoUsuarioDao;
import br.ufrn.ct.cronos.entity.PermissaoUsuario;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class PermissaoUsuarioDaoImpl extends AbstractDao<Long, PermissaoUsuario> implements PermissaoUsuarioDao {
	
	public PermissaoUsuarioDaoImpl() {
		super();
	}
	
	@Override
	public List<PermissaoUsuario> getPorUsuario(Long idUsuario){
      String sql = "select pu from PermissaoUsuario pu WHERE pu.idUsuario = :idUsuario";
		List<PermissaoUsuario> lista = new ArrayList<PermissaoUsuario>();
		
		Query query = getSession().createQuery(sql);
		query.setLong("idUsuario", idUsuario);
		lista = query.list();
		
		return lista;
	}

}

