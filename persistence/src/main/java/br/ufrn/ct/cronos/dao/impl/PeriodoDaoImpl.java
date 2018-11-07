package br.ufrn.ct.cronos.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.Periodo;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class PeriodoDaoImpl extends AbstractDao<Long, Periodo> implements PeriodoDao {
	
   private static final String DATA_TERMINO = "dataTermino";

	public PeriodoDaoImpl() {
		super();
	}

   @Override
   public boolean verificarPeriodoTemTurma(Long idPeriodo) {
      String sql = "select count(t) from Turma t WHERE t.idPeriodo = :idPeriodo";
      Query query = getSession().createQuery(sql);
      query.setLong("idPeriodo", idPeriodo);
      Long contador = (Long) query.uniqueResult();

      if (contador > 0) {
         return true;
      }
      return false;
   }

   @Override
   public boolean verificarIntervaloDatasJaExiste(Date dataInicio, Date dataTermino) {
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
      String sql = "select count(p) from Periodo p WHERE p.dataInicio BETWEEN :dataInicio AND :dataTermino OR " +
         "p.dataTermino BETWEEN :dataInicio AND :dataTermino";
      Query query = getSession().createQuery(sql);
      query.setString("dataInicio", formatter.format(dataInicio));
      query.setString("dataTermino", formatter.format(dataTermino));
      Long contador = (Long) query.uniqueResult();

      if (contador > 0) {
         return true;
      }
      return false;
   }

   @Override
   public List<Periodo> getPeriodoDentroDeIntervalo(Date dataInicio, Date dataTermino) {
      List<Periodo> retorno = new ArrayList<Periodo>(0);
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
      String sql = "select p from Periodo p WHERE p.dataInicio BETWEEN :dataInicio AND :dataTermino OR " +
         "p.dataTermino BETWEEN :dataInicio AND :dataTermino";
      Query query = getSession().createQuery(sql);
      query.setString("dataInicio", formatter.format(dataInicio));
      query.setString("dataTermino", formatter.format(dataTermino));
      retorno = query.list();

      return retorno;
   }

   @Override
   public Periodo getPeriodoPorData(Date data) {
      Periodo retorno = new Periodo();
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
      String sql = "select p from Periodo p WHERE :data BETWEEN p.dataInicio AND p.dataTermino";
      Query query = getSession().createQuery(sql);
      query.setString("data", formatter.format(data));
      retorno = (Periodo) query.uniqueResult();

      return retorno;
   }

   @Override
   public Periodo getPeriodoLetivoAnterior() {
      Periodo retorno = new Periodo();
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
      String sql = "select p from Periodo p WHERE p.dataTermino < :data AND p.isPeriodoLetivo = :isPeriodoLetivo ORDER BY p.dataTermino DESC";
      Query query = getSession().createQuery(sql);
      query.setString("data", formatter.format(new Date()));
      query.setBoolean("isPeriodoLetivo", true);
      query.setFirstResult(0);
      query.setMaxResults(1);
      retorno = (Periodo) query.uniqueResult();
      
      return retorno;
   }

   /**
    * Retorna o período letivo atual. Caso a data atual não esteja dentro de um período letivo, retorna o próximo futuro período letivo.
    * (Ver Javadoc da super classe)
    * @see br.ufrn.ct.cronos.dao.PeriodoDao#getPeriodoLetivoAtualOuProximo()
    */
   @Override
   public Periodo getPeriodoLetivoAtualOuProximo() {
      Periodo retorno = new Periodo();
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
      String sql =
         "select p from Periodo p WHERE p.dataTermino > :data AND p.isPeriodoLetivo = :isPeriodoLetivo ORDER BY p.dataTermino ASC";
      Query query = getSession().createQuery(sql);
      query.setString("data", formatter.format(new Date()));
      query.setBoolean("isPeriodoLetivo", true);
      query.setFirstResult(0);
      query.setMaxResults(1);
      retorno = (Periodo) query.uniqueResult();

      return retorno;
   }

   @Override
   public List<Periodo> findAllOrderByDataTermino(Integer startPage, Integer maxPage) {
      DetachedCriteria criteria = criarDetachedCriteria();
      criteria.addOrder(Order.desc(PeriodoDaoImpl.DATA_TERMINO));
      List<Periodo> retorno = findByDetachedCriteria(criteria, startPage, maxPage);
      return retorno;
   }

   @Override
   public List<Periodo> findAllOrderByDataTermino() {
      DetachedCriteria criteria = criarDetachedCriteria();
      criteria.addOrder(Order.desc(PeriodoDaoImpl.DATA_TERMINO));
      List<Periodo> retorno = findByDetachedCriteria(criteria);
      return retorno;
   }
	
}
