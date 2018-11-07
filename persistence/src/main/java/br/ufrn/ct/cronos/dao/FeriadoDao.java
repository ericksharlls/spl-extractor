package br.ufrn.ct.cronos.dao;

import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.entity.Feriado;
import dev.home.componente.dao.hibernate.Dao;

public interface FeriadoDao extends Dao<Long, Feriado> {

   public List<Feriado> findAllOrderByData(Integer startPage, Integer maxPage);

   public List<Feriado> findAllByPeriodo(Long idPeriodo);

   public List<Date> getDatasFeriadosByPeriodo(Long idPeriodo);

}
