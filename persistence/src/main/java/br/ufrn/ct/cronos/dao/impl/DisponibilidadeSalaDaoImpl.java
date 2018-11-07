package br.ufrn.ct.cronos.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.Query;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.DisponibilidadeSala;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import dev.home.componente.dao.hibernate.impl.AbstractDao;
import dev.home.componente.utils.date.DateUtils;

public class DisponibilidadeSalaDaoImpl extends AbstractDao<Long, DisponibilidadeSala> implements DisponibilidadeSalaDao {

   private HorarioDao horarioDao;
   private PeriodoDao periodoDao;
   private FeriadoDao feriadoDao;

   public DisponibilidadeSalaDaoImpl() {
      super();
   }

   @Override
   public boolean verificarDisponibilidadeSala(Turma turma, Long idSala) {
      Auxiliar auxiliar = new Auxiliar();

      String sql =
         "select DISTINCT s from Sala s WHERE s.id = :idSala AND s.utilizarNaDistribuicao = true AND s.idTipo = :idTipo AND s.capacidade >= :capacidade AND s.idPredio = :idPredio AND NOT EXISTS (select ds from DisponibilidadeSala ds WHERE ds.idSala = :idSala AND ds.idPeriodo = :idPeriodo AND ds.idHorarioSala IN (";

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
            ManipuladorDatas.retornaDatasLetivasPorDias(DateUtils.datasEntre(periodo.getDataInicio(), periodo.getDataTermino()),
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
            sql =
               sql
                  + " AND NOT EXISTS (select ds from DisponibilidadeSala ds WHERE ds.idSala = :idSala AND ds.idPeriodo = :idPeriodo AND ds.idHorarioSala IN (";
         }
      }

