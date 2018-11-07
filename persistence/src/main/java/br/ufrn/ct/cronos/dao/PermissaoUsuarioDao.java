package br.ufrn.ct.cronos.dao;

import java.util.List;
import br.ufrn.ct.cronos.entity.PermissaoUsuario;
import dev.home.componente.dao.hibernate.Dao;

public interface PermissaoUsuarioDao extends Dao<Long, PermissaoUsuario>{
  
	public List<PermissaoUsuario> getPorUsuario(Long idUsuario);
	
}
