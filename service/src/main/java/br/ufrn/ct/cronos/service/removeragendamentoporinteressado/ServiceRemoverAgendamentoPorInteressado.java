package br.ufrn.ct.cronos.service.removeragendamentoporinteressado;

import br.ufrn.ct.cronos.dao.AgendamentoDao;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaAgendamentoBackupDao;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.entity.Agendamento;
import br.ufrn.ct.cronos.removeragendamentoporinteressado.vo.RemoverAgendamentoPorInteressado;
import br.ufrn.ct.cronos.removeragendamentoporinteressado.vo.RespostaRemoverAgendamentoPorInteressado;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRemoverAgendamentoPorInteressado extends AbstractService<RemoverAgendamentoPorInteressado, RespostaRemoverAgendamentoPorInteressado> {

   private AgendamentoDao agendamentoDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private DisponibilidadeSalaAgendamentoBackupDao disponibilidadeSalaAgendamentoBackupDao;

   // private AgendamentoBackupDao agendamentoBackupDao;
      
   @Override
   public RespostaRemoverAgendamentoPorInteressado processa(RemoverAgendamentoPorInteressado solicitacao) throws NegocioException {
      Agendamento agendamento = this.agendamentoDao.get(solicitacao.getIdAgendamento());
      this.disponibilidadeSalaDao.removerDisponibilidadesSalaPorAgendamento(agendamento.getId());
      this.disponibilidadeSalaAgendamentoBackupDao.removerDisponibilidadesSalaPorAgendamento(agendamento.getId());
      this.agendamentoDao.delete(agendamento);
         
         //AgendamentoBackup agendamentoBackup = new AgendamentoBackup();
      // agendamentoBackup.setData(agendamento.getData());
      // agendamentoBackup.setDia(agendamento.getDia());
      // agendamentoBackup.setHorario(agendamento.getHorario());
      // agendamentoBackup.setIdentificadorInteressado(agendamento.getIdentificadorInteressado());
      // agendamentoBackup.setIdSala(agendamento.getIdSala());
      // agendamentoBackup.setInteressado(agendamento.getInteressado());
         //agendamentoBackup.setMotivo(agendamento.getMotivo());
      // agendamentoBackup.setTelefone(agendamento.getTelefone());
      // agendamentoBackup.setTurno(agendamento.getTurno());
         
      // this.agendamentoDao.apagarAgendamentosPorInteressado(agendamento);
         
      // this.agendamentoBackupDao.apagarAgendamentosPorInteressado(agendamentoBackup);
         

      return new RespostaRemoverAgendamentoPorInteressado();
   }
      
   @Override
   public void valida(RemoverAgendamentoPorInteressado solicitacao) throws NegocioException {
         
   }
      
   public void setAgendamentoDao(AgendamentoDao agendamentoDao) {
      this.agendamentoDao = agendamentoDao;
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   public void setDisponibilidadeSalaAgendamentoBackupDao(DisponibilidadeSalaAgendamentoBackupDao disponibilidadeSalaAgendamentoBackupDao) {
      this.disponibilidadeSalaAgendamentoBackupDao = disponibilidadeSalaAgendamentoBackupDao;
   }
      
   /*
    * public void setAgendamentoBackupDao(AgendamentoBackupDao agendamentoBackupDao) { this.agendamentoBackupDao = agendamentoBackupDao; }
    */
      
}
