package br.ufrn.ct.cronos.dao;

import br.ufrn.ct.cronos.entity.Usuario;
import dev.home.componente.dao.hibernate.Dao;

public interface UsuarioDao extends Dao<Long, Usuario> {
  
	public Usuario getByFuncionario(Long idFuncionario);

	public Usuario getByLogin(String login);
  
}

