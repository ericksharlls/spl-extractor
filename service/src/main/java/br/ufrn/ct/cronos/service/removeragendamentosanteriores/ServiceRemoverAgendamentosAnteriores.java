
package br.ufrn.ct.cronos.service.removeragendamentosanteriores;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;
import org.springframework.scheduling.quartz.QuartzJobBean;
import br.ufrn.ct.cronos.dao.AgendamentoDao;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;

public class ServiceRemoverAgendamentosAnteriores extends QuartzJobBean implements StatefulJob {

   private AgendamentoDao agendamentoDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;

   protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
      // Fazendo nada por enquanto
      // this.agendamentoDao.removerAgendamentosAnteriores();
      System.out.println("EXECUTANDO UMA TAREFA AGENDADA !");
      try {
         this.disponibilidadeSalaDao.removerAgendamentosAnteriores();
         System.out.println("TAREFA AGENDADA EXECUTADA COM SUCESSO !");
      } catch (Exception e) {
         System.out.println("OCORREU UMA EXCEÇÃO AO EXECUTAR A TAREFA AGENDADA !");
         e.printStackTrace();
      }
   }

   public void setAgendamentoDao(AgendamentoDao agendamentoDao) {
      this.agendamentoDao = agendamentoDao;
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

}
