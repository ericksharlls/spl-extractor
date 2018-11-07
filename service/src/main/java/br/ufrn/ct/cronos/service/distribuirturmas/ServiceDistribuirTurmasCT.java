package br.ufrn.ct.cronos.service.distribuirturmas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.DisponibilidadeSala;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.service.ManipuladorDatas;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.utils.date.DateUtils;

public class ServiceDistribuirTurmasCT implements IServiceDistribuirTurmas {

   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private TurmaDao turmaDao;
   private PeriodoDao periodoDao;
   private FeriadoDao feriadoDao;
   private HorarioDao horarioDao;
   private SalaDao salaDao;

   private FuncionarioDao funcionarioDao;

   @Override
   public void processa(Long idPeriodo, Long idPredio) throws NegocioException {
      System.out.println("!! METODO PROCESSA -- Service do CT !!");
      Auxiliar auxiliar = new Auxiliar();
      Teste2 teste2 = new Teste2();
      Periodo periodo = this.periodoDao.get(idPeriodo);

      // Passo 1 na Distribuicao - Distribuir as turmas q SAO CONSECUTIVAS E QUE EXISTIAM no semestre letivo anterior MAS que ESTAO SEM SALA

      // Obter os nomes de todos os docentes
      // for (String d : this.turmaDao.getAllNomesDocentes(idPeriodo, idPredio)) {
      for (Funcionario d : this.funcionarioDao.getAllByTipoFuncionario(new Long(1))) { // ##### REFATORAR


         // Obter as turmas por docentes
         // for (Turma t1 : this.turmaDao.getTurmasPorDocente(d, idPeriodo, idPredio)) {
         for (Turma t1 : this.turmaDao.getTurmasPorDocente(d.getId(), idPeriodo, idPredio)) { // ##### REFATORAR

            // Eliminar as turmas que tiver sem docente definido
            // if (!t1.getDocente().replaceAll(" ", "").equals("ADEFINIRDOCENTE")) { ##### DEIXAR COMENTADO (20-07-2017)

               if (auxiliar.validarHorarioComSabado(t1.getHorario())) {
                  // Criando lista das turmas com horarios consecutivos
                  List<Turma> consecutivos = new ArrayList<Turma>(0);

                  // Montando lista das turmas com horarios consecutivos
               // for (Turma tu : this.turmaDao.getTurmasPorDocente(d, idPeriodo, idPredio)) {
               for (Turma tu : this.turmaDao.getTurmasPorDocente(d.getId(), idPeriodo, idPredio)) { // ##### REFATORAR
                     if ((t1.getIdTipo().equals(tu.getIdTipo())) && (teste2.saoConsecutivos(t1, tu)) && tu.getDistribuir()) {
                        if (!consecutivos.contains(t1)) {
                           consecutivos.add(t1);
                        }
                        consecutivos.add(tu);
                     }
                  }

                  // Iterando as turmas com horarios consecutivos
                  for (Turma turma : consecutivos) {

                     Periodo periodoLetivoAnterior = this.periodoDao.getPeriodoLetivoAnterior();
                     // Verificar se existe Periodo Letivo Anterior no Sistema. Para o sistema comecando DO ZERO, NUNCA vai existir.
                     if (periodoLetivoAnterior != null) {
                        Turma turmaAnteriorSemelhanteUm =
                           this.turmaDao.getTurmaAnteriorSemelhantePorPeriodo(turma, periodoLetivoAnterior.getId());
                     if (turmaAnteriorSemelhanteUm != null && !this.disponibilidadeSalaDao.verificarTurmaTemSala(turma.getId())) {
                    	 
                    	 // Testes no dia 23/07/2018
                    	 //turmaAnteriorSemelhanteUm
                    	 
                           // Obter a sala dessa turma
                           Sala s = this.disponibilidadeSalaDao.getSalaPorTurma(turmaAnteriorSemelhanteUm.getId());

                           // Verificar se jah tem sala mesmo
                           if (s != null) {

                              // Novamente iterando as mesmas turmas com horarios consecutivos
                              for (Turma t : consecutivos) {

                                 // Verificando se estou obtendo uma turma q n seja a q jah tem sala
                                 if (!t.equals(turma)) { // Qualquer problema, eh soh comentar essa linha e descomentar a de baixo
                                    // if (!consecutivos.equals(turma)) {

                                    // Verificar se a turma em questao jah tem sala.. Esse teste eh feito, pq corre o risco de mais de uma
                                    // turma
                                    // consecutiva jah ter uma sala. Caso tenha, permanecera onde estah
                                    if (!this.disponibilidadeSalaDao.verificarTurmaTemSala(t.getId())) {

                                       if (this.disponibilidadeSalaDao.verificarDisponibilidadeSala(t, s.getId())) {

                                          for (int h = 0; h < auxiliar.contadorDeGruposComSabado(t.getHorario()); h++) {
                                             List<Long> idsHorarios = new ArrayList<Long>(0);
                                             String grupo = auxiliar.retornaGrupoComSabado(t.getHorario(), h);
                                             String turno = auxiliar.retornaTurno(grupo);
                                             String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

                                             for (int k = 0; k < arrayHorarios.length; k++) {
                                                idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno,
                                                   Integer.parseInt(arrayHorarios[k]))
                                                         .getId());
                                             }
                                             List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(periodo.getId());
                                             List<Date> datasParaReserva =
                                                ManipuladorDatas.retornaDatasLetivasPorDias(DateUtils.datasEntre(periodo.getDataInicio(),
                                                   periodo.getDataTermino()), feriados, grupo);
                                             Collections.sort(datasParaReserva, new Comparator<Date>() {

                                                public int compare(Date d1, Date d2) {
                                                   return d1.compareTo(d2);
                                                }
                                             });

                                             for (Long idHorario : idsHorarios) {
                                                DisponibilidadeSala disponibilidadeSala = new DisponibilidadeSala();
                                                disponibilidadeSala.setIdHorarioSala(idHorario);
                                                disponibilidadeSala.setIdPeriodo(t.getIdPeriodo());
                                                disponibilidadeSala.setIdSala(s.getId());
                                                disponibilidadeSala.setIdTurma(t.getId());

                                                for (Date data : datasParaReserva) {
                                                   disponibilidadeSala.setDataReserva(data);
                                                   this.disponibilidadeSalaDao.save(disponibilidadeSala);
                                                }
                                             }
                                          }
                                          t.setDistribuir(false);
                                          this.turmaDao.merge(t);

                                          // Distribuindo o objeto turma
                                          if (!this.disponibilidadeSalaDao.verificarTurmaTemSala(turma.getId())) {

                                             if (this.disponibilidadeSalaDao.verificarDisponibilidadeSala(turma, s.getId())) {

                                                for (int h = 0; h < auxiliar.contadorDeGruposComSabado(turma.getHorario()); h++) {
                                                   List<Long> idsHorarios = new ArrayList<Long>(0);
                                                   String grupo = auxiliar.retornaGrupoComSabado(turma.getHorario(), h);
                                                   String turno = auxiliar.retornaTurno(grupo);
                                                   String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

                                                   for (int k = 0; k < arrayHorarios.length; k++) {
                                                      idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno,
                                                         Integer.parseInt(arrayHorarios[k]))
                                                               .getId());
                                                   }
                                                   List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(periodo.getId());
                                                   List<Date> datasParaReserva =
                                                      ManipuladorDatas.retornaDatasLetivasPorDias(
                                                         DateUtils.datasEntre(periodo.getDataInicio(),
                                                            periodo.getDataTermino()), feriados, grupo);
                                                   Collections.sort(datasParaReserva, new Comparator<Date>() {

                                                      public int compare(Date d1, Date d2) {
                                                         return d1.compareTo(d2);
                                                      }
                                                   });

                                                   for (Long idHorario : idsHorarios) {
                                                      DisponibilidadeSala disponibilidadeSala = new DisponibilidadeSala();
                                                      disponibilidadeSala.setIdHorarioSala(idHorario);
                                                      disponibilidadeSala.setIdPeriodo(turma.getIdPeriodo());
                                                      disponibilidadeSala.setIdSala(s.getId());
                                                      disponibilidadeSala.setIdTurma(turma.getId());

                                                      for (Date data : datasParaReserva) {
                                                         disponibilidadeSala.setDataReserva(data);
                                                         this.disponibilidadeSalaDao.save(disponibilidadeSala);
                                                      }
                                                   }
                                                }
                                                turma.setDistribuir(false);
                                                this.turmaDao.merge(turma);
                                             }
                                          }
                                          // Finalizada a distribuicao do objeto turma
                                       }

                                    }
                                 }
                              }
                           }
                        }
                     }

                  }
               }
            // }
         }

      }
      System.out.println("### FINALIZADO PASSO 1 ###");


      // -------------------------------
      // Passo 2 na Distribuicao - Distribuir as turmas q SAO CONSECUTIVAS MAS QUE NAO EXISTIAM no semestre letivo anterior
      // Obter os nomes de todos os docentes
      // for (String d : this.turmaDao.getAllNomesDocentes(idPeriodo, idPredio)) {
      for (Funcionario d : this.funcionarioDao.getAllByTipoFuncionario(new Long(1))) { // ##### REFATORAR

         // Obter as turmas por docentes
         // for (Turma t1 : this.turmaDao.getTurmasPorDocente(d, idPeriodo, idPredio)) {
         for (Turma t1 : this.turmaDao.getTurmasPorDocente(d.getId(), idPeriodo, idPredio)) { // ##### REFATORAR

            // Eliminar as turmas que tiver sem docente definido
            // if (!t1.getDocente().replaceAll(" ", "").equals("ADEFINIRDOCENTE") && t1.getDistribuir() == true
            // && auxiliar.validarHorarioComSabado(t1.getHorario())) { ###### DEIXAR COMENTADO (20-07-2017)
            if (t1.getDistribuir() == true && auxiliar.validarHorarioComSabado(t1.getHorario())) {

               // Criando lista das turmas com horarios consecutivos
               List<Turma> consecutivos = new ArrayList<Turma>(0);

               // Montando lista das turmas com horarios consecutivos
               // for (Turma tu : this.turmaDao.getTurmasPorDocente(d, idPeriodo, idPredio)) {
               for (Turma tu : this.turmaDao.getTurmasPorDocente(d.getId(), idPeriodo, idPredio)) { // ###### REFATORAR
                  if ((t1.getIdTipo().equals(tu.getIdTipo())) && (teste2.saoConsecutivos(t1, tu)) && tu.getDistribuir()) {
                     if (!consecutivos.contains(t1)) {
                        consecutivos.add(t1);
                     }
                     consecutivos.add(tu);
                  }
               }

               for (Sala s : this.salaDao.getAllDiponiveisParaDistribuicao(idPredio)) {
                  int contadorVerdadeiros = 0;
                  for (Turma t2 : consecutivos) {
                     if ((t2.getIdTipo().equals(s.getIdTipo()) && t2.getCapacidade() <= s.getCapacidade())
                        && (this.disponibilidadeSalaDao.verificarDisponibilidadeSala(t2, s.getId()))) {
                        contadorVerdadeiros++;
                     }
                  }
                  if (contadorVerdadeiros == consecutivos.size()) {
                     for (Turma t3 : consecutivos) {
                        this.turmaDao.refresh(t3);
                        if (t3.getDistribuir() && !t3.getCodigoDisciplina().equals("DEQ0353")) {
                           for (int h = 0; h < auxiliar.contadorDeGruposComSabado(t3.getHorario()); h++) {
                              List<Long> idsHorarios = new ArrayList<Long>(0);
                              String grupo = auxiliar.retornaGrupoComSabado(t3.getHorario(), h);
                              String turno = auxiliar.retornaTurno(grupo);
                              String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

                              for (int k = 0; k < arrayHorarios.length; k++) {
                                 idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k]))
                                          .getId());
                              }
                              List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(periodo.getId());
                              List<Date> datasParaReserva =
                                 ManipuladorDatas.retornaDatasLetivasPorDias(DateUtils.datasEntre(periodo.getDataInicio(),
                                    periodo.getDataTermino()), feriados, grupo);
                              Collections.sort(datasParaReserva, new Comparator<Date>() {

                                 public int compare(Date d1, Date d2) {
                                    return d1.compareTo(d2);
                                 }
                              });
                              for (Long idHorario : idsHorarios) {
                                 DisponibilidadeSala disponibilidadeSala = new DisponibilidadeSala();
                                 disponibilidadeSala.setIdHorarioSala(idHorario);
                                 disponibilidadeSala.setIdPeriodo(t3.getIdPeriodo());
                                 disponibilidadeSala.setIdSala(s.getId());
                                 disponibilidadeSala.setIdTurma(t3.getId());

                                 for (Date data : datasParaReserva) {
                                    disponibilidadeSala.setDataReserva(data);
                                    this.disponibilidadeSalaDao.save(disponibilidadeSala);
                                 }
                              }
                           }

                           t3.setDistribuir(false);
                           this.turmaDao.merge(t3);
                        }
                     }
                     break;
                  }
               }
            }
         }
      }
      System.out.println("### FINALIZADO PASSO 2 ###");

      // ---------------------------------------------
      // Passo 3 na Distribuicao - Distribuir as turmas q nao sao consecutivas MAS QUE EXISTIAM no semestre letivo anterior
      for (Turma t : this.turmaDao.getTurmasNaoDistribuidas(idPeriodo, idPredio)) {
         if (!this.disponibilidadeSalaDao.verificarTurmaTemSala(t.getId()) && auxiliar.validarHorarioComSabado(t.getHorario())) {

            Periodo periodoLetivoAnterior = this.periodoDao.getPeriodoLetivoAnterior();
            // Verificar se existe Periodo Letivo Anterior no Sistema. Para o sistema comecando DO ZERO, NUNCA vai existir.
            if (periodoLetivoAnterior != null) {
               Turma turmaAnteriorSemelhante =
                  this.turmaDao.getTurmaAnteriorSemelhantePorPeriodo(t, periodoLetivoAnterior.getId());
               if (turmaAnteriorSemelhante != null && !this.disponibilidadeSalaDao.verificarTurmaTemSala(t.getId()) && !turmaAnteriorSemelhante.getCodigoDisciplina().equals("DEQ0503")
            		   &&!turmaAnteriorSemelhante.getCodigoDisciplina().equals("DEQ0526")) {
                  Sala sala = this.disponibilidadeSalaDao.getSalaPorTurma(turmaAnteriorSemelhante.getId());
                  if (sala != null && this.disponibilidadeSalaDao.verificarDisponibilidadeSala(t, sala.getId())) {

                     for (int h = 0; h < auxiliar.contadorDeGruposComSabado(t.getHorario()); h++) {
                        List<Long> idsHorarios = new ArrayList<Long>(0);
                        String grupo = auxiliar.retornaGrupoComSabado(t.getHorario(), h);
                        String turno = auxiliar.retornaTurno(grupo);
                        String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

                        for (int k = 0; k < arrayHorarios.length; k++) {
                           idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k]))
                                    .getId());
                        }
                        List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(periodo.getId());
                        List<Date> datasParaReserva =
                           ManipuladorDatas.retornaDatasLetivasPorDias(DateUtils.datasEntre(periodo.getDataInicio(),
                              periodo.getDataTermino()), feriados, grupo);
                        Collections.sort(datasParaReserva, new Comparator<Date>() {

                           public int compare(Date d1, Date d2) {
                              return d1.compareTo(d2);
                           }
                        });
                        for (Long idHorario : idsHorarios) {
                           DisponibilidadeSala disponibilidadeSala = new DisponibilidadeSala();
                           disponibilidadeSala.setIdHorarioSala(idHorario);
                           disponibilidadeSala.setIdPeriodo(t.getIdPeriodo());
                           disponibilidadeSala.setIdSala(sala.getId());
                           disponibilidadeSala.setIdTurma(t.getId());

                           for (Date data : datasParaReserva) {
                              disponibilidadeSala.setDataReserva(data);
                              this.disponibilidadeSalaDao.save(disponibilidadeSala);
                           }
                        }
                     }

                     t.setDistribuir(false);
                     this.turmaDao.merge(t);

                  }
               }
            }
         }
      }
      System.out.println("### FINALIZADO PASSO 3 ###");

      // ---------------------------------------------
      // Passo 4 na Distribuicao - Distribuir as turmas q nao sao consecutivas e q NAO EXISTIAM no semestre letivo anterior
      for (Turma t : this.turmaDao.getTurmasNaoDistribuidas(idPeriodo, idPredio)) {
         if (!this.disponibilidadeSalaDao.verificarTurmaTemSala(t.getId()) && auxiliar.validarHorarioComSabado(t.getHorario())) {
            Sala salaDisponivel = this.disponibilidadeSalaDao.getSalaDisponivelPorTurma(t);
            if (salaDisponivel != null) {

               for (int h = 0; h < auxiliar.contadorDeGruposComSabado(t.getHorario()); h++) {
                  List<Long> idsHorarios = new ArrayList<Long>(0);
                  String grupo = auxiliar.retornaGrupoComSabado(t.getHorario(), h);
                  String turno = auxiliar.retornaTurno(grupo);
                  String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

                  for (int k = 0; k < arrayHorarios.length; k++) {
                     idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k]))
                              .getId());
                  }
                  List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(periodo.getId());
                  List<Date> datasParaReserva =
                     ManipuladorDatas.retornaDatasLetivasPorDias(DateUtils.datasEntre(periodo.getDataInicio(),
                        periodo.getDataTermino()), feriados, grupo);
                  Collections.sort(datasParaReserva, new Comparator<Date>() {

                     public int compare(Date d1, Date d2) {
                        return d1.compareTo(d2);
                     }
                  });
                  for (Long idHorario : idsHorarios) {
                     DisponibilidadeSala disponibilidadeSala = new DisponibilidadeSala();
                     disponibilidadeSala.setIdHorarioSala(idHorario);
                     disponibilidadeSala.setIdPeriodo(t.getIdPeriodo());
                     disponibilidadeSala.setIdSala(salaDisponivel.getId());
                     disponibilidadeSala.setIdTurma(t.getId());

                     for (Date data : datasParaReserva) {
                        disponibilidadeSala.setDataReserva(data);
                        this.disponibilidadeSalaDao.save(disponibilidadeSala);
                     }
                  }
               }

               t.setDistribuir(false);
               this.turmaDao.merge(t);

            }

         }
      }
      System.out.println("### FINALIZADO PASSO 4 ###");
      System.out.println("### ServiceDistribuirTurmasCT EXECUTADO COM SUCESSO ###");
   }

   @Override
   public void valida(Long idPeriodo, Long idPredio) throws NegocioException {
      System.out.println("!! METODO VALIDA -- Service do CT !!");
      if (idPeriodo == 0 || idPeriodo == null) {
         throw new NegocioException(ErrorCode.ID_PERIODO);
      }
      if (idPredio == 0 || idPredio == null) {
         throw new NegocioException(ErrorCode.ID_PREDIO);
      }
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

   public void setSalaDao(SalaDao salaDao) {
      this.salaDao = salaDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

}
