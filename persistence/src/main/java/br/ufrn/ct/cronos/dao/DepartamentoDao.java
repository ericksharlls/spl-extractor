package br.ufrn.ct.cronos.dao;

import java.util.List;
import br.ufrn.ct.cronos.entity.Departamento;
import dev.home.componente.dao.hibernate.Dao;

public interface DepartamentoDao extends Dao<Long, Departamento> {

   public List<Departamento> getAllOrdemAlfabetica();

}
