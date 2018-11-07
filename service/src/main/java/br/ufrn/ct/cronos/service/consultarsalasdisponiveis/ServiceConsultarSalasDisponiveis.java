
package br.ufrn.ct.cronos.service.consultarsalasdisponiveis;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.consultarsalasdisponiveis.vo.ConsultarSalasDisponiveis;
import br.ufrn.ct.cronos.consultarsalasdisponiveis.vo.DadosConsultarSalasDisponiveis;
import br.ufrn.ct.cronos.consultarsalasdisponiveis.vo.RespostaConsultarSalasDisponiveis;
import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.PerfilSalaTurmaDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.PredioDao;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.dao.impl.ManipuladorDatas;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.PerfilSalaTurma;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.entity.Predio;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.utils.date.DateUtils;

public class ServiceConsultarSalasDisponiveis extends AbstractService<ConsultarSalasDisponiveis, RespostaConsultarSalasDisponiveis> {

   private SalaDao salaDao;
   private TurmaDao turmaDao;
   private PerfilSalaTurmaDao perfilSalaTurmaDao;
   private PredioDao predioDao;
   private PeriodoDao periodoDao;
   private FeriadoDao feriadoDao;
   private HorarioDao horarioDao;
   private Turma turma;
   private Auxiliar auxiliar;

