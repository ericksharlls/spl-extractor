package br.ufrn.ct.cronos.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import br.ufrn.ct.cronos.dao.TurmaDocenteDao;
import br.ufrn.ct.cronos.entity.TurmaDocente;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class TurmaDocenteDaoImpl extends AbstractDao<Long, TurmaDocente> implements TurmaDocenteDao {
	
	public TurmaDocenteDaoImpl() {
		super();
	}

   @Override
   public void removerRelacionamentoDocenteComTurma(Long idTurma) {
      String sql = "delete from TurmaDocente WHERE idTurma = :idTurma";
      Query query = getSession().createQuery(sql);
      query.setLong("idTurma", idTurma);
      query.executeUpdate();
   }

   @Override
   public List<TurmaDocente> getTurmaDocentePorTurma(Long idTurma) {
      List<TurmaDocente> retorno = new ArrayList<TurmaDocente>(0);
      String sql = "select td from TurmaDocente td WHERE td.idTurma = :idTurma";
      Query query = getSession().createQuery(sql);
      query.setLong("idTurma", idTurma);
      retorno = query.list();
      return retorno;
   }

}
