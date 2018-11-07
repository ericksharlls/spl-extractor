package br.ufrn.ct.cronos.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import br.ufrn.ct.cronos.dao.ChaveDao;
import br.ufrn.ct.cronos.entity.Chave;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class ChaveDaoImpl extends AbstractDao<Long, Chave> implements ChaveDao {
	
	private static final String ID_SALA = "idSala";
	
	public ChaveDaoImpl() {
		super();
	}
	
	@Override
	public Chave getByCodigo(String codigo){
		String sql = "select c from Chave c WHERE c.codigo = :codigo";
		Query query = getSession().createQuery(sql);
		
		query.setString("codigo", codigo);
		
		Chave chave = (Chave) query.uniqueResult();
		
		return chave;
	}
	
	@Override
	public List<Chave> getBySala(Long idSala){
		DetachedCriteria criteria = criarDetachedCriteria();
		criteria.add(Expression.eq(ChaveDaoImpl.ID_SALA, idSala));
		List<Chave> retorno = findByDetachedCriteria(criteria);
		return retorno;
	}
	
	@Override
	public List<Chave> chavesUtilizadas(Long idSala) {
		String sql = "select DISTINCT c from Chave c, HistoricoChave hc WHERE c.id = hc.idChave AND c.idSala = :idSala";
		List<Chave> retorno = new ArrayList<Chave>(0);
		
		Query query = getSession().createQuery(sql);
		query.setLong("idSala", idSala);
		retorno = query.list();
		
		return retorno;
	}
	
}
