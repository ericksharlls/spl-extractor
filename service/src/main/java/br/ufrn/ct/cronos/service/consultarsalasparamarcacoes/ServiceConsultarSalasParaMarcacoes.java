package br.ufrn.ct.cronos.service.consultarsalasparamarcacoes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;
import br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo.ConsultarSalasParaMarcacoes;
import br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo.DadosConsultarSalasParaMarcacoes;
import br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo.RespostaConsultarSalasParaMarcacoes;
import br.ufrn.ct.cronos.dao.AgendamentoDao;
import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.PerfilSalaTurmaDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.PerfilSalaTurma;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.service.ManipuladorDatas;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceConsultarSalasParaMarcacoes extends AbstractService<ConsultarSalasParaMarcacoes, RespostaConsultarSalasParaMarcacoes> {

   private PerfilSalaTurmaDao perfilSalaTurmaDao;
   private AgendamentoDao agendamentoDao;
   private PeriodoDao periodoDao;
   private FeriadoDao feriadoDao;
   private HorarioDao horarioDao;
      
   @Override
   public RespostaConsultarSalasParaMarcacoes processa(ConsultarSalasParaMarcacoes solicitacao) throws NegocioException {
      List<DadosConsultarSalasParaMarcacoes> dados = new ArrayList<DadosConsultarSalasParaMarcacoes>(0);
      Auxiliar auxiliar = new Auxiliar();

      if (solicitacao.getDataAgendamentoInicio() != null || solicitacao.getDataAgendamentoTermino() != null) {
         List<Long> idsHorarios = new ArrayList<Long>(0);

         List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(solicitacao.getIdPeriodo());
         // List<Date> datasAgendamento = ManipuladorDatas.retornaDatasSemSabadosDomingosFeriados(
         // dev.home.componente.utils.date.DateUtils.datasEntre(solicitacao.getDataAgendamentoInicio(),
         // solicitacao.getDataAgendamentoTermino()), feriados);
         for (String dia : solicitacao.getDiasDaSemana()) {
            solicitacao.setHorarioTurma(dia + solicitacao.getHorarioTurma());
         }
         List<Date> datasAgendamento =
            ManipuladorDatas.retornaDatasPorDiasSemFeriados(
               dev.home.componente.utils.date.DateUtils.datasEntre(solicitacao.getDataAgendamentoInicio(),
                  solicitacao.getDataAgendamentoTermino()), feriados, solicitacao.getHorarioTurma());

         // solicitacao.setHorarioTurma(ManipuladorDatas.retornaListaDiasDaSemana(datasAgendamento) + solicitacao.getHorarioTurma());

         for (int h = 0; h < auxiliar.contadorDeGruposComSabado(solicitacao.getHorarioTurma()); h++) {
            String grupo = auxiliar.retornaGrupoComSabado(solicitacao.getHorarioTurma(), h);
            // String[] arrayDias = auxiliar.retornaArrayDias(grupo);
            String turno = auxiliar.retornaTurno(grupo);
            String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

            for (int k = 0; k < arrayHorarios.length; k++) {
               idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
            }

         }

         //solicitacao.setHorarioTurmaOpcional(ManipuladorDatas.retornaListaDiasDaSemana(datasAgendamento) + solicitacao.getHorarioTurmaOpcional());
         if (!solicitacao.getHorarioTurmaOpcional().equals("")) {
            for (String dia : solicitacao.getDiasDaSemana()) {
               solicitacao.setHorarioTurmaOpcional(dia + solicitacao.getHorarioTurmaOpcional());
            }

            for (int h = 0; h < auxiliar.contadorDeGruposComSabado(solicitacao.getHorarioTurmaOpcional()); h++) {
               String grupo = auxiliar.retornaGrupoComSabado(solicitacao.getHorarioTurmaOpcional(), h);
               // String[] arrayDias = auxiliar.retornaArrayDias(grupo);
               String turno = auxiliar.retornaTurno(grupo);
               String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

               for (int k = 0; k < arrayHorarios.length; k++) {
                  idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
               }

            }
         }

         for (Sala sala : this.agendamentoDao.getSalasDisponiveisPorHorarioEData(solicitacao.getIdPredio(), solicitacao.getIdPeriodo(),
            idsHorarios, datasAgendamento)) {
            DadosConsultarSalasParaMarcacoes dado = new DadosConsultarSalasParaMarcacoes();
            dado.setCapacidade(sala.getCapacidade());
            dado.setId(sala.getId());
            dado.setNome(sala.getNome());
            dado.setTipoQuadro(sala.getTipoQuadro());

            PerfilSalaTurma perfil = this.perfilSalaTurmaDao.get(sala.getIdTipo());

            dado.setPerfilSala(perfil.getNome());
            dados.add(dado);
         }

      } else {
         List<Long> idsHorarios = new ArrayList<Long>(0);

         for (int h = 0; h < auxiliar.contadorDeGruposComSabado(solicitacao.getHorarioTurma()); h++) {
            String grupo = auxiliar.retornaGrupoComSabado(solicitacao.getHorarioTurma(), h);
            String turno = auxiliar.retornaTurno(grupo);
            String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);
            for (int k = 0; k < arrayHorarios.length; k++) {
               idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
            }
         }

         if (!solicitacao.getHorarioTurmaOpcional().equals("")) {
            for (int h = 0; h < auxiliar.contadorDeGruposComSabado(solicitacao.getHorarioTurmaOpcional()); h++) {
               String grupo = auxiliar.retornaGrupoComSabado(solicitacao.getHorarioTurmaOpcional(), h);
               String turno = auxiliar.retornaTurno(grupo);
               String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);
               for (int k = 0; k < arrayHorarios.length; k++) {
                  idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
               }
            }
         }

         for (Sala sala : this.agendamentoDao.getSalasDisponiveisPorHorarioEData(solicitacao.getIdPredio(), solicitacao.getIdPeriodo(),
            idsHorarios, solicitacao.getDatasParaAgendamento())) {
            DadosConsultarSalasParaMarcacoes dado = new DadosConsultarSalasParaMarcacoes();
            dado.setCapacidade(sala.getCapacidade());
            dado.setId(sala.getId());
            dado.setNome(sala.getNome());
            dado.setTipoQuadro(sala.getTipoQuadro());

            PerfilSalaTurma perfil = this.perfilSalaTurmaDao.get(sala.getIdTipo());

            dado.setPerfilSala(perfil.getNome());
            dados.add(dado);
         }
      }

      Integer totalNumeroLinhas = dados.size();

      return new RespostaConsultarSalasParaMarcacoes(dados, totalNumeroLinhas);
   }
      
   @Override
   public void valida(ConsultarSalasParaMarcacoes solicitacao) throws NegocioException {
      if (solicitacao.getIdPeriodo() == 0 || solicitacao.getIdPeriodo() == null) {
         throw new NegocioException(ErrorCode.ID_PERIODO);
      }

      if (solicitacao.getIdPredio() == 0 || solicitacao.getIdPredio() == null) {
         throw new NegocioException(ErrorCode.ID_PREDIO);
      }
      Auxiliar auxiliar = new Auxiliar();
      if ((solicitacao.getHorarioTurma().equals("")) || (!auxiliar.validarHorarioSemDias(solicitacao.getHorarioTurma()))) {
         throw new NegocioException(ErrorCode.HORARIO_VAZIO_OU_INVALIDO);
      }
      if (auxiliar.retornaTurno(solicitacao.getHorarioTurma()).equals(auxiliar.retornaTurno(solicitacao.getHorarioTurmaOpcional()))) {
         throw new NegocioException(ErrorCode.TURNOS_IGUAIS);
      }

      if ((solicitacao.getDatasParaAgendamento() == null || solicitacao.getDatasParaAgendamento().size() == 0)
         && (solicitacao.getDataAgendamentoInicio() == null || solicitacao.getDataAgendamentoTermino() == null)) {
         throw new NegocioException(ErrorCode.DATA_VAZIO);
      }


      if (solicitacao.getDatasParaAgendamento().size() > 0 && (solicitacao.getDataAgendamentoInicio() != null
         || solicitacao.getDataAgendamentoTermino() != null)) {
         throw new NegocioException(ErrorCode.ERRO_COM_PERIODO_DATA);
      }

      /*
       * if (solicitacao.getDataAgendamentoInicio() == null || solicitacao.getDataAgendamentoTermino() == null) { if
       * ((solicitacao.getDatasParaAgendamento().size() == 0) || (solicitacao.getDatasParaAgendamento() == null)) {
       * System.out.println("------ SEGUNDO IF --------"); throw new NegocioException(ErrorCode.DATA_VAZIO); } }
       */

      Periodo periodo = this.periodoDao.get(solicitacao.getIdPeriodo());

      if (solicitacao.getDatasParaAgendamento() == null || solicitacao.getDatasParaAgendamento().size() == 0) {

         Date ontem2 = new Date();
         ontem2 = DateUtils.addDays(new Date(), -1);

         if (solicitacao.getDataAgendamentoInicio().before(ontem2)) {
            throw new NegocioException(ErrorCode.DATA_INICIO_INVALIDA);
         }
         if (solicitacao.getDataAgendamentoTermino().before(solicitacao.getDataAgendamentoInicio())) {
            throw new NegocioException(ErrorCode.DATA_TERMINO_AGENDAMENTO_INVALIDA);
         }
         if (solicitacao.getDataAgendamentoInicio().before(periodo.getDataInicio())
            || solicitacao.getDataAgendamentoInicio().after(periodo.getDataTermino())
            || solicitacao.getDataAgendamentoTermino().before(periodo.getDataInicio())
            || solicitacao.getDataAgendamentoTermino().after(periodo.getDataTermino())) {
            throw new NegocioException(ErrorCode.DATAS_NAO_CORRESPONDEM_PERIODO_INFORMADO);
         }

         if (solicitacao.getDiasDaSemana() == null || solicitacao.getDiasDaSemana().size() == 0) {
            throw new NegocioException(ErrorCode.DIAS_SEMANA_NAO_INFORMADOS);
         }

      } else {
         for (Date date : solicitacao.getDatasParaAgendamento()) {
            Date ontem = new Date();
            ontem = DateUtils.addDays(new Date(), -1);
            if (date.before(ontem)) {
               throw new NegocioException(ErrorCode.DATA_INVALIDA);
            }
         }

         for (Date date : solicitacao.getDatasParaAgendamento()) {
            if (date.before(periodo.getDataInicio()) || date.after(periodo.getDataTermino())) {
               throw new NegocioException(ErrorCode.DATAS_NAO_CORRESPONDEM_PERIODO_INFORMADO);
            }
         }

         solicitacao.setHorarioTurma(ManipuladorDatas.retornaListaDiasDaSemana(solicitacao.getDatasParaAgendamento())
            + solicitacao.getHorarioTurma());

         if (!solicitacao.getHorarioTurmaOpcional().equals("")) {
            if (!auxiliar.validarHorarioSemDias(solicitacao.getHorarioTurmaOpcional())) {
               throw new NegocioException(ErrorCode.HORARIO_OPCIONAL_INVALIDO);
            }
            solicitacao.setHorarioTurmaOpcional(ManipuladorDatas.retornaListaDiasDaSemana(solicitacao.getDatasParaAgendamento())
               + solicitacao.getHorarioTurmaOpcional());
         }
      }

   }

   public void setPerfilSalaTurmaDao(PerfilSalaTurmaDao perfilSalaTurmaDao) {
      this.perfilSalaTurmaDao = perfilSalaTurmaDao;
   }
      
   public void setAgendamentoDao(AgendamentoDao agendamentoDao) {
      this.agendamentoDao = agendamentoDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

   /**
    * Atribui o novo valor de horarioDao
    * @param horarioDao horarioDao que será atribuído
    */
   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }
   
}
