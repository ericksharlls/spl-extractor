package br.ufrn.ct.cronos.dao.impl;

import org.hibernate.Query;
import br.ufrn.ct.cronos.dao.PredioDao;
import br.ufrn.ct.cronos.entity.Predio;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class PredioDaoImpl extends AbstractDao<Long, Predio> implements PredioDao {

	public PredioDaoImpl() {
		super();
	}
	
	@Override
	public Predio getPredioBySala(Long idPredio){
		String sql = "select p from Sala s, Predio p WHERE s.idPredio = :idPredio";
		Query query = getSession().createQuery(sql);
		
		query.setLong("idPredio", idPredio);
		
		Predio predio = (Predio) query.uniqueResult();
		
		return predio;
	}

}