      Query query = getSession().createQuery(sql);
      query.setLong("idTipo", turma.getIdTipo());
      query.setInteger("capacidade", turma.getCapacidade());
      query.setLong("idPredio", turma.getIdPredio());
      query.setLong("idPeriodo", turma.getIdPeriodo());
      query.setLong("idSala", idSala);
      Sala sala = (Sala) query.uniqueResult();
      if (sala != null) {
         return true;
      }
      return false;
   }

   @Override
   public boolean verificarTurmaTemSala(Long idTurma) {
      String sql = "select count(d) from DisponibilidadeSala d WHERE d.idTurma = :idTurma";
      Query query = getSession().createQuery(sql);
      query.setLong("idTurma", idTurma);
      Long contador = (Long) query.uniqueResult();

      if (contador > 0) {
         return true;
      }
      return false;
   }

   @Override
   public Sala getSalaPorTurma(Long idTurma) {
      Sala retorno = null;
      String sql = "select DISTINCT s from Sala s, DisponibilidadeSala ds WHERE ds.idSala = s.id AND ds.idTurma = :idTurma";
      Query query = getSession().createQuery(sql);
      query.setLong("idTurma", idTurma);
      retorno = (Sala) query.uniqueResult();
      return retorno;
   }

   @Override
   public List<Sala> getSalasPorTurma(Long idTurma) {
      List<Sala> retorno = new ArrayList<Sala>(0);
      String sql =
         "select DISTINCT s from Sala s, DisponibilidadeSala ds WHERE ds.idSala = s.id AND ds.idTurma = :idTurma ORDER BY DAYOFWEEK(ds.dataReserva)";
      Query query = getSession().createQuery(sql);
      query.setLong("idTurma", idTurma);
      retorno = query.list();
      return retorno;
   }

   @Override
   public Sala getSalaDisponivelPorTurma(Turma turma) {
      Sala retorno = null;
      Auxiliar auxiliar = new Auxiliar();

      String sql =
         "select DISTINCT s from Sala s WHERE s.utilizarNaDistribuicao = true AND s.idTipo = :idTipo AND s.capacidade >= :capacidade AND s.idPredio = :idPredio AND NOT EXISTS (select ds from DisponibilidadeSala ds WHERE ds.idSala = s.id AND ds.idPeriodo = :idPeriodo AND ds.idHorarioSala IN (";
      
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
            ManipuladorDatas.retornaDatasLetivasPorDias(DateUtils.datasEntre(periodo.getDataInicio(), periodo.getDataTermino()),
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
            sql =
               sql
                  + " AND NOT EXISTS (select ds from DisponibilidadeSala ds WHERE ds.idSala = s.id AND ds.idPeriodo = :idPeriodo AND ds.idHorarioSala IN (";
         }
      }
      
      Query query = getSession().createQuery(sql);
      query.setLong("idTipo", turma.getIdTipo());
      query.setInteger("capacidade", turma.getCapacidade());
      query.setLong("idPredio", turma.getIdPredio());
      query.setLong("idPeriodo", turma.getIdPeriodo());
      query.setFirstResult(0);
      query.setMaxResults(1);
      retorno = (Sala) query.uniqueResult();
      return retorno;
   }

   @Override
   public List<DisponibilidadeSala> getDisponibilidadesSalaPorTurma(Turma turma) {
      List<DisponibilidadeSala> retorno = new ArrayList<DisponibilidadeSala>(0);
      String sql =
         "select ds from DisponibilidadeSala ds WHERE ds.idTurma = :id AND ds.idPeriodo = :idPeriodo";

      Query query = getSession().createQuery(sql);
      query.setLong("id", turma.getId());
      query.setLong("idPeriodo", turma.getIdPeriodo());
      retorno = query.list();

      return retorno;
   }

   @Override
   public List<DisponibilidadeSala> getDisponibilidadesSalaPorSalaTurnoEPeriodo(Long idSala, String turno, Long idPeriodo) {
      List<DisponibilidadeSala> retorno = new ArrayList<DisponibilidadeSala>(0);
      String sql =
         "select ds from DisponibilidadeSala ds WHERE ds.idSala = :idSala AND ds.idPeriodo = :idPeriodo AND ds.idAgendamento IS NUll"
            + " AND ds.idHorarioSala IN (select hs.id from Horario hs WHERE hs.turno = :turno) GROUP BY ds.idHorarioSala, DAYOFWEEK(ds.dataReserva)";

      Query query = getSession().createQuery(sql);
      query.setLong("idSala", idSala);
      query.setString("turno", turno);
      query.setLong("idPeriodo", idPeriodo);
      retorno = query.list();

      return retorno;
   }

   @Override
   public List<DisponibilidadeSala> getAgendamentosPorSalaTurnoEPeriodo(Long idSala, String turno, Long idPeriodo) {
      List<DisponibilidadeSala> retorno = new ArrayList<DisponibilidadeSala>(0);
      Date hoje = new Date();
      GregorianCalendar data = new GregorianCalendar();
      data.setTime(hoje);
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
      String sql =
         "select ds from DisponibilidadeSala ds WHERE ds.idSala = :idSala AND ds.idPeriodo = :idPeriodo AND ds.dataReserva >= :stringDataHoje AND ds.idTurma IS NUll"
            + " AND ds.idHorarioSala IN (select hs.id from Horario hs WHERE hs.turno = :turno)";

      Query query = getSession().createQuery(sql);
      query.setLong("idSala", idSala);
      query.setString("turno", turno);
      query.setLong("idPeriodo", idPeriodo);
      query.setString("stringDataHoje", formatter.format(hoje));
      retorno = query.list();

      return retorno;
   }

   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

   @Override
   public boolean verificarDisponibilidadeSalaParaAgendamentos(String horario, Long idSala, List<Date> datas, Long idPeriodo) {
      Auxiliar auxiliar = new Auxiliar();

      String sql =
         "select count(d) from DisponibilidadeSala d WHERE d.idSala = :idSala AND d.idPeriodo = :idPeriodo AND (( d.idHorarioSala IN (";
      if (auxiliar.validarHorarioComDomingo(horario)) {
         int controlador = auxiliar.contadorDeGruposComDomingo(horario);
         for (int h = 0; h < auxiliar.contadorDeGruposComDomingo(horario); h++) {
            List<Long> idsHorarios = new ArrayList<Long>(0);
            String grupo = auxiliar.retornaGrupoComDomingo(horario, h);
            String turno = auxiliar.retornaTurno(grupo);
            String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

            for (int k = 0; k < arrayHorarios.length; k++) {
               idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
            }

            Long[] arrayIdsHorarios = (Long[]) idsHorarios.toArray(new Long[idsHorarios.size()]);
            for (int i = 0; i < arrayIdsHorarios.length; i++) {
               if (i == arrayIdsHorarios.length - 1) {
                  sql = sql + arrayIdsHorarios[i] + ") AND d.dataReserva IN (";
               }
               else {
                  sql = sql + arrayIdsHorarios[i] + ",";
               }
            }
            Date[] arrayDatasReserva = (Date[]) datas.toArray(new Date[datas.size()]);
            DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

            for (int i = 0; i < arrayDatasReserva.length; i++) {
               if (i == arrayDatasReserva.length - 1) {
                  if (h == controlador - 1) {
                     sql = sql + "'" + formatter.format(arrayDatasReserva[i]) + "'" + ")))";
                  } else {
                     sql = sql + "'" + formatter.format(arrayDatasReserva[i]) + "'" + "))";
                  }
               }
               else {
                  sql = sql + "'" + formatter.format(arrayDatasReserva[i]) + "'" + ",";
               }
            }

            if (h != controlador - 1) {
               sql = sql + " OR ( d.idHorarioSala IN (";
            }
         }
      }

      Query query = getSession().createQuery(sql);
      query.setLong("idPeriodo", idPeriodo);
      query.setLong("idSala", idSala);
      Long contador = (Long) query.uniqueResult();
      if (contador > 0) {
         return false;
      }
      return true;
   }

   @Override
   public void removerDisponibilidadesSalaPorAgendamento(Long idAgendamento) {
      String sql = "delete from DisponibilidadeSala d WHERE d.idAgendamento = :idAgendamento";
      Query query = getSession().createQuery(sql);
      query.setLong("idAgendamento", idAgendamento);
      query.executeUpdate();
   }

   @Override
   public void removerAgendamentosAnteriores() {
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
      String sqlFinal =
         "delete from DisponibilidadeSala WHERE dataReserva <= '" + formatter.format(new Date()) + "'" + " AND idTurma IS NUll";
      getSession().createQuery(sqlFinal).executeUpdate();
   }

   @Override
   public Integer contadorOcupacaoHorarioPorDiaDaSemana(Integer diaDaSemana, Long idHorarioSala, Long idPredio, Long idPeriodo) {
      String sql =
         "select ds from DisponibilidadeSala ds WHERE ds.idHorarioSala = :idHorarioSala AND DAYOFWEEK(ds.dataReserva) = :diaDaSemana AND "
            + " ds.idPeriodo = :idPeriodo AND ds.idTurma IS NOT NULL AND ds.idSala IN (select s.id from Sala s WHERE s.idPredio = :idPredio)"
            + " GROUP BY ds.idSala";
      Query query = getSession().createQuery(sql);
      query.setLong("idHorarioSala", idHorarioSala);
      query.setLong("diaDaSemana", diaDaSemana);
      query.setLong("idPeriodo", idPeriodo);
      query.setLong("idPredio", idPredio);
      Integer contador = (Integer) query.list().size();

      return contador;
   }

   @Override
   public Integer contadorOcupacaoHorarioPorDiaDaSemana(Integer diaDaSemana, Long idHorarioSala, Long idPredio, Long idPeriodo,
      List<Long> idsSalasForaDaEstatistica) {
      String sql =
         "select ds from DisponibilidadeSala ds WHERE ds.idHorarioSala = :idHorarioSala AND DAYOFWEEK(ds.dataReserva) = :diaDaSemana AND "
            + " ds.idPeriodo = :idPeriodo AND ds.idTurma IS NOT NULL AND ds.idSala IN (select s.id from Sala s WHERE s.idPredio = :idPredio "
            + " AND s.id NOT IN (";
      Long[] arraysIdsSalasForaDaEstatistica = (Long[]) idsSalasForaDaEstatistica.toArray(new Long[idsSalasForaDaEstatistica.size()]);
      for (int i = 0; i < arraysIdsSalasForaDaEstatistica.length; i++) {
         if (i == arraysIdsSalasForaDaEstatistica.length - 1) {
            sql = sql + arraysIdsSalasForaDaEstatistica[i] + ")";
         } else {
            sql = sql + arraysIdsSalasForaDaEstatistica[i] + ",";
         }
      }
      sql = sql + ") GROUP BY ds.idSala";

      Query query = getSession().createQuery(sql);
      query.setLong("idHorarioSala", idHorarioSala);
      query.setLong("diaDaSemana", diaDaSemana);
      query.setLong("idPeriodo", idPeriodo);
      query.setLong("idPredio", idPredio);
      Integer contador = (Integer) query.list().size();

      return contador;
   }

   @Override
   public Sala getSalaPorAgendamento(Long idAgendamento) {
      Sala retorno = null;
      String sql = "select DISTINCT s from Sala s, DisponibilidadeSala ds WHERE ds.idSala = s.id AND ds.idAgendamento = :idAgendamento";
      Query query = getSession().createQuery(sql);
      query.setLong("idAgendamento", idAgendamento);
      retorno = (Sala) query.uniqueResult();

      return retorno;
   }

   @Override
   public List<Date> getDatasReservaPorAgendamento(Long idAgendamento) {
      List<Date> retorno = new ArrayList<Date>(0);
      String sql = "select ds.dataReserva from DisponibilidadeSala ds WHERE ds.idAgendamento = :idAgendamento GROUP BY ds.dataReserva";
      Query query = getSession().createQuery(sql);
      query.setLong("idAgendamento", idAgendamento);
      retorno = query.list();

      return retorno;
   }

   @Override
   public List<DisponibilidadeSala> getHorariosPorDataReservaAgendamento(Long idAgendamento, Date data) {
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
      List<DisponibilidadeSala> retorno = new ArrayList<DisponibilidadeSala>(0);
      String sql = "select ds from DisponibilidadeSala ds WHERE ds.idAgendamento = :idAgendamento AND ds.dataReserva = :data";
      Query query = getSession().createQuery(sql);
      query.setLong("idAgendamento", idAgendamento);
      query.setString("data", formatter.format(data));
      retorno = query.list();

      return retorno;
   }

   @Override
   public List<DisponibilidadeSala> getDisponibilidadesSalaPorTurmaHorariosEDiaDaSemana(Turma turma, List<Long> idsHorarios,
      int diaDaSemana) {
      List<DisponibilidadeSala> retorno = new ArrayList<DisponibilidadeSala>(0);
      String sql = "select ds FROM DisponibilidadeSala ds WHERE ds.idTurma = :idTurma AND ds.idPeriodo = :idPeriodo" +
         " AND ds.idHorarioSala IN (";
      Long[] arrayIdsHorarios = (Long[]) idsHorarios.toArray(new Long[idsHorarios.size()]);
      for (int i = 0; i < arrayIdsHorarios.length; i++) {
         if (i == arrayIdsHorarios.length - 1) {
            sql += arrayIdsHorarios[i] + ") ";
         } else {
            sql += arrayIdsHorarios[i] + ",";
         }
      }
      sql += "AND DAYOFWEEK(ds.dataReserva) = :diaDaSemana";
      Query query = getSession().createQuery(sql);
      query.setLong("idTurma", turma.getId());
      query.setLong("idPeriodo", turma.getIdPeriodo());
      query.setInteger("diaDaSemana", diaDaSemana);
      retorno = query.list();
      return retorno;
   }

   @Override
   public List<DisponibilidadeSala> getDisponibilidadesSalaPorTurmaESala(Turma turma, Sala sala) {
      List<DisponibilidadeSala> retorno = new ArrayList<DisponibilidadeSala>(0);
      String sql = "select ds FROM DisponibilidadeSala ds WHERE ds.idTurma = :idTurma AND ds.idSala = :idSala AND " +
         "ds.idPeriodo = :idPeriodo GROUP BY ds.idSala, DAYOFWEEK(ds.dataReserva), ds.idHorarioSala " +
         "ORDER BY ds.idHorarioSala";
      Query query = getSession().createQuery(sql);
      query.setLong("idTurma", turma.getId());
      query.setLong("idSala", sala.getId());
      query.setLong("idPeriodo", turma.getIdPeriodo());
      retorno = query.list();
      return retorno;
   }

   @Override
   public List<String> getHorariosPorTurmaSalaEDiaDaSemana(Turma turma, Sala sala, int diaDaSemana, String turno) {
      List<String> retorno = new ArrayList<String>(0);
      String sql =
         "select DAYOFWEEK(ds.dataReserva), h.turno, h.horario FROM Horario h, DisponibilidadeSala ds WHERE h.id = ds.idHorarioSala "
            +
            "AND ds.idTurma = :idTurma AND DAYOFWEEK(ds.dataReserva) = :diaDaSemana AND h.turno = :turno AND ds.idSala = :idSala AND ds.idPeriodo = :idPeriodo "
            +
            "GROUP BY DAYOFWEEK(ds.dataReserva), ds.idHorarioSala ORDER BY DAYOFWEEK(ds.dataReserva), ds.idHorarioSala";
      Query query = getSession().createQuery(sql);
      query.setLong("idTurma", turma.getId());
      query.setInteger("diaDaSemana", diaDaSemana);
      query.setString("turno", turno);
      query.setLong("idSala", sala.getId());
      query.setLong("idPeriodo", turma.getIdPeriodo());
      List<Object[]> objetos = query.list();
      for (Object[] objects : objetos) {
         String dia = String.valueOf(objects[0]);
         String t = String.valueOf(objects[1]);
         String horario = String.valueOf(objects[2]);
         retorno.add(dia + t + horario);
      }
      return retorno;
   }

   @Override
   public List<String> getHorariosPorTurmaESala(Turma turma, Sala sala, String turno, List<Long> listaIdsHorarios,
      List<String> listaStringsDias) {
      List<String> retorno = new ArrayList<String>(0);
      Long[] arrayIdsHorarios = (Long[]) listaIdsHorarios.toArray(new Long[listaIdsHorarios.size()]);

      // Mudanca feita em 25/07
      String[] arrayDias = (String[]) listaStringsDias.toArray(new String[listaStringsDias.size()]);
      // Fim da Mudanca aqui

      String stringIdsHorarios = "";
      for (int i = 0; i < arrayIdsHorarios.length; i++) {
         if (i == arrayIdsHorarios.length - 1) {
            stringIdsHorarios += arrayIdsHorarios[i];
         } else {
            stringIdsHorarios += arrayIdsHorarios[i] + ",";
         }
      }

      // Mudanca feita em 25/07
      String stringDias = "";
      for (int i = 0; i < arrayDias.length; i++) {
         if (i == arrayDias.length - 1) {
            stringDias += arrayDias[i];
         } else {
            stringDias += arrayDias[i] + ",";
         }
      }
      // Fim da Mudanca aqui em 25/07

      String sql =
         "select DAYOFWEEK(ds.dataReserva), h.turno, h.horario FROM Horario h, DisponibilidadeSala ds WHERE h.id = ds.idHorarioSala "
            +
            "AND ds.idTurma = :idTurma AND h.turno = :turno AND ds.idSala = :idSala AND ds.idPeriodo = :idPeriodo "
            + " AND ds.idHorarioSala IN (" + stringIdsHorarios + ")" +
            " AND DAYOFWEEK(ds.dataReserva) IN (" + stringDias + ")"
            +
            "GROUP BY DAYOFWEEK(ds.dataReserva), ds.idHorarioSala ORDER BY DAYOFWEEK(ds.dataReserva), ds.idHorarioSala";

      Query query = getSession().createQuery(sql);
      query.setLong("idTurma", turma.getId());
      query.setString("turno", turno);
      query.setLong("idSala", sala.getId());
      query.setLong("idPeriodo", turma.getIdPeriodo());
      List<Object[]> objetos = query.list();
      for (Object[] objects : objetos) {
         String dia = String.valueOf(objects[0]);
         String t = String.valueOf(objects[1]);
         String horario = String.valueOf(objects[2]);
         retorno.add(dia + t + horario);
      }
      return retorno;
   }

   @Override
   public List<DisponibilidadeSala> getDisponibilidadesSalaPorIdSala(Long idSala) {
      return null;
   }

}
