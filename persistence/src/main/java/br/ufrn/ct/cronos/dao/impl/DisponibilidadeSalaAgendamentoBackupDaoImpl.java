package br.ufrn.ct.cronos.dao.impl;

import org.hibernate.Query;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaAgendamentoBackupDao;
import br.ufrn.ct.cronos.entity.DisponibilidadeSalaAgendamentoBackup;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class DisponibilidadeSalaAgendamentoBackupDaoImpl extends AbstractDao<Long, DisponibilidadeSalaAgendamentoBackup> implements
   DisponibilidadeSalaAgendamentoBackupDao {

   @Override
   public void removerDisponibilidadesSalaPorAgendamento(Long idAgendamento) {
      String sql = "delete from DisponibilidadeSalaAgendamentoBackup d WHERE d.idAgendamento = :idAgendamento";
      Query query = getSession().createQuery(sql);
      query.setLong("idAgendamento", idAgendamento);
      query.executeUpdate();
   }

}