   @Override
   public RespostaConsultarSalasDisponiveis processa(ConsultarSalasDisponiveis solicitacao) throws NegocioException {
      List<DadosConsultarSalasDisponiveis> dados = new ArrayList<DadosConsultarSalasDisponiveis>(0);

      if ((solicitacao.getIdTurma() != null) && (!solicitacao.getIdTurma().equals(new Long(0)))) {
         this.auxiliar = new Auxiliar();
         this.turma = this.turmaDao.get(solicitacao.getIdTurma());
         if (auxiliar.validarHorarioComSabado(turma.getHorario())) {
            if (solicitacao.getDesmembrarHorarioTurma()) {
               // turma = this.turmaDao.get(solicitacao.getIdTurma());
               // if (auxiliar.validarHorarioComSabado(turma.getHorario())) {
                  for (int h = 0; h < auxiliar.contadorDeGruposComSabado(turma.getHorario()); h++) {
                     String grupo = auxiliar.retornaGrupoComSabado(turma.getHorario(), h);
                     String[] dias = auxiliar.retornaArrayDias(grupo);
                     for (int m = 0; m < dias.length; m++) {
                        List<Long> idsHorarios = new ArrayList<Long>(0);
                        String sql =
                           "select DISTINCT s from Sala s WHERE s.utilizarNaDistribuicao = true AND s.idTipo = " + turma.getIdTipo()
                              + " AND s.capacidade >= " + turma.getCapacidade() + " AND s.idPredio = " + turma.getIdPredio()
                              + " AND NOT EXISTS (" +
                              "select ds from DisponibilidadeSala ds WHERE ds.idSala = s.id AND ds.idPeriodo = " + turma.getIdPeriodo() +
                              " AND ds.idHorarioSala IN (";
                        String turno = auxiliar.retornaTurno(grupo);
                        String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);
                        String subGrupo = dias[m] + turno;

                        for (int k = 0; k < arrayHorarios.length; k++) {
                           idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
                           subGrupo += arrayHorarios[k];
                        }
                        Periodo periodo = this.periodoDao.get(turma.getIdPeriodo());
                        List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(periodo.getId());
                        List<Date> datasParaReserva =
                           ManipuladorDatas.retornaDatasLetivasPorDias(
                              DateUtils.datasEntre(periodo.getDataInicio(), periodo.getDataTermino()),
                              feriados, subGrupo);
                        Long[] arrayIdsHorarios = (Long[]) idsHorarios.toArray(new Long[idsHorarios.size()]);
                        for (int i = 0; i < arrayIdsHorarios.length; i++) {
                           if (i == arrayIdsHorarios.length - 1) {
                              sql = sql + arrayIdsHorarios[i] + ") AND ds.dataReserva IN (";
                           }
                           else {
                              sql = sql + arrayIdsHorarios[i] + ",";
                           }
                        }
                        Date[] arrayDatasReserva = (Date[]) datasParaReserva.toArray(new Date[datasParaReserva.size()]);
                        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

                        for (int i = 0; i < arrayDatasReserva.length; i++) {
                           if (i == arrayDatasReserva.length - 1) {
                              sql = sql + "'" + formatter.format(arrayDatasReserva[i]) + "'" + "))";
                           }
                           else {
                              sql = sql + "'" + formatter.format(arrayDatasReserva[i]) + "'" + ",";
                           }
                        }

                     System.out.println("#### SQL(1): " + sql);
                        for (Sala sala : this.salaDao.getSalasDisponiveisPorTurma3(turma, sql)) {
                           DadosConsultarSalasDisponiveis dado = new DadosConsultarSalasDisponiveis();
                           dado.setCapacidade(sala.getCapacidade());
                           dado.setId(sala.getId());
                           dado.setNome(sala.getNome());
                           dado.setTipoQuadro(sala.getTipoQuadro());
                           dado.setHorario(subGrupo);

                           Predio predio = this.predioDao.get(sala.getIdPredio());
                           dado.setPredio(predio.getNome());

                           PerfilSalaTurma perfil = this.perfilSalaTurmaDao.get(sala.getIdTipo());

                           dado.setPerfilSala(perfil.getNome());
                           dados.add(dado);
                        }

                     }
                  }
               // }
            } else {
               // turma = this.turmaDao.get(solicitacao.getIdTurma());
               // Auxiliar auxiliar = new Auxiliar();
               String sql =
                  "select DISTINCT s from Sala s WHERE s.utilizarNaDistribuicao = true AND s.idTipo = " + turma.getIdTipo()
                     + " AND s.capacidade >= " + turma.getCapacidade() + " AND s.idPredio = " + turma.getIdPredio() + " AND NOT EXISTS (" +
                     "select ds from DisponibilidadeSala ds WHERE ds.idSala = s.id AND ds.idPeriodo = " + turma.getIdPeriodo() +
                     " AND ds.idHorarioSala IN (";
               // if (auxiliar.validarHorarioComSabado(turma.getHorario())) {
                  int controlador = auxiliar.contadorDeGruposComSabado(turma.getHorario());
                  for (int h = 0; h < auxiliar.contadorDeGruposComSabado(turma.getHorario()); h++) {
                     List<Long> idsHorarios = new ArrayList<Long>(0);
                     String grupo = auxiliar.retornaGrupoComSabado(turma.getHorario(), h);
                     String turno = auxiliar.retornaTurno(grupo);
                     String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);
                     for (int k = 0; k < arrayHorarios.length; k++) {
                        idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
                     }
                     Periodo periodo = this.periodoDao.get(turma.getIdPeriodo());
                     List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(periodo.getId());
                     List<Date> datasParaReserva =
                        ManipuladorDatas.retornaDatasLetivasPorDias(
                           DateUtils.datasEntre(periodo.getDataInicio(), periodo.getDataTermino()),
                           feriados, grupo);
                     Long[] arrayIdsHorarios = (Long[]) idsHorarios.toArray(new Long[idsHorarios.size()]);
                     for (int i = 0; i < arrayIdsHorarios.length; i++) {
                        if (i == arrayIdsHorarios.length - 1) {
                           sql = sql + arrayIdsHorarios[i] + ") AND ds.dataReserva IN (";
                        }
                        else {
                           sql = sql + arrayIdsHorarios[i] + ",";
                        }
                     }
                     Date[] arrayDatasReserva = (Date[]) datasParaReserva.toArray(new Date[datasParaReserva.size()]);
                     DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

                     for (int i = 0; i < arrayDatasReserva.length; i++) {
                        if (i == arrayDatasReserva.length - 1) {
                           sql = sql + "'" + formatter.format(arrayDatasReserva[i]) + "'" + "))";
                        }
                        else {
                           sql = sql + "'" + formatter.format(arrayDatasReserva[i]) + "'" + ",";
                        }
                     }

                     if (h != controlador - 1) {
                        sql = sql + " AND NOT EXISTS (" +
                           "select ds from DisponibilidadeSala ds WHERE ds.idSala = s.id AND ds.idPeriodo = " + turma.getIdPeriodo() +
                           " AND ds.idHorarioSala IN (";
                     }
                  }
               // }

               System.out.println("#### SQL(2): " + sql);
               for (Sala sala : this.salaDao.getSalasDisponiveisPorTurma3(turma, sql)) {
                  DadosConsultarSalasDisponiveis dado = new DadosConsultarSalasDisponiveis();
                  dado.setCapacidade(sala.getCapacidade());
                  dado.setId(sala.getId());
                  dado.setNome(sala.getNome());
                  dado.setTipoQuadro(sala.getTipoQuadro());
                  dado.setHorario(turma.getHorario());

                  Predio predio = this.predioDao.get(sala.getIdPredio());
                  dado.setPredio(predio.getNome());

                  PerfilSalaTurma perfil = this.perfilSalaTurmaDao.get(sala.getIdTipo());

                  dado.setPerfilSala(perfil.getNome());
                  dados.add(dado);
               }
            }
         }
      }

      Integer totalNumeroLinhas = dados.size();

      return new RespostaConsultarSalasDisponiveis(dados, totalNumeroLinhas);
   }

   @Override
   public void valida(ConsultarSalasDisponiveis solicitacao) throws NegocioException {

   }

   public void setSalaDao(SalaDao salaDao) {
      this.salaDao = salaDao;
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setPerfilSalaTurmaDao(PerfilSalaTurmaDao perfilSalaTurmaDao) {
      this.perfilSalaTurmaDao = perfilSalaTurmaDao;
   }

   public void setPredioDao(PredioDao predioDao) {
      this.predioDao = predioDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

}
