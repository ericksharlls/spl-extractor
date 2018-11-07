package br.ufrn.ct.cronos.service.relatoriosalas;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.ParametrosRelatoriosDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.dao.TurmaDocenteDao;
import br.ufrn.ct.cronos.entity.DisponibilidadeSala;
import br.ufrn.ct.cronos.entity.Horario;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.entity.TurmaDocente;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.relatoriosalas.vo.DadosSala;
import br.ufrn.ct.cronos.relatoriosalas.vo.DadosTurma;
import br.ufrn.ct.cronos.relatoriosalas.vo.RelatorioSalas;
import br.ufrn.ct.cronos.relatoriosalas.vo.RespostaRelatorioSalas;
import br.ufrn.ct.cronos.service.ManipuladorNomes;
import br.ufrn.ct.cronos.service.ParametrosRelatoriosEnum;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRelatorioSalas extends AbstractService<RelatorioSalas, RespostaRelatorioSalas> {

   private TurmaDao turmaDao;
   private SalaDao salaDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private PeriodoDao periodoDao;
   private HorarioDao horarioDao;
   private ParametrosRelatoriosDao parametrosRelatoriosDao;
   private FuncionarioDao funcionarioDao;
   private TurmaDocenteDao turmaDocenteDao;

   public RespostaRelatorioSalas processa(RelatorioSalas solicitacao) throws NegocioException {
      List<DadosSala> dadosSalas = new ArrayList<DadosSala>();

      for (Sala s : this.salaDao.getAllByPredio(solicitacao.getIdPredio())) {
         for (int i = 0; i < 3; i++) {
            switch (i) {
               case 0:
                  DadosSala d1 = new DadosSala();
                  d1.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
                           .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
                  d1.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
                           .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
                  d1.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
                           .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());
                  d1.setSala(s.getNome() + " - MANH\u00C3");
                  d1.setSemestre(this.periodoDao.get(solicitacao.getIdPeriodo()).getNome());
                  // d1.setDistribuicoes(getTurmasManha(this.distribuicaoDao.getDistribuicoes(s.getId(), "M")));
                  d1.setDistribuicoes(getTurmasManha(this.disponibilidadeSalaDao.getDisponibilidadesSalaPorSalaTurnoEPeriodo(s.getId(),
                     "M",
                     solicitacao.getIdPeriodo())));

                  dadosSalas.add(d1);
                  break;
               case 1:
                  DadosSala d2 = new DadosSala();
                  d2.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
                           .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
                  d2.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
                           .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
                  d2.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
                           .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());
                  d2.setSala(s.getNome() + " - TARDE");
                  d2.setSemestre(this.periodoDao.get(solicitacao.getIdPeriodo()).getNome());
                  // d2.setDistribuicoes(getTurmasTarde(this.distribuicaoDao.getDistribuicoes(s.getId(), "T")));
                  d2.setDistribuicoes(getTurmasTarde(this.disponibilidadeSalaDao.getDisponibilidadesSalaPorSalaTurnoEPeriodo(s.getId(),
                     "T",
                     solicitacao.getIdPeriodo())));

                  dadosSalas.add(d2);
                  break;
               case 2:
                  DadosSala d3 = new DadosSala();
                  d3.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
                           .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
                  d3.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
                           .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
                  d3.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
                           .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());
                  d3.setSala(s.getNome() + " - NOITE");
                  d3.setSemestre(this.periodoDao.get(solicitacao.getIdPeriodo()).getNome());
                  // d3.setDistribuicoes(getTurmasNoite(this.distribuicaoDao.getDistribuicoes(s.getId(), "N")));
                  d3.setDistribuicoes(getTurmasNoite(this.disponibilidadeSalaDao.getDisponibilidadesSalaPorSalaTurnoEPeriodo(s.getId(),
                     "N",
                     solicitacao.getIdPeriodo())));

                  dadosSalas.add(d3);
            }

         }

      }

      return new RespostaRelatorioSalas(dadosSalas);
   }

   public void valida(RelatorioSalas solicitacao) throws NegocioException {
      if (this.disponibilidadeSalaDao.findAll().size() < 1) {
         throw new NegocioException(ErrorCode.DISTRIBUICAO_NAO_REALIZADA);
      }
      if (solicitacao.getIdPeriodo() == 0 || solicitacao.getIdPeriodo() == null) {
         throw new NegocioException(ErrorCode.ID_PERIODO);
      }
      if (solicitacao.getIdPredio() == 0 || solicitacao.getIdPredio() == null) {
         throw new NegocioException(ErrorCode.ID_PREDIO);
      }

   }

   public List<DadosTurma> getTurmasManha(List<DisponibilidadeSala> distribuicoes) {
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
                        dadosTurma.setHorarioSegunda("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + "\nProf.: " + obterDocente(turma.getId()));
                     } else if (dia == 3) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioTerca("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero() + " \nProf.: "
                           + obterDocente(turma.getId()));
                     } else if (dia == 4) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        System.out.println("### Quem es? " + disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioQuarta("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     } else if (dia == 5) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioQuinta("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     } else if (dia == 6) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSexta("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero() + " \nProf.: "
                           + obterDocente(turma.getId()));
                     } else if (dia == 7) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSabado("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     }
                  }
               }
            }

         }

         dados.add(dadosTurma);
      }
      return dados;
   }

   public List<DadosTurma> getTurmasTarde(List<DisponibilidadeSala> distribuicoes) {
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
                        dadosTurma.setHorarioSegunda("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     } else if (dia == 3) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioTerca("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     } else if (dia == 4) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioQuarta("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     } else if (dia == 5) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioQuinta("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     } else if (dia == 6) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSexta("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     } else if (dia == 7) {
                        Turma turma = this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSabado("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     }
                  }
               }

            }

         }

         dados.add(dadosTurma);
      }
      return dados;
   }

   public List<DadosTurma> getTurmasNoite(List<DisponibilidadeSala> distribuicoes) {
      List<DadosTurma> dados = new ArrayList<DadosTurma>(0);

      for (int horario = 1; horario < 5; horario++) {
         DadosTurma dadosTurma = new DadosTurma();

         if (horario == 1)
            dadosTurma.setHorario("18:45 \u00E0s 19:35");
         else if (horario == 2)
            dadosTurma.setHorario("19:35 \u00E0s 20:25");
         else if (horario == 3)
            dadosTurma.setHorario("20:35 \u00E0s 21:25");
         else if (horario == 4) {
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
                        Turma turma = (Turma) this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSegunda("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     } else if (dia == 3) {
                        Turma turma = (Turma) this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioTerca("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     } else if (dia == 4) {
                        Turma turma = (Turma) this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioQuarta("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     } else if (dia == 5) {
                        Turma turma = (Turma) this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioQuinta("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     } else if (dia == 6) {
                        Turma turma = (Turma) this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSexta("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
                     } else if (dia == 7) {
                        Turma turma = (Turma) this.turmaDao.get(disponibilidadeSala.getIdTurma());
                        dadosTurma.setHorarioSabado("Disc.: " + turma.getCodigoDisciplina() + "\nTurma: " + turma.getNumero()
                           + " \nProf.: " + obterDocente(turma.getId()));
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

   public void setSalaDao(SalaDao salaDao) {
      this.salaDao = salaDao;
   }

   /**
    * Atribui o novo valor de disponibilidadeSalaDao
    * @param disponibilidadeSalaDao disponibilidadeSalaDao que será atribuído
    */
   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

   public void setParametrosRelatoriosDao(ParametrosRelatoriosDao parametrosRelatoriosDao) {
      this.parametrosRelatoriosDao = parametrosRelatoriosDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

   public void setTurmaDocenteDao(TurmaDocenteDao turmaDocenteDao) {
      this.turmaDocenteDao = turmaDocenteDao;
   }

}
