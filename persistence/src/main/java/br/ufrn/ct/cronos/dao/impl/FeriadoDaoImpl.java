package br.ufrn.ct.cronos.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.entity.Feriado;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class FeriadoDaoImpl extends AbstractDao<Long, Feriado> implements FeriadoDao {

   private static final String DATA_FERIADO = "data";
   private static final String ID_PERIODO = "idPeriodo";

   @Override
   public List<Feriado> findAllOrderByData(Integer startPage, Integer maxPage) {
      DetachedCriteria criteria = criarDetachedCriteria();
      criteria.addOrder(Order.desc(FeriadoDaoImpl.DATA_FERIADO));
      List<Feriado> retorno = findByDetachedCriteria(criteria, startPage, maxPage);
      return retorno;
   }

   @Override
   public List<Feriado> findAllByPeriodo(Long idPeriodo) {
      DetachedCriteria criteria = criarDetachedCriteria();
      criteria.addOrder(Order.asc(FeriadoDaoImpl.DATA_FERIADO));
      criteria.add(Expression.eq(FeriadoDaoImpl.ID_PERIODO, idPeriodo));
      List<Feriado> retorno = findByDetachedCriteria(criteria);
      return retorno;
   }

   @Override
   public List<Date> getDatasFeriadosByPeriodo(Long idPeriodo) {
      List<Date> retorno = new ArrayList<Date>(0);
      DetachedCriteria criteria = criarDetachedCriteria();
      criteria.addOrder(Order.asc(FeriadoDaoImpl.DATA_FERIADO));
      criteria.add(Expression.eq(FeriadoDaoImpl.ID_PERIODO, idPeriodo));
      List<Feriado> feriados = findByDetachedCriteria(criteria);
      for (Feriado feriado : feriados) {
         retorno.add(feriado.getData());
      }
      return retorno;
   }

}
