package br.ufrn.ct.cronos.dao.impl;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import br.ufrn.ct.cronos.dao.DepartamentoDao;
import br.ufrn.ct.cronos.entity.Departamento;
import dev.home.componente.dao.hibernate.impl.AbstractDao;


public class DepartamentoDaoImpl extends AbstractDao<Long, Departamento> implements DepartamentoDao {

   private static final String NOME_DEPARTAMENTO = "nome";

   public DepartamentoDaoImpl() {
      super();
   }

   @Override
   public List<Departamento> getAllOrdemAlfabetica() {
      DetachedCriteria criteria = criarDetachedCriteria();
      criteria.addOrder(Order.asc(DepartamentoDaoImpl.NOME_DEPARTAMENTO));
      List<Departamento> retorno = findByDetachedCriteria(criteria);
      return retorno;
   }

}
