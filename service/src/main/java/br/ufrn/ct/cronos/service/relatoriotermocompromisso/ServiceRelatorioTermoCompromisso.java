package br.ufrn.ct.cronos.service.relatoriotermocompromisso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.ParametrosRelatoriosDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.relatoriotermocompromisso.vo.DadosTermoCompromisso;
import br.ufrn.ct.cronos.relatoriotermocompromisso.vo.RelatorioTermoCompromisso;
import br.ufrn.ct.cronos.relatoriotermocompromisso.vo.RespostaRelatorioTermoCompromisso;
import br.ufrn.ct.cronos.service.ManipuladorDatas;
import br.ufrn.ct.cronos.service.ParametrosRelatoriosEnum;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRelatorioTermoCompromisso extends AbstractService<RelatorioTermoCompromisso, RespostaRelatorioTermoCompromisso> {
      
   private SalaDao salaDao;
   private PeriodoDao periodoDao;
   private FeriadoDao feriadoDao;
   private FuncionarioDao funcionarioDao;
   private ParametrosRelatoriosDao parametrosRelatoriosDao;
      
   @Override
   public RespostaRelatorioTermoCompromisso processa(RelatorioTermoCompromisso solicitacao) throws NegocioException {
      List<DadosTermoCompromisso> dados = new ArrayList<DadosTermoCompromisso>(0);
      DadosTermoCompromisso termoCompromisso = new DadosTermoCompromisso();

      termoCompromisso.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
      termoCompromisso.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
      termoCompromisso.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());

      Sala sala = this.salaDao.get(solicitacao.getIdSala());
         
      termoCompromisso.setInteressado(this.funcionarioDao.get(solicitacao.getIdInteressado()).getNome());
      termoCompromisso.setSala(sala.getNome());
         
      String stringData = "";

      if (solicitacao.getDatasParaAgendamento() != null && solicitacao.getDatasParaAgendamento().size() > 0) {
         for (Date data : solicitacao.getDatasParaAgendamento()) {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            stringData = stringData + " " + formatter.format(data) + ",";
         }
      } else {
         List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(solicitacao.getIdPeriodo());
         List<Date> datasParaReserva =
            ManipuladorDatas.retornaDatasPorDiasSemFeriados(
               dev.home.componente.utils.date.DateUtils.datasEntre(solicitacao.getDataAgendamentoInicio(),
                  solicitacao.getDataAgendamentoTermino()), feriados, solicitacao.getHorario());
         Collections.sort(datasParaReserva, new Comparator<Date>() {
            public int compare(Date d1, Date d2) {
               return d1.compareTo(d2);
            }
         });
         for (Date data : datasParaReserva) {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            stringData = stringData + " " + formatter.format(data) + ",";
         }
      }


      termoCompromisso.setData(stringData);
      termoCompromisso.setDataItemF(stringData.substring(0, stringData.length() - 1));
      termoCompromisso.setHorario(retornaDescricaoHorario(solicitacao.getHorario()));

      if (!solicitacao.getHorarioOpcional().equals("")) {
         String horarioOpcional = termoCompromisso.getHorario();
         horarioOpcional = horarioOpcional + " e das " + retornaDescricaoHorario(solicitacao.getHorarioOpcional());
         termoCompromisso.setHorario(horarioOpcional);
      }

      termoCompromisso.setSemestre(this.periodoDao.get(solicitacao.getIdPeriodo()).getNome());
      termoCompromisso.setMotivo(solicitacao.getMotivo());
      if (this.funcionarioDao.get(solicitacao.getIdInteressado()).getCpf() != null
         && !this.funcionarioDao.get(solicitacao.getIdInteressado()).getCpf().equals("")) {
         termoCompromisso.setIdentificador(this.funcionarioDao.get(solicitacao.getIdInteressado()).getCpf());
      } else {
         termoCompromisso.setIdentificador(this.funcionarioDao.get(solicitacao.getIdInteressado()).getMatricula());
      }
         
      dados.add(termoCompromisso);
         
      return new RespostaRelatorioTermoCompromisso(dados);
   }
      
   @Override
   public void valida(RelatorioTermoCompromisso solicitacao) throws NegocioException {
      if (solicitacao.getDatasParaAgendamento() != null && solicitacao.getDatasParaAgendamento().size() > 0) {
         solicitacao.setHorario(ManipuladorDatas.retornaListaDiasDaSemana(solicitacao.getDatasParaAgendamento())
            + solicitacao.getHorario());
         if (!solicitacao.getHorarioOpcional().equals("")) {
            solicitacao.setHorarioOpcional(ManipuladorDatas.retornaListaDiasDaSemana(solicitacao.getDatasParaAgendamento())
               + solicitacao.getHorarioOpcional());
         }
      } else {
         for (String dia : solicitacao.getDiasDaSemana()) {
            solicitacao.setHorario(dia + solicitacao.getHorario());
         }
         if (!solicitacao.getHorarioOpcional().equals("")) {
            for (String dia : solicitacao.getDiasDaSemana()) {
               solicitacao.setHorarioOpcional(dia + solicitacao.getHorarioOpcional());
            }
         }
      }
   }
      

   private String retornaDescricaoHorario(String horarioTurma) {
      Auxiliar auxiliar = new Auxiliar();
      String[] arrayHorario = auxiliar.retornaArrayHorarios(horarioTurma);
      String turno = auxiliar.retornaTurno(horarioTurma);
      String[] horario = new String[2];

      for (int i = 0; i < arrayHorario.length; i++) {
         if (i == 0)
            horario[0] = arrayHorario[i];
         else if (i == arrayHorario.length - 1) {
            horario[1] = arrayHorario[i];
         }

      }

      String retorno = "";

      if (turno.equals("M")) {
         for (int i = 0; i < horario.length; i++) {
            if (i == 0) {
               if (horario[i].equals("1"))
                  retorno = retorno + "7:00";
               else if (horario[i].equals("2"))
                  retorno = retorno + "7:50";
               else if (horario[i].equals("3"))
                  retorno = retorno + "8:55";
               else if (horario[i].equals("4"))
                  retorno = retorno + "9:45";
               else if (horario[i].equals("5"))
                  retorno = retorno + "10:50";
               else if (horario[i].equals("6"))
                  retorno = retorno + "11:40";
            }
            else {
               retorno = retorno + " \u00E0s ";
               if (horario[i].equals("2"))
                  retorno = retorno + "8:40";
               else if (horario[i].equals("3"))
                  retorno = retorno + "9:45";
               else if (horario[i].equals("4"))
                  retorno = retorno + "10:35";
               else if (horario[i].equals("5"))
                  retorno = retorno + "11:40";
               else if (horario[i].equals("6"))
                  retorno = retorno + "12:30";
            }
         }
      }
      else if (turno.equals("T")) {
         for (int i = 0; i < horario.length; i++) {
            if (i == 0) {
               if (horario[i].equals("1"))
                  retorno = retorno + "13:00";
               else if (horario[i].equals("2"))
                  retorno = retorno + "13:50";
               else if (horario[i].equals("3"))
                  retorno = retorno + "14:55";
               else if (horario[i].equals("4"))
                  retorno = retorno + "15:45";
               else if (horario[i].equals("5"))
                  retorno = retorno + "16:50";
               else if (horario[i].equals("6"))
                  retorno = retorno + "17:40";
            }
            else {
               retorno = retorno + " \u00E0s ";
               if (horario[i].equals("2"))
                  retorno = retorno + "14:40";
               else if (horario[i].equals("3"))
                  retorno = retorno + "15:45";
               else if (horario[i].equals("4"))
                  retorno = retorno + "16:35";
               else if (horario[i].equals("5"))
                  retorno = retorno + "17:40";
               else if (horario[i].equals("6"))
                  retorno = retorno + "18:30";
            }
         }
      }
      else if (turno.equals("N")) {
         for (int i = 0; i < horario.length; i++) {
            if (i == 0) {
               if (horario[i].equals("1"))
                  retorno = retorno + "18:45";
               else if (horario[i].equals("2"))
                  retorno = retorno + "19:35";
               else if (horario[i].equals("3"))
                  retorno = retorno + "20:35";
               else if (horario[i].equals("4"))
                  retorno = retorno + "21:25";
            }
            else {
               retorno = retorno + " \u00E0s ";
               if (horario[i].equals("2"))
                  retorno = retorno + "20:25";
               else if (horario[i].equals("3"))
                  retorno = retorno + "21:25";
               else if (horario[i].equals("4")) {
                  retorno = retorno + "22:15";
               }
            }
         }
      }

      return retorno;
   }

   public void setSalaDao(SalaDao salaDao) {
      this.salaDao = salaDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

   public void setParametrosRelatoriosDao(ParametrosRelatoriosDao parametrosRelatoriosDao) {
      this.parametrosRelatoriosDao = parametrosRelatoriosDao;
   }
      
}
