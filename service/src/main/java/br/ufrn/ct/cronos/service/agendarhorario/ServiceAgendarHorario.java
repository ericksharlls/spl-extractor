package br.ufrn.ct.cronos.service.agendarhorario;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import br.ufrn.ct.cronos.agendarhorario.vo.AgendarHorario;
import br.ufrn.ct.cronos.agendarhorario.vo.RespostaAgendarHorario;
import br.ufrn.ct.cronos.dao.AgendamentoDao;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaAgendamentoBackupDao;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.UsuarioDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.Agendamento;
import br.ufrn.ct.cronos.entity.DisponibilidadeSala;
import br.ufrn.ct.cronos.entity.DisponibilidadeSalaAgendamentoBackup;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.service.ManipuladorDatas;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceAgendarHorario extends AbstractService<AgendarHorario, RespostaAgendarHorario>{

   private AgendamentoDao agendamentoDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private FeriadoDao feriadoDao;
   private HorarioDao horarioDao;
   private UsuarioDao usuarioDao;

   private DisponibilidadeSalaAgendamentoBackupDao disponibilidadeSalaAgendamentoBackupDao;
 
   @Override
   public RespostaAgendarHorario processa(AgendarHorario solicitacao) throws NegocioException {
      Auxiliar auxiliar = new Auxiliar();

      if (solicitacao.getDataAgendamentoInicio() != null && solicitacao.getDataAgendamentoTermino() != null) {
         List<Long> idsHorarios = new ArrayList<Long>(0);
         List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(solicitacao.getIdPeriodo());
        
         List<Date> datasParaReserva =
            ManipuladorDatas.retornaDatasPorDiasSemFeriados(
               dev.home.componente.utils.date.DateUtils.datasEntre(solicitacao.getDataAgendamentoInicio(),
                  solicitacao.getDataAgendamentoTermino()), feriados, solicitacao.getHorario());

         for (int h = 0; h < auxiliar.contadorDeGruposComDomingo(solicitacao.getHorario()); h++) {
            String grupo = auxiliar.retornaGrupoComDomingo(solicitacao.getHorario(), h);
            String turno = auxiliar.retornaTurno(grupo);
            String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);
            for (int k = 0; k < arrayHorarios.length; k++) {
               idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
            }
         }

         if (!solicitacao.getHorarioOpcional().equals("")) {
            for (int h = 0; h < auxiliar.contadorDeGruposComDomingo(solicitacao.getHorarioOpcional()); h++) {
               String grupo = auxiliar.retornaGrupoComDomingo(solicitacao.getHorarioOpcional(), h);
               String turno = auxiliar.retornaTurno(grupo);
               String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

               for (int k = 0; k < arrayHorarios.length; k++) {
                  idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
               }
            }
         }

         Agendamento agendamento = new Agendamento();
         agendamento.setMotivo(solicitacao.getMotivo());
         agendamento.setIdPeriodo(solicitacao.getIdPeriodo());
         agendamento.setIdFuncionario(solicitacao.getIdInteressado());
         agendamento.setHoraRealizacaoAgendamento(new Date());
         agendamento.setIdUsuarioSistema(this.usuarioDao.getByLogin(solicitacao.getLoginUsuario()).getId());

         this.agendamentoDao.save(agendamento);

         for (Long idHorario : idsHorarios) {
            DisponibilidadeSala disponibilidadeSala = new DisponibilidadeSala();
            disponibilidadeSala.setIdHorarioSala(idHorario);
            disponibilidadeSala.setIdPeriodo(solicitacao.getIdPeriodo());
            disponibilidadeSala.setIdSala(solicitacao.getIdSala());
            disponibilidadeSala.setIdAgendamento(agendamento.getId());

            for (Date data : datasParaReserva) {
               disponibilidadeSala.setDataReserva(data);
               this.disponibilidadeSalaDao.save(disponibilidadeSala);
            }
         }

         // ------------------------------------------------------------------------------------------------
         // Fazendo o backup do agendamento
         // Persistindo os registros de DisponibilidadeSalaAgendamentoBackup
         for (Long idHorario : idsHorarios) {
            DisponibilidadeSalaAgendamentoBackup disponibilidadeSalaAgendamentoBackup = new DisponibilidadeSalaAgendamentoBackup();
            disponibilidadeSalaAgendamentoBackup.setIdHorarioSala(idHorario);
            disponibilidadeSalaAgendamentoBackup.setIdPeriodo(solicitacao.getIdPeriodo());
            disponibilidadeSalaAgendamentoBackup.setIdSala(solicitacao.getIdSala());
            disponibilidadeSalaAgendamentoBackup.setIdAgendamento(agendamento.getId());

            for (Date data : datasParaReserva) {
               disponibilidadeSalaAgendamentoBackup.setDataReserva(data);
               this.disponibilidadeSalaAgendamentoBackupDao.save(disponibilidadeSalaAgendamentoBackup);
            }
         }


      } else {
         List<Long> idsHorarios = new ArrayList<Long>(0);

         for (int h = 0; h < auxiliar.contadorDeGruposComDomingo(solicitacao.getHorario()); h++) {
            String grupo = auxiliar.retornaGrupoComDomingo(solicitacao.getHorario(), h);
            String turno = auxiliar.retornaTurno(grupo);
            String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);
            for (int k = 0; k < arrayHorarios.length; k++) {
               idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
            }
         }

         if (!solicitacao.getHorarioOpcional().equals("")) {
            for (int h = 0; h < auxiliar.contadorDeGruposComDomingo(solicitacao.getHorarioOpcional()); h++) {
               String grupo = auxiliar.retornaGrupoComDomingo(solicitacao.getHorarioOpcional(), h);
               String turno = auxiliar.retornaTurno(grupo);
               String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);
               for (int k = 0; k < arrayHorarios.length; k++) {
                  idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
               }
            }
         }

         Agendamento agendamento = new Agendamento();
         agendamento.setMotivo(solicitacao.getMotivo());
         agendamento.setIdPeriodo(solicitacao.getIdPeriodo());
         agendamento.setIdFuncionario(solicitacao.getIdInteressado());
         agendamento.setHoraRealizacaoAgendamento(new Date());
         agendamento.setIdUsuarioSistema(this.usuarioDao.getByLogin(solicitacao.getLoginUsuario()).getId());

         this.agendamentoDao.save(agendamento);

         for (Long idHorario : idsHorarios) {
            DisponibilidadeSala disponibilidadeSala = new DisponibilidadeSala();
            disponibilidadeSala.setIdHorarioSala(idHorario);
            disponibilidadeSala.setIdPeriodo(solicitacao.getIdPeriodo());
            disponibilidadeSala.setIdSala(solicitacao.getIdSala());
            disponibilidadeSala.setIdAgendamento(agendamento.getId());

            for (Date data : solicitacao.getDatasParaAgendamento()) {
               disponibilidadeSala.setDataReserva(data);
               this.disponibilidadeSalaDao.save(disponibilidadeSala);
            }
         }

         // ------------------------------------------------------------------------------------------------
         // Fazendo o backup do agendamento
         // Persistindo os registros de DisponibilidadeSalaAgendamentoBackup
         for (Long idHorario : idsHorarios) {
            DisponibilidadeSalaAgendamentoBackup disponibilidadeSalaAgendamentoBackup = new DisponibilidadeSalaAgendamentoBackup();
            disponibilidadeSalaAgendamentoBackup.setIdHorarioSala(idHorario);
            disponibilidadeSalaAgendamentoBackup.setIdPeriodo(solicitacao.getIdPeriodo());
            disponibilidadeSalaAgendamentoBackup.setIdSala(solicitacao.getIdSala());
            disponibilidadeSalaAgendamentoBackup.setIdAgendamento(agendamento.getId());

            for (Date data : solicitacao.getDatasParaAgendamento()) {
               disponibilidadeSalaAgendamentoBackup.setDataReserva(data);
               this.disponibilidadeSalaAgendamentoBackupDao.save(disponibilidadeSalaAgendamentoBackup);
            }
         }

      }


      /*
       * Auxiliar auxiliar = new Auxiliar(); if (solicitacao.getDataAgendamentoInicio() != null || solicitacao.getDataAgendamentoTermino()
       * != null) { List<Long> idsHorarios = new ArrayList<Long>(0); solicitacao.setHorario("7" + solicitacao.getHorario()); for (int h = 0;
       * h < auxiliar.contadorDeGruposComSabado(solicitacao.getHorario()); h++) { String grupo =
       * auxiliar.retornaGrupoComSabado(solicitacao.getHorario(), h); String[] arrayDias = auxiliar.retornaArrayDias(grupo); String turno =
       * auxiliar.retornaTurno(grupo); String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo); for (int i = 0; i < arrayDias.length;
       * i++) { for (int k = 0; k < arrayHorarios.length; k++) { idsHorarios.add(this.horarioSalaDao.getByDiaTurnoEHorario(arrayDias[i],
       * turno, arrayHorarios[k]).getId()); } } } solicitacao.setHorarioOpcional("7" + solicitacao.getHorarioOpcional()); if
       * (auxiliar.validarHorarioSemDias(solicitacao.getHorarioOpcional())) { for (int h = 0; h <
       * auxiliar.contadorDeGruposComSabado(solicitacao.getHorarioOpcional()); h++) { String grupo =
       * auxiliar.retornaGrupoComSabado(solicitacao.getHorarioOpcional(), h); String[] arrayDias = auxiliar.retornaArrayDias(grupo); String
       * turno = auxiliar.retornaTurno(grupo); String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo); for (int i = 0; i <
       * arrayDias.length; i++) { for (int k = 0; k < arrayHorarios.length; k++) {
       * idsHorarios.add(this.horarioSalaDao.getByDiaTurnoEHorario(arrayDias[i], turno, arrayHorarios[k]).getId()); } } } } List<Date>
       * feriados = this.feriadoDao.getDatasFeriadosByPeriodo(solicitacao.getIdPeriodo()); List<Date> datasAgendamento =
       * ManipuladorDatas.retornaDatasSemSabadosDomingosFeriados(
       * dev.home.componente.utils.date.DateUtils.datasEntre(solicitacao.getDataAgendamentoInicio(),
       * solicitacao.getDataAgendamentoTermino()), feriados); for (Long idHorario : idsHorarios) { DisponibilidadeSala disponibilidadeSala =
       * new DisponibilidadeSala(); disponibilidadeSala.setIdHorarioSala(idHorario);
       * disponibilidadeSala.setIdPeriodo(solicitacao.getIdPeriodo()); disponibilidadeSala.setIdSala(sala.getId());
       * disponibilidadeSala.setIdTurma(turma.getId()); for (Date data : DateUtils.datasEntre(periodo.getDataInicio(),
       * periodo.getDataTermino())) { disponibilidadeSala.setDataReserva(data); } this.disponibilidadeSalaDao.save(disponibilidadeSala); } }
       * else { List<Long> idsHorarios = new ArrayList<Long>(0); solicitacao.setHorarioTurma("7" + solicitacao.getHorarioTurma()); for (int
       * h = 0; h < auxiliar.contadorDeGruposComSabado(solicitacao.getHorarioTurma()); h++) { String grupo =
       * auxiliar.retornaGrupoComSabado(solicitacao.getHorarioTurma(), h); String[] arrayDias = auxiliar.retornaArrayDias(grupo); String
       * turno = auxiliar.retornaTurno(grupo); String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo); for (int i = 0; i <
       * arrayDias.length; i++) { for (int k = 0; k < arrayHorarios.length; k++) {
       * idsHorarios.add(this.horarioSalaDao.getByDiaTurnoEHorario(arrayDias[i], turno, arrayHorarios[k]).getId()); } } }
       * solicitacao.setHorarioTurmaOpcional("7" + solicitacao.getHorarioTurmaOpcional()); if
       * (auxiliar.validarHorarioSemDias(solicitacao.getHorarioTurmaOpcional())) { for (int h = 0; h <
       * auxiliar.contadorDeGruposComSabado(solicitacao.getHorarioTurmaOpcional()); h++) { String grupo =
       * auxiliar.retornaGrupoComSabado(solicitacao.getHorarioTurmaOpcional(), h); String[] arrayDias = auxiliar.retornaArrayDias(grupo);
       * String turno = auxiliar.retornaTurno(grupo); String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo); for (int i = 0; i <
       * arrayDias.length; i++) { for (int k = 0; k < arrayHorarios.length; k++) {
       * idsHorarios.add(this.horarioSalaDao.getByDiaTurnoEHorario(arrayDias[i], turno, arrayHorarios[k]).getId()); } } } } for (Sala sala :
       * this.agendamentoDao.getSalasDisponiveisPorHorarioEData(solicitacao.getIdPredio(), solicitacao.getIdPeriodo(), idsHorarios,
       * solicitacao.getDatasParaAgendamento())) { DadosConsultarSalasParaMarcacoes dado = new DadosConsultarSalasParaMarcacoes();
       * dado.setCapacidade(sala.getCapacidade()); dado.setId(sala.getId()); dado.setNome(sala.getNome());
       * dado.setTipoQuadro(sala.getTipoQuadro()); Tipo perfil = this.tipoSalaDao.get(sala.getIdTipo());
       * dado.setPerfilSala(perfil.getNome()); dados.add(dado); } }
       */
      return new RespostaAgendarHorario();
   }

   @Override
   public void valida(AgendarHorario solicitacao) throws NegocioException {
      if (solicitacao.getIdInteressado() == null || solicitacao.getIdInteressado() == 0) {
         throw new NegocioException(ErrorCode.INTERESSADO_VAZIO);
      }
      if (StringUtils.isBlank(solicitacao.getMotivo())) {
         throw new NegocioException(ErrorCode.MOTIVO_VAZIO);
      }
      if ((solicitacao.getDatasParaAgendamento() == null || solicitacao.getDatasParaAgendamento().size() == 0)
         && (solicitacao.getDataAgendamentoInicio() == null || solicitacao.getDataAgendamentoTermino() == null)) {
         throw new NegocioException(ErrorCode.DATA_VAZIO);
      }

      if (solicitacao.getDatasParaAgendamento() != null && solicitacao.getDatasParaAgendamento().size() > 0) {
         solicitacao.setHorario(ManipuladorDatas.retornaListaDiasDaSemana(solicitacao.getDatasParaAgendamento())
            + solicitacao.getHorario());

         if (!solicitacao.getHorarioOpcional().equals("")) {
            // Formatando o horario opcional
            solicitacao.setHorarioOpcional(ManipuladorDatas.retornaListaDiasDaSemana(solicitacao.getDatasParaAgendamento())
               + solicitacao.getHorarioOpcional());
            // Verificando a disponibilidade da sala no horario 'normal' e no horario opcional
            if (!this.disponibilidadeSalaDao.verificarDisponibilidadeSalaParaAgendamentos(solicitacao.getHorario(),
               solicitacao.getIdSala(),
               solicitacao.getDatasParaAgendamento(), solicitacao.getIdPeriodo())
               && !this.disponibilidadeSalaDao.verificarDisponibilidadeSalaParaAgendamentos(solicitacao.getHorarioOpcional(),
                  solicitacao.getIdSala(), solicitacao.getDatasParaAgendamento(), solicitacao.getIdPeriodo())) {
               throw new NegocioException(ErrorCode.NAO_PODE_REALIZAR_ALOCACAO);
            }
         } else {
            // Verificando a disponibilidade da sala somente no horario 'normal'
            if (!this.disponibilidadeSalaDao.verificarDisponibilidadeSalaParaAgendamentos(solicitacao.getHorario(),
               solicitacao.getIdSala(), solicitacao.getDatasParaAgendamento(), solicitacao.getIdPeriodo())) {

            }
         }
      } else {
         if (solicitacao.getDiasDaSemana() == null || solicitacao.getDiasDaSemana().size() == 0) {
            throw new NegocioException(ErrorCode.DIAS_SEMANA_NAO_INFORMADOS);
         }
         for (String dia : solicitacao.getDiasDaSemana()) {
            solicitacao.setHorario(dia + solicitacao.getHorario());
         }
         List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(solicitacao.getIdPeriodo());
         List<Date> datasParaReserva =
            ManipuladorDatas.retornaDatasPorDiasSemFeriados(
               dev.home.componente.utils.date.DateUtils.datasEntre(solicitacao.getDataAgendamentoInicio(),
                  solicitacao.getDataAgendamentoTermino()), feriados, solicitacao.getHorario());
         if (!solicitacao.getHorarioOpcional().equals("")) {
            // Formatando o horario opcional
            for (String dia : solicitacao.getDiasDaSemana()) {
               solicitacao.setHorarioOpcional(dia + solicitacao.getHorarioOpcional());
            }
            // Verificando a disponibilidade da sala no horario 'normal' e no horario opcional
            if (!this.disponibilidadeSalaDao.verificarDisponibilidadeSalaParaAgendamentos(solicitacao.getHorario(),
               solicitacao.getIdSala(), datasParaReserva, solicitacao.getIdPeriodo())
               && !this.disponibilidadeSalaDao.verificarDisponibilidadeSalaParaAgendamentos(solicitacao.getHorarioOpcional(),
                  solicitacao.getIdSala(), datasParaReserva, solicitacao.getIdPeriodo())) {
               throw new NegocioException(ErrorCode.NAO_PODE_REALIZAR_ALOCACAO);
            }
         } else {
            // Verificando a disponibilidade da sala somente no horario 'normal'
            if (!this.disponibilidadeSalaDao.verificarDisponibilidadeSalaParaAgendamentos(solicitacao.getHorario(),
               solicitacao.getIdSala(), datasParaReserva, solicitacao.getIdPeriodo())) {

            }
         }
      }

      /*
       * Turma t1 = new Turma(); t1.setHorario(solicitacao.getHorario()); t1.setIdPeriodo(solicitacao.getIdPeriodo()); if
       * (!solicitacao.getHorarioOpcional().equals("")) { Turma t2 = new Turma(); t2.setHorario(solicitacao.getHorarioOpcional());
       * t2.setIdPeriodo(solicitacao.getIdPeriodo()); if (!this.disponibilidadeSalaDao.verificarDisponibilidadeSala(t1,
       * solicitacao.getIdSala()) && !this.disponibilidadeSalaDao.verificarDisponibilidadeSala(t2, solicitacao.getIdSala())) { throw new
       * NegocioException(ErrorCode.NAO_PODE_REALIZAR_ALOCACAO); } } else { if
       * (!this.disponibilidadeSalaDao.verificarDisponibilidadeSala(t1, solicitacao.getIdSala())) { throw new
       * NegocioException(ErrorCode.NAO_PODE_REALIZAR_ALOCACAO); } }
       */
      /*
       * String diasDaSemana = ""; for (Date d : solicitacao.getDatasParaAgendamento()) { diasDaSemana = diasDaSemana +
       * String.valueOf(d.getDay() + 1); } solicitacao.setHorario(diasDaSemana + solicitacao.getHorario());
       * solicitacao.setHorarioOpcional(diasDaSemana + solicitacao.getHorarioOpcional());
       */
   }

   /**
    * Atribui o novo valor de agendamentoDao
    * @param agendamentoDao agendamentoDao que será atribuído
    */
   public void setAgendamentoDao(AgendamentoDao agendamentoDao) {
      this.agendamentoDao = agendamentoDao;
   }

   /**
    * Atribui o novo valor de feriadoDao
    * @param feriadoDao feriadoDao que será atribuído
    */
   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   public void setDisponibilidadeSalaAgendamentoBackupDao(DisponibilidadeSalaAgendamentoBackupDao disponibilidadeSalaAgendamentoBackupDao) {
      this.disponibilidadeSalaAgendamentoBackupDao = disponibilidadeSalaAgendamentoBackupDao;
   }

   public void setUsuarioDao(UsuarioDao usuarioDao) {
      this.usuarioDao = usuarioDao;
   }
      
}
