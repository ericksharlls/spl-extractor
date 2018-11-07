package br.ufrn.ct.cronos.dao.impl;

import org.hibernate.Query;
import br.ufrn.ct.cronos.dao.ParametrosRelatoriosDao;
import br.ufrn.ct.cronos.entity.ParametrosRelatorios;
import dev.home.componente.dao.hibernate.impl.AbstractDao;


public class ParametrosRelatoriosDaoImpl extends AbstractDao<Long, ParametrosRelatorios> implements ParametrosRelatoriosDao {

   @Override
   public ParametrosRelatorios getByIdentificadorParametro(String identificadorParametro) {
      String sql = "select p from ParametrosRelatorios p WHERE p.identificador = :identificador";
      Query query = getSession().createQuery(sql);

      query.setString("identificador", identificadorParametro);

      ParametrosRelatorios retorno = (ParametrosRelatorios) query.uniqueResult();

      return retorno;
   }

}
