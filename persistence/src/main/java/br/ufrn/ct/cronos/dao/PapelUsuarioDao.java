package br.ufrn.ct.cronos.dao;

import br.ufrn.ct.cronos.entity.PapelUsuario;
import dev.home.componente.dao.hibernate.Dao;

public interface PapelUsuarioDao extends Dao<Long, PapelUsuario>{
  
	public PapelUsuario getByUsuario(Long idUsuario);
		
}
