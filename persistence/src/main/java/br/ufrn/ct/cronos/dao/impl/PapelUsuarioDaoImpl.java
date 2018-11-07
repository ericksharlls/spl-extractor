package br.ufrn.ct.cronos.dao.impl;

import org.hibernate.Query;
import br.ufrn.ct.cronos.dao.PapelUsuarioDao;
import br.ufrn.ct.cronos.entity.PapelUsuario;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class PapelUsuarioDaoImpl extends AbstractDao<Long, PapelUsuario> implements PapelUsuarioDao {
	
	public PapelUsuarioDaoImpl() {
		super();
	}
	
	@Override
	public PapelUsuario getByUsuario(Long idUsuario){
      String sql = "select p from PapelUsuario p, PermissaoUsuario pu WHERE p.id = pu.idPapel AND pu.idUsuario = :idUsuario";
		PapelUsuario perfil = new PapelUsuario();
		
		Query query = getSession().createQuery(sql);
		query.setLong("idUsuario", idUsuario);
		perfil = (PapelUsuario) query.uniqueResult();
		
		return perfil;
	}

}

