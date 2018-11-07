package br.ufrn.ct.cronos.service.obterhorariosala;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import br.ufrn.ct.cronos.dao.AgendamentoDao;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.dao.TurmaDocenteDao;
import br.ufrn.ct.cronos.entity.Agendamento;
import br.ufrn.ct.cronos.entity.DisponibilidadeSala;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.entity.Horario;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.entity.TurmaDocente;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.obterhorariosala.vo.DadosLegais;
import br.ufrn.ct.cronos.obterhorariosala.vo.DadosTurma;
import br.ufrn.ct.cronos.obterhorariosala.vo.ObterHorarioSala;
import br.ufrn.ct.cronos.obterhorariosala.vo.RespostaObterHorarioSala;
import br.ufrn.ct.cronos.service.ManipuladorNomes;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceObterHorarioSala extends AbstractService<ObterHorarioSala, RespostaObterHorarioSala> {
      
   private TurmaDao turmaDao;
   private AgendamentoDao agendamentoDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private HorarioDao horarioDao;
   private FuncionarioDao funcionarioDao;
   private TurmaDocenteDao turmaDocenteDao;
      
   @Override
   public RespostaObterHorarioSala processa(ObterHorarioSala solicitacao) throws NegocioException {

         if (solicitacao.getTurno().equals("M")) {
            return new RespostaObterHorarioSala(getTurmasManha(
               this.disponibilidadeSalaDao.getDisponibilidadesSalaPorSalaTurnoEPeriodo(solicitacao.getIdSala(), solicitacao.getTurno(),
                  solicitacao.getIdPeriodo()),
               this.disponibilidadeSalaDao.getAgendamentosPorSalaTurnoEPeriodo(solicitacao.getIdSala(), solicitacao.getTurno(),
                  solicitacao.getIdPeriodo())));
         } else if (solicitacao.getTurno().equals("T")) {
            return new RespostaObterHorarioSala(getTurmasTarde(
               this.disponibilidadeSalaDao.getDisponibilidadesSalaPorSalaTurnoEPeriodo(solicitacao.getIdSala(), solicitacao.getTurno(),
                  solicitacao.getIdPeriodo()),
               this.disponibilidadeSalaDao.getAgendamentosPorSalaTurnoEPeriodo(solicitacao.getIdSala(), solicitacao.getTurno(),
                  solicitacao.getIdPeriodo())));
         } else if (solicitacao.getTurno().equals("N")) {
            return new RespostaObterHorarioSala(getTurmasNoite(
               this.disponibilidadeSalaDao.getDisponibilidadesSalaPorSalaTurnoEPeriodo(solicitacao.getIdSala(), solicitacao.getTurno(),
                  solicitacao.getIdPeriodo()),
               this.disponibilidadeSalaDao.getAgendamentosPorSalaTurnoEPeriodo(solicitacao.getIdSala(), solicitacao.getTurno(),
                  solicitacao.getIdPeriodo())));
         }

      return null;
   }
      
   @Override
   public void valida(ObterHorarioSala solicitacao) throws NegocioException {
      if (StringUtils.isBlank(solicitacao.getTurno()))
         throw new NegocioException(ErrorCode.TURNO_VAZIO);
   }
      
   public List<DadosTurma> getTurmasManha(List<DisponibilidadeSala> distribuicoes, List<DisponibilidadeSala> agendamentos) {
      List<DadosTurma> dados = new ArrayList<DadosTurma>(0);
         
      for (int horario = 1; horario < 7; horario++) {
         DadosTurma dadosTurma = new DadosTurma();
            
         if (horario == 1)
            dadosTurma.setHorario("7:00 \u00E0s 7:50");
         else if (horario == 2)
            dadosTurma.setHorario("7:50 \u00E0s 8:40");
         else if (horario == 3)
            dadosTurma.setHorario("8:55 \u00E0s 9:45");
         else if (horario == 4)
            dadosTurma.setHorario("9:45 \u00E0s 10:35");
         else if (horario == 5)
            dadosTurma.setHorario("10:50 \u00E0s 11:40");
         else if (horario == 6) {
            dadosTurma.setHorario("11:40 \u00E0s 12:30");
         }

         for (int dia = 2; dia < 8; dia++) {
            if (distribuicoes.size() > 0) {
               for (DisponibilidadeSala disponibilidadeSala : distribuicoes) {
                  Horario horarioSala = this.horarioDao.get(disponibilidadeSala.getIdHorarioSala());
                  GregorianCalendar data = new GregorianCalendar();
                  data.setTime(disponibilidadeSala.getDataReserva());
                  if ((horarioSala.getHorario() == horario) && (data.get(GregorianCalendar.DAY_OF_WEEK) == dia)) {
                     if (dia == 2) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSegunda(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 3) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioTerca(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 4) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioQuarta(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 5) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioQuinta(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 6) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSexta(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 7) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSabado(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     }
                  }
               }
            }
               
         }

         for (int dia = 2; dia < 8; dia++) {
            if (agendamentos.size() > 0) {
               for (DisponibilidadeSala disponibilidadeSala : agendamentos) {
                  Horario horarioSala = this.horarioDao.get(disponibilidadeSala.getIdHorarioSala());
                  GregorianCalendar data = new GregorianCalendar();
                  data.setTime(disponibilidadeSala.getDataReserva());
                  if ((horarioSala.getHorario() == horario) && (data.get(GregorianCalendar.DAY_OF_WEEK) == dia)) {
                     if (dia == 2) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosSegunda().add(dadoLegal);
                     } else if (dia == 3) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosTerca().add(dadoLegal);
                     } else if (dia == 4) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosQuarta().add(dadoLegal);
                     } else if (dia == 5) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosQuinta().add(dadoLegal);
                     } else if (dia == 6) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosSexta().add(dadoLegal);
                     } else if (dia == 7) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosSabado().add(dadoLegal);
                     }
                  }

               }
            }
               
         }
            
         dados.add(dadosTurma);
      }
      return dados;
   }

   public List<DadosTurma> getTurmasTarde(List<DisponibilidadeSala> distribuicoes, List<DisponibilidadeSala> agendamentos) {
      List<DadosTurma> dados = new ArrayList<DadosTurma>(0);

      for (int horario = 1; horario < 7; horario++) {
         DadosTurma dadosTurma = new DadosTurma();

         if (horario == 1)
            dadosTurma.setHorario("13:00 \u00E0s 13:50");
         else if (horario == 2)
            dadosTurma.setHorario("13:50 \u00E0s 14:40");
         else if (horario == 3)
            dadosTurma.setHorario("14:55 \u00E0s 15:45");
         else if (horario == 4)
            dadosTurma.setHorario("15:45 \u00E0s 16:35");
         else if (horario == 5)
            dadosTurma.setHorario("16:50 \u00E0s 17:40");
         else if (horario == 6) {
            dadosTurma.setHorario("17:40 \u00E0s 18:30");
         }

         for (int dia = 2; dia < 8; dia++) {
            if (distribuicoes.size() > 0) {
               for (DisponibilidadeSala disponibilidadeSala : distribuicoes) {
                  Horario horarioSala = this.horarioDao.get(disponibilidadeSala.getIdHorarioSala());
                  GregorianCalendar data = new GregorianCalendar();
                  data.setTime(disponibilidadeSala.getDataReserva());
                  if ((horarioSala.getHorario() == horario) && (data.get(GregorianCalendar.DAY_OF_WEEK) == dia)) {
                     if (dia == 2) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSegunda(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 3) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioTerca(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 4) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioQuarta(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 5) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioQuinta(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 6) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSexta(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 7) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSabado(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     }
                  }
               }
            }

         }

         for (int dia = 2; dia < 8; dia++) {
            if (agendamentos.size() > 0) {
               for (DisponibilidadeSala disponibilidadeSala : agendamentos) {
                  Horario horarioSala = this.horarioDao.get(disponibilidadeSala.getIdHorarioSala());
                  GregorianCalendar data = new GregorianCalendar();
                  data.setTime(disponibilidadeSala.getDataReserva());
                  if ((horarioSala.getHorario() == horario) && (data.get(GregorianCalendar.DAY_OF_WEEK) == dia)) {
                     if (dia == 2) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosSegunda().add(dadoLegal);
                     } else if (dia == 3) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosTerca().add(dadoLegal);
                     } else if (dia == 4) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosQuarta().add(dadoLegal);
                     } else if (dia == 5) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosQuinta().add(dadoLegal);
                     } else if (dia == 6) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosSexta().add(dadoLegal);
                     } else if (dia == 7) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosSabado().add(dadoLegal);
                     }
                  }

               }
            }

         }

         dados.add(dadosTurma);
      }
      return dados;
   }

   public List<DadosTurma> getTurmasNoite(List<DisponibilidadeSala> distribuicoes, List<DisponibilidadeSala> agendamentos) {
      List<DadosTurma> dados = new ArrayList<DadosTurma>(0);
      
      for (int horario = 1; horario < 5; horario++) {
         DadosTurma dadosTurma = new DadosTurma();

         if (horario == 1) {
            dadosTurma.setHorario("18:45 \u00E0s 19:35");
         } else if (horario == 2) {
            dadosTurma.setHorario("19:35 \u00E0s 20:25");
         } else if (horario == 3) {
            dadosTurma.setHorario("20:35 \u00E0s 21:25");
         } else if (horario == 4) {
            dadosTurma.setHorario("21:25 \u00E0s 22:15");
         }

         for (int dia = 2; dia < 8; dia++) {
            if (distribuicoes.size() > 0) {
               for (DisponibilidadeSala disponibilidadeSala : distribuicoes) {
                  Horario horarioSala = this.horarioDao.get(disponibilidadeSala.getIdHorarioSala());
                  GregorianCalendar data = new GregorianCalendar();
                  data.setTime(disponibilidadeSala.getDataReserva());
                  if ((horarioSala.getHorario() == horario) && (data.get(GregorianCalendar.DAY_OF_WEEK) == dia)) {
                     if (dia == 2) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSegunda(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 3) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioTerca(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 4) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioQuarta(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 5) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioQuinta(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 6) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSexta(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     } else if (dia == 7) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSabado(turma.getCodigoDisciplina() + " - " + "Turma " + turma.getNumero() + " - "
                           + obterDocente(turma.getId()));
                     }
                  }
               }
            }

         }

         for (int dia = 2; dia < 8; dia++) {
            if (agendamentos.size() > 0) {
               for (DisponibilidadeSala disponibilidadeSala : agendamentos) {
                  Horario horarioSala = this.horarioDao.get(disponibilidadeSala.getIdHorarioSala());
                  GregorianCalendar data = new GregorianCalendar();
                  data.setTime(disponibilidadeSala.getDataReserva());
                  if ((horarioSala.getHorario() == horario) && (data.get(GregorianCalendar.DAY_OF_WEEK) == dia)) {
                     if (dia == 2) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosSegunda().add(dadoLegal);
                     } else if (dia == 3) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosTerca().add(dadoLegal);
                     } else if (dia == 4) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosQuarta().add(dadoLegal);
                     } else if (dia == 5) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosQuinta().add(dadoLegal);
                     } else if (dia == 6) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosSexta().add(dadoLegal);
                     } else if (dia == 7) {
                        Agendamento ag = this.agendamentoDao.get(disponibilidadeSala.getIdAgendamento());
                        Funcionario interessado = this.funcionarioDao.get(ag.getIdFuncionario());
                        DadosLegais dadoLegal = new DadosLegais();
                        dadoLegal.setId(ag.getId());
                        dadoLegal.setHorario(ag.getMotivo() + " : " + interessado.getNome() + " : "
                           + disponibilidadeSala.getDataFormatoBrasileiro() + " : " + interessado.getTelefone());
                        dadosTurma.getHorariosSabado().add(dadoLegal);
                     }
                  }

               }
            }

         }

         dados.add(dadosTurma);
      }
      return dados;
   }

   private String obterDocente(Long idTurma) {
      List<TurmaDocente> listaTurmaDocente = this.turmaDocenteDao.getTurmaDocentePorTurma(idTurma);
      String docente = "";
      if (listaTurmaDocente.isEmpty()) {
         docente = "A DEFINIR DOCENTE";
      } else if (listaTurmaDocente.size() == 1) {
         for (TurmaDocente td : listaTurmaDocente) {
            docente += this.funcionarioDao.get(td.getIdDocente()).getNome() + " ";
         }
      } else {
         int contador = listaTurmaDocente.size();
         for (TurmaDocente td : listaTurmaDocente) {
            contador--;
            if (contador > 0) {
               docente += ManipuladorNomes.abreviaNome(this.funcionarioDao.get(td.getIdDocente()).getNome()) + ", ";
            } else {
               docente += ManipuladorNomes.abreviaNome(this.funcionarioDao.get(td.getIdDocente()).getNome());
            }
         }
      }
      return docente;
   }
      
   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }
      
   public void setAgendamentoDao(AgendamentoDao agendamentoDao) {
      this.agendamentoDao = agendamentoDao;
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

   public void setTurmaDocenteDao(TurmaDocenteDao turmaDocenteDao) {
      this.turmaDocenteDao = turmaDocenteDao;
   }
   
}
