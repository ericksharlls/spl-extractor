package br.ufrn.ct.cronos.dao;

import java.util.List;
import br.ufrn.ct.cronos.entity.Chave;
import dev.home.componente.dao.hibernate.Dao;

public interface ChaveDao extends Dao<Long, Chave> {
  
	public Chave getByCodigo(String codigo);
	public List<Chave> getBySala(Long idSala);
	public List<Chave> chavesUtilizadas(Long idSala);

}
