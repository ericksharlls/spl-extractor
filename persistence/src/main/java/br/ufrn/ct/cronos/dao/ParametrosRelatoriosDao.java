package br.ufrn.ct.cronos.dao;

import br.ufrn.ct.cronos.entity.ParametrosRelatorios;
import dev.home.componente.dao.hibernate.Dao;

public interface ParametrosRelatoriosDao extends Dao<Long, ParametrosRelatorios> {

   public ParametrosRelatorios getByIdentificadorParametro(String identificadorParametro);

}
