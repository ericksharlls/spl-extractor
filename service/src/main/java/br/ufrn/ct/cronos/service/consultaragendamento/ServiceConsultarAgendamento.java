package br.ufrn.ct.cronos.service.consultaragendamento;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.consultaragendamento.vo.ConsultarAgendamento;
import br.ufrn.ct.cronos.consultaragendamento.vo.DadosConsultarAgendamento;
import br.ufrn.ct.cronos.consultaragendamento.vo.RespostaConsultarAgendamento;
import br.ufrn.ct.cronos.dao.AgendamentoDao;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.Agendamento;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.entity.Sala;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceConsultarAgendamento extends AbstractService<ConsultarAgendamento, RespostaConsultarAgendamento> {

   private AgendamentoDao agendamentoDao;
   private FuncionarioDao funcionarioDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private PeriodoDao periodoDao;

   @Override
   public RespostaConsultarAgendamento processa(ConsultarAgendamento solicitacao) throws NegocioException {
      List<DadosConsultarAgendamento> dadosAgendamentos = new ArrayList<DadosConsultarAgendamento>(0);
      Integer totalNumeroLinhas =
         this.agendamentoDao.contadorAgendamentosPorPeriodoPredioInteressadoMotivoEIntervaloDatas(solicitacao.getStartPage(),
               solicitacao.getMaxPage(),
               solicitacao.getIdPeriodo(), solicitacao.getIdPredio(), solicitacao.getInteressado(), solicitacao.getMotivo(),
               solicitacao.getDataInicial(), solicitacao.getDataFinal());

         for (Agendamento agendamento : this.agendamentoDao.getAgendamentosPorPeriodoPredioInteressadoMotivoEIntervaloDatas(
            solicitacao.getStartPage(),
            solicitacao.getMaxPage(), solicitacao.getIdPeriodo(), solicitacao.getIdPredio(), solicitacao.getInteressado(),
            solicitacao.getMotivo(),
            solicitacao.getDataInicial(), solicitacao.getDataFinal())) {

            Funcionario funcionario = this.funcionarioDao.get(agendamento.getIdFuncionario());
            Sala sala = this.disponibilidadeSalaDao.getSalaPorAgendamento(agendamento.getId());
            String data = new String();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            List<Date> datasReservaAgendamento = this.disponibilidadeSalaDao.getDatasReservaPorAgendamento(agendamento.getId());

            Date[] datasReserva = (Date[]) datasReservaAgendamento.toArray(new Date[datasReservaAgendamento.size()]);

            DadosConsultarAgendamento dadosConsultarAgendamento = new DadosConsultarAgendamento();
         dadosConsultarAgendamento.setIdAgendamento(agendamento.getId());
            dadosConsultarAgendamento.setInteressado(funcionario.getNome());
            dadosConsultarAgendamento.setMotivo(agendamento.getMotivo());
            dadosConsultarAgendamento.setSala(sala.getNome());

            for (int i = 0; i < datasReserva.length; i++) {
            if (i == 3) {
               data = data + formatter.format(datasReserva[i]) + "...";
               break;
            } else if (i == datasReserva.length - 1) {
                  data = data + formatter.format(datasReserva[i]);
            } else if (i != datasReserva.length - 1) {
                  data = data + formatter.format(datasReserva[i]) + ",";
            }
            }
            dadosConsultarAgendamento.setData(data);
            dadosAgendamentos.add(dadosConsultarAgendamento);
      }

      return new RespostaConsultarAgendamento(dadosAgendamentos, totalNumeroLinhas);
   }

   @Override
   public void valida(ConsultarAgendamento solicitacao) throws NegocioException {
      if (solicitacao.getIdPeriodo() == null || solicitacao.getIdPeriodo() == 0) {
         solicitacao.setIdPeriodo(this.periodoDao.getPeriodoPorData(new Date()).getId());
      }
      if (solicitacao.getIdPredio() == null || solicitacao.getIdPredio() == 0) {
         solicitacao.setIdPredio(new Long(1));
      }
      if (solicitacao.getDataInicial() == null) {
         solicitacao.setDataInicial(new Date());
      }
      if (solicitacao.getDataFinal() == null) {
         solicitacao.setDataFinal(this.periodoDao.getPeriodoPorData(new Date()).getDataTermino());
      }
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

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

}
