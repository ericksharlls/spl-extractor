package br.ufrn.ct.cronos.dao.impl;
 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import br.ufrn.ct.cronos.dao.AgendamentoDao;
import br.ufrn.ct.cronos.entity.Agendamento;
import br.ufrn.ct.cronos.entity.Sala;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class AgendamentoDaoImpl extends AbstractDao<Long, Agendamento> implements AgendamentoDao {
   
      public AgendamentoDaoImpl() {
         super();
      }
      
      private String retornaSql2(String[] arrayDias, String turno, String[] arrayHorarios, Date dataAgendamento, Long idSala){
         DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
         
         String sqlTemp = " ( ";
         for (int i = 0; i < arrayDias.length; i++) {
            for (int j = 0; j < arrayHorarios.length; j++) {
               sqlTemp = sqlTemp + " EXISTS(select ag from Agendamento ag WHERE a.dia = " + arrayDias[i];
               sqlTemp = sqlTemp + " AND a.turno = '" + turno + "'";
               sqlTemp = sqlTemp + " AND a.horario = " + arrayHorarios[j];
               
               sqlTemp = sqlTemp + " AND a.data = '" + formatter.format(dataAgendamento) + "'";
               
               sqlTemp = sqlTemp + " AND a.idSala = " + idSala + ")";
               
               if ((i != arrayDias.length - 1) || (j != arrayHorarios.length - 1)) {
                  sqlTemp = sqlTemp + " OR ";
               }
            }
         }

         sqlTemp = sqlTemp + " ) ";
         return sqlTemp;
      }
      
      @Override
      public List<Agendamento> getAgendamentos(Long idsala, String turno) {
         String sqlFinal = "select a from Agendamento a WHERE a.idSala = " + idsala + " AND a.turno = " + "'" + turno + "'";
         List<Agendamento> agendamentos = new ArrayList<Agendamento>();
         
         Query query = getSession().createQuery(sqlFinal);
         agendamentos = query.list();
         
         return agendamentos;
      }
 
      @Override
      public List<Sala> getSalasDisponiveisPorHorarioEData(String horario, List<Date> datasParaAgendamento) {
         String sqlFinal = "select DISTINCT s from Sala s WHERE s.utilizarNoAgendamento = :utilizarNoAgendamento AND s.idPredio = :idPredio AND ";
         List<Sala> salas = new ArrayList<Sala>();
         Auxiliar auxiliar = new Auxiliar();

      for (int h = 0; h < auxiliar.contadorDeGruposComSabado(horario); h++) {
         String grupo = auxiliar.retornaGrupoComSabado(horario, h);
            String[] arrayDias = auxiliar.retornaArrayDias(grupo);
            String turno = auxiliar.retornaTurno(grupo);
            String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);
            if (h > 0) {
               sqlFinal = sqlFinal + " AND ";
            }
            sqlFinal = sqlFinal + retornaSql(arrayDias, turno, arrayHorarios, datasParaAgendamento);
         }
         Query query = getSession().createQuery(sqlFinal);
         query.setBoolean("utilizarNoAgendamento", true);
         query.setLong("idPredio", new Long(1));
         salas = query.list();
         
         return salas;
      }
      
      private String retornaSql(String[] arrayDias, String turno, String[] arrayHorarios, List<Date> datasParaAgendamento) {
         DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
         String sqlTemp = "";
         for (int i = 0; i < arrayDias.length; i++) {
            for (int j = 0; j < arrayHorarios.length; j++) {
               sqlTemp = sqlTemp + " NOT EXISTS(select dt from Distribuicao dt WHERE dt.dia = " + arrayDias[i];
               sqlTemp = sqlTemp + " AND dt.turno = '" + turno + "'";
               sqlTemp = sqlTemp + " AND dt.horario = " + arrayHorarios[j];
               sqlTemp = sqlTemp + " AND dt.idSala = s.id)";
               
               sqlTemp = sqlTemp + " AND NOT EXISTS(select ag from Agendamento ag WHERE ag.dia = " + arrayDias[i];
               sqlTemp = sqlTemp + " AND ag.turno = '" + turno + "'";
               sqlTemp = sqlTemp + " AND ag.horario = " + arrayHorarios[j];
               sqlTemp = sqlTemp + " AND ag.idSala = s.id";
               sqlTemp = sqlTemp + " AND ag.data = '" + formatter.format(datasParaAgendamento.get(i)) + "')";
               
               if ((i != arrayDias.length - 1) || (j != arrayHorarios.length - 1)) {
                  sqlTemp = sqlTemp + " AND ";
               }
            }
            
         }
         
         return sqlTemp;
      }
 
      @Override
      public boolean verificarDisponibilidadeSala(Integer dia, String turno, Integer horario, Date dataAgendamento, Long idSala){
         DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
         String sqlFinal = "select ag from Agendamento ag WHERE ag.turno = '" + turno + "' AND ag.dia = " + dia + " AND ag.horario = " + horario + 
                  " AND ag.idSala = " + idSala + " AND ag.data = '" + formatter.format(dataAgendamento) + "'";
         
         Query query = getSession().createQuery(sqlFinal);
         if (query.list().size() > 0) {
            return false;
         }
         
         return true;
      }
      
      @Override
      public void removerAgendamentosAnteriores(){
         DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
         String sqlFinal = "delete from Agendamento WHERE data <= '" + formatter.format(new Date()) + "'";
         getSession().createQuery(sqlFinal).executeUpdate();
      }
      
      @Override
      public void apagarAgendamentosPorInteressado(Agendamento agendamento){
         String sql = "delete from Agendamento ag WHERE ag.interessado = :interessado AND ag.turno = :turno AND ag.data = :data AND ag.idSala = :idSala";
         Query query = getSession().createQuery(sql);
         
      // query.setString("interessado", agendamento.getInteressado());
      // query.setString("turno", agendamento.getTurno());
      // query.setString("data", agendamento.getDataFormatoAmericano());
      // query.setParameter("idSala", agendamento.getIdSala());
         
         query.executeUpdate();
      }
      
      
      public List<Agendamento> getAgendamentosPorInteressado(String interessado) {
         List<Agendamento> retorno = new ArrayList<Agendamento>();
         
         return retorno;
      }
      
      @Override
      public List<Sala> getSalasDisponiveisPorHorarioEData(String horario, String horarioOpcional, List<Date> datasParaAgendamento){
         String sqlFinal = "select DISTINCT s from Sala s WHERE s.utilizarNoAgendamento = :utilizarNoAgendamento AND s.idPredio = :idPredio AND ";
         List<Sala> salas = new ArrayList<Sala>();
         Auxiliar auxiliar = new Auxiliar();
         
      for (int h = 0; h < auxiliar.contadorDeGruposComSabado(horario); h++) {
         String grupo = auxiliar.retornaGrupoComSabado(horario, h);
            String[] arrayDias = auxiliar.retornaArrayDias(grupo);
            String turno = auxiliar.retornaTurno(grupo);
            String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);
            
         String grupoOpcional = auxiliar.retornaGrupoComSabado(horarioOpcional, h);
            String[] arrayDiasOpcional = auxiliar.retornaArrayDias(grupoOpcional);
            String turnoOpcional = auxiliar.retornaTurno(grupoOpcional);
            String[] arrayHorariosOpcional = auxiliar.retornaArrayHorarios(grupoOpcional);
            
            if (h > 0) {
               sqlFinal = sqlFinal + " AND ";
            }
            sqlFinal = sqlFinal + retornaSqlNovo(arrayDias, turno, arrayHorarios, datasParaAgendamento, arrayDiasOpcional, turnoOpcional, arrayHorariosOpcional);
         }
         Query query = getSession().createQuery(sqlFinal);
         query.setBoolean("utilizarNoAgendamento", true);
         query.setLong("idPredio", new Long(1));
         salas = query.list();
         
         return salas;
      }
      
      private String retornaSqlNovo(String[] arrayDias, String turno, String[] arrayHorarios, List<Date> datasParaAgendamento, 
               String[] arrayDiasOpcional, String turnoOpcional, String[] arrayHorariosOpcional){
         DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
         String sqlTemp = "";
         for (int i = 0; i < arrayDias.length; i++) {
            for (int j = 0; j < arrayHorarios.length; j++) {
               sqlTemp = sqlTemp + " NOT EXISTS(select dt from Distribuicao dt WHERE dt.dia = " + arrayDias[i];
               sqlTemp = sqlTemp + " AND dt.turno = '" + turno + "'";
               sqlTemp = sqlTemp + " AND dt.horario = " + arrayHorarios[j];
               sqlTemp = sqlTemp + " AND dt.idSala = s.id)";
               
               sqlTemp = sqlTemp + " AND NOT EXISTS(select ag from Agendamento ag WHERE ag.dia = " + arrayDias[i];
               sqlTemp = sqlTemp + " AND ag.turno = '" + turno + "'";
               sqlTemp = sqlTemp + " AND ag.horario = " + arrayHorarios[j];
               sqlTemp = sqlTemp + " AND ag.idSala = s.id";
               sqlTemp = sqlTemp + " AND ag.data = '" + formatter.format(datasParaAgendamento.get(i)) + "')";
               
               sqlTemp = sqlTemp + " AND ";
            }
         }
         
         for (int i = 0; i < arrayDiasOpcional.length; i++) {
            for (int j = 0; j < arrayHorariosOpcional.length; j++) {
               sqlTemp = sqlTemp + " NOT EXISTS(select dt from Distribuicao dt WHERE dt.dia = " + arrayDiasOpcional[i];
               sqlTemp = sqlTemp + " AND dt.turno = '" + turnoOpcional + "'";
               sqlTemp = sqlTemp + " AND dt.horario = " + arrayHorariosOpcional[j];
               sqlTemp = sqlTemp + " AND dt.idSala = s.id)";
               
               sqlTemp = sqlTemp + " AND NOT EXISTS(select ag from Agendamento ag WHERE ag.dia = " + arrayDiasOpcional[i];
               sqlTemp = sqlTemp + " AND ag.turno = '" + turnoOpcional + "'";
               sqlTemp = sqlTemp + " AND ag.horario = " + arrayHorariosOpcional[j];
               sqlTemp = sqlTemp + " AND ag.idSala = s.id";
               sqlTemp = sqlTemp + " AND ag.data = '" + formatter.format(datasParaAgendamento.get(i)) + "')";
               
               if ((i != arrayDiasOpcional.length - 1) || (j != arrayHorariosOpcional.length - 1)) {
                  sqlTemp = sqlTemp + " AND ";
               }
            }
         }
         
         return sqlTemp;
      }

   @Override
   public List<Sala> getSalasDisponiveisPorHorarioEData(Long idPredio, Long idPeriodo, List<Long> idsHorarios,
      List<Date> datasParaAgendamento) {
      List<Sala> retorno = new ArrayList<Sala>(0);
      String sql =
         "select DISTINCT s from Sala s WHERE s.utilizarNoAgendamento = true AND s.idPredio = :idPredio AND NOT EXISTS ("
            + "select ds from DisponibilidadeSala ds WHERE ds.idSala = s.id AND ds.idPeriodo = :idPeriodo" + " AND ds.idHorarioSala IN (";

      Long[] arrayIdsHorarios = (Long[]) idsHorarios.toArray(new Long[idsHorarios.size()]);
      /*
       * for (int i = 0; i < arrayIdsHorarios.length; i++) { if (i == arrayIdsHorarios.length - 1) { sql = sql + arrayIdsHorarios[i] +
       * ") AND NOT EXISTS(select ag from Agendamento ag WHERE ag.idSala = s.id AND ag.idPeriodo = :idPeriodo" +
       * " AND ag.idHorarioSala IN ("; } else { sql = sql + arrayIdsHorarios[i] + ","; } }
       */

      for (int i = 0; i < arrayIdsHorarios.length; i++) {
         if (i == arrayIdsHorarios.length - 1) {
            sql = sql + arrayIdsHorarios[i] + ") AND ds.dataReserva IN (";
         }
         else {
            sql = sql + arrayIdsHorarios[i] + ",";
         }
      }

      Date[] arrayDatasReserva = (Date[]) datasParaAgendamento.toArray(new Date[datasParaAgendamento.size()]);
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

      for (int i = 0; i < arrayDatasReserva.length; i++) {
         if (i == arrayDatasReserva.length - 1) {
            sql = sql + "'" + formatter.format(arrayDatasReserva[i]) + "'" + "))";
         }
         else {
            sql = sql + "'" + formatter.format(arrayDatasReserva[i]) + "'" + ",";
         }
      }

      Query query = getSession().createQuery(sql);
      query.setLong("idPredio", idPredio);
      query.setLong("idPeriodo", idPeriodo);
      retorno = query.list();
      return retorno;
   }

   @Override
   public List<Agendamento> getAgendamentosPorPeriodoPredioInteressadoMotivoEIntervaloDatas(Integer startPage, Integer maxPage,
      Long idPeriodo,
      Long idPredio, String interessado, String motivo, Date dataInicial, Date dataFinal) {
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
      List<Agendamento> retorno = new ArrayList<Agendamento>(0);
      String sql =
         "select DISTINCT ag from Agendamento ag, DisponibilidadeSala ds WHERE ag.id = ds.idAgendamento AND ds.dataReserva BETWEEN :dataInicial AND :dataFinal AND "
            + " ds.idSala IN (select s.id from Sala s WHERE s.idPredio = :idPredio) AND ag.idPeriodo = :idPeriodo AND ag.motivo LIKE :motivo AND "
            + "ag.idFuncionario IN (select f.id from Funcionario f WHERE f.nome LIKE :interessado)";

      Query query = getSession().createQuery(sql);

      query.setString("dataInicial", formatter.format(dataInicial));
      query.setString("dataFinal", formatter.format(dataFinal));
      query.setLong("idPredio", idPredio);
      query.setLong("idPeriodo", idPeriodo);
      query.setString("motivo", "%" + motivo + "%");
      query.setString("interessado", "%" + interessado + "%");

      query.setFirstResult(startPage);
      query.setMaxResults(maxPage);

      retorno = query.list();

      return retorno;
   }

   @Override
   public Integer contadorAgendamentosPorPeriodoPredioInteressadoMotivoEIntervaloDatas(Integer startPage, Integer maxPage,
      Long idPeriodo, Long idPredio, String interessado, String motivo, Date dataInicial, Date dataFinal) {
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
      String sql =
         "select count(DISTINCT ag) from Agendamento ag, DisponibilidadeSala ds WHERE ag.id = ds.idAgendamento AND ds.dataReserva BETWEEN :dataInicial AND :dataFinal AND "
            + " ds.idSala IN (select s.id from Sala s WHERE s.idPredio = :idPredio) AND ag.idPeriodo = :idPeriodo AND ag.motivo LIKE :motivo AND "
            + "ag.idFuncionario IN (select f.id from Funcionario f WHERE f.nome LIKE :interessado)";

      Query query = getSession().createQuery(sql);

      query.setString("dataInicial", formatter.format(dataInicial));
      query.setString("dataFinal", formatter.format(dataFinal));
      query.setLong("idPredio", idPredio);
      query.setLong("idPeriodo", idPeriodo);
      query.setString("motivo", "%" + motivo + "%");
      query.setString("interessado", "%" + interessado + "%");

      Long contador = (Long) query.uniqueResult();
      Integer retorno = contador.intValue();

      return retorno;
   }

}
