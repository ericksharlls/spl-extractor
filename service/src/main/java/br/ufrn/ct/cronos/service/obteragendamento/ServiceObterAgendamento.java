package br.ufrn.ct.cronos.service.obteragendamento;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.ufrn.ct.cronos.dao.AgendamentoDao;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.entity.Agendamento;
import br.ufrn.ct.cronos.entity.DisponibilidadeSala;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.entity.Horario;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.obteragendamento.vo.DadosObterAgendamento;
import br.ufrn.ct.cronos.obteragendamento.vo.ObterAgendamento;
import br.ufrn.ct.cronos.obteragendamento.vo.RespostaObterAgendamento;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceObterAgendamento extends AbstractService<ObterAgendamento, RespostaObterAgendamento> {

   private AgendamentoDao agendamentoDao;
   private FuncionarioDao funcionarioDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private HorarioDao horarioDao;

   public RespostaObterAgendamento processa(ObterAgendamento solicitacao) throws NegocioException {
      Agendamento agendamento = (Agendamento) this.agendamentoDao.get(solicitacao.getId());
      Funcionario funcionario = this.funcionarioDao.get(agendamento.getIdFuncionario());
      Sala sala = this.disponibilidadeSalaDao.getSalaPorAgendamento(agendamento.getId());
      List<Date> datasReservaAgendamento = this.disponibilidadeSalaDao.getDatasReservaPorAgendamento(agendamento.getId());
      DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
      DateFormat formatadorHora = new SimpleDateFormat("HH:mm");

      DadosObterAgendamento dados = new DadosObterAgendamento();

      // dados.setData(agendamento.getData());
      // dados.setInteressado(agendamento.getInteressado());
      // dados.setTelefone(agendamento.getTelefone());

      dados.setId(agendamento.getId());
      dados.setInteressado(funcionario.getNome());
      dados.setMotivo(agendamento.getMotivo());
      if (StringUtils.isBlank(funcionario.getTelefone())) {
    	  dados.setTelefone("-");
      } else {
    	  dados.setTelefone(funcionario.getTelefone());  
      }
      dados.setSala(sala.getNome());

      for (Date data : datasReservaAgendamento) {
         String s = new String();
         List<DisponibilidadeSala> lista = this.disponibilidadeSalaDao.getHorariosPorDataReservaAgendamento(agendamento.getId(), data);
         DisponibilidadeSala[] arrayLista = (DisponibilidadeSala[]) lista.toArray(new DisponibilidadeSala[lista.size()]);
         s = s + formatadorData.format(data);
         for (int i = 0; i < arrayLista.length; i++) {
            if (i == 0) {
               Horario horario = this.horarioDao.get(arrayLista[i].getIdHorarioSala());
               s = s + " - " + formatadorHora.format(horario.getInicioHorarioAbsoluto()) + " \u00E0s ";
            } else if(i == arrayLista.length - 1){
               Horario horario = this.horarioDao.get(arrayLista[i].getIdHorarioSala());
               s = s + formatadorHora.format(horario.getTerminoHorarioAbsoluto());
            }
         }
         dados.getDatas().add(s);
      }

      return new RespostaObterAgendamento(dados);
   }

   public void valida(ObterAgendamento solicitacao) throws NegocioException {

   }

   public void setAgendamentoDao(AgendamentoDao agendamentoDao) {
      this.agendamentoDao = agendamentoDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

}
