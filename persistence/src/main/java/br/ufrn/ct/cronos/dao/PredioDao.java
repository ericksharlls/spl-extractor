package br.ufrn.ct.cronos.dao;

import br.ufrn.ct.cronos.entity.Predio;
import dev.home.componente.dao.hibernate.Dao;

public interface PredioDao extends Dao<Long, Predio>{
  
	public Predio getPredioBySala(Long idPredio);
		
}
