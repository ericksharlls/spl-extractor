package br.ufrn.ct.cronos.dao;

import br.ufrn.ct.cronos.entity.DisponibilidadeSalaAgendamentoBackup;
import dev.home.componente.dao.hibernate.Dao;

public interface DisponibilidadeSalaAgendamentoBackupDao extends Dao<Long, DisponibilidadeSalaAgendamentoBackup> {

   public void removerDisponibilidadesSalaPorAgendamento(Long idAgendamento);

}
