package br.ufrn.ct.cronos.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class SalaDaoImpl extends AbstractDao<Long, Sala> implements SalaDao {
	
   private static final String ID_PREDIO = "idPredio";
   private static final String NOME_SALA = "nome";
   // private HorarioSalaDao horarioSalaDao;
   private PeriodoDao periodoDao;
   private FeriadoDao feriadoDao;
   private HorarioDao horarioDao;
		
   public SalaDaoImpl() {
      super();
   }
		
   @Override
   public List<Sala> getByPredio(Long idPredio, Integer startPage, Integer maxPage) {
      DetachedCriteria criteria = criarDetachedCriteria();
      criteria.add(Expression.eq(SalaDaoImpl.ID_PREDIO, idPredio));
      List<Sala> retorno = findByDetachedCriteria(criteria, startPage, maxPage);
      return retorno;
   }
		
   @Override
   public Integer countByPredio(Long idPredio) {
      DetachedCriteria criteria = criarDetachedCriteria();
      criteria.add(Expression.eq(SalaDaoImpl.ID_PREDIO, idPredio));
      List<Sala> retorno = findByDetachedCriteria(criteria);
      Integer total = retorno.size();
      return total;
   }
		
   @Override
   public List<Sala> getSalasDisponiveisPorTurma(Turma turma) {
      String sqlFinal =
         "select DISTINCT s from Sala s WHERE s.idTipo = " + turma.getIdTipo() + " AND s.capacidade >= " + turma.getCapacidade() + " AND ";
      Auxiliar auxiliar = new Auxiliar();
			
      for (int h = 0; h < auxiliar.contadorDeGrupos(turma.getHorario()); h++) {
         String grupo = auxiliar.retornaGrupo(turma.getHorario(), h);
         String[] arrayDias = auxiliar.retornaArrayDias(grupo);
         String turno = auxiliar.retornaTurno(grupo);
         String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);
         if (h > 0) {
            sqlFinal = sqlFinal + " AND ";
         }
         sqlFinal += retornaSql(arrayDias, turno, arrayHorarios);
      }
      List<Sala> salas = new ArrayList<Sala>();
			
      Query query = getSession().createQuery(sqlFinal);
      salas = query.list();
			
      return salas;
   }

		@Override
		public List<Sala> getSalasDisponiveisPorHorario(String horario){
			String sqlFinal = "select DISTINCT s from Sala s WHERE ";
			List<Sala> salas = new ArrayList<Sala>();
			Auxiliar auxiliar = new Auxiliar();
			
			for (int h = 0; h < auxiliar.contadorDeGrupos(horario); h++) {
				String grupo = auxiliar.retornaGrupo(horario, h);
				String[] arrayDias = auxiliar.retornaArrayDias(grupo);
				String turno = auxiliar.retornaTurno(grupo);
				String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);
				if (h > 0) {
					sqlFinal = sqlFinal + " AND ";
				}
				sqlFinal = sqlFinal + retornaSql(arrayDias, turno, arrayHorarios);
			}
			
			Query query = getSession().createQuery(sqlFinal);
			salas = query.list();
			
			return salas;
		}
		
		@Override
		public boolean verificarDisponibilidadeSala(String horario, Date dataAgendamento, Long idSala){
			String sqlFinal = "select DISTINCT s from Sala s WHERE ";
			Auxiliar auxiliar = new Auxiliar();
			
      for (int h = 0; h < auxiliar.contadorDeGruposComSabado(horario); h++) {
         String grupo = auxiliar.retornaGrupoComSabado(horario, h);
				String[] arrayDias = auxiliar.retornaArrayDias(grupo);
				String turno = auxiliar.retornaTurno(grupo);
				String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);
				if (h > 0) {
					sqlFinal = sqlFinal + " AND ";
				}
				sqlFinal = sqlFinal + retornaSql3(arrayDias, turno, arrayHorarios, dataAgendamento, idSala);
			}
			
			Query query = getSession().createQuery(sqlFinal);
			if (query.list().size() > 0) {
				return false;
			}
			
			return true;
		}

		private String retornaSql3(String[] arrayDias, String turno, String[] arrayHorarios, Date dataAgendamento, Long idSala) {
			DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
			String sqlTemp = "";
			for (int i = 0; i < arrayDias.length; i++) {
				for (int j = 0; j < arrayHorarios.length; j++) {
					sqlTemp = sqlTemp + " EXISTS(select dt from Distribuicao dt WHERE dt.dia = " + arrayDias[i];
					sqlTemp = sqlTemp + " AND dt.turno = '" + turno + "'";
					sqlTemp = sqlTemp + " AND dt.horario = " + arrayHorarios[j];
					sqlTemp = sqlTemp + " AND dt.idSala = " + idSala + ")";
					
					sqlTemp = sqlTemp + " AND EXISTS(select ag from Agendamento ag WHERE ag.dia = " + arrayDias[i];
					sqlTemp = sqlTemp + " AND ag.turno = '" + turno + "'";
					sqlTemp = sqlTemp + " AND ag.horario = " + arrayHorarios[j];
					sqlTemp = sqlTemp + " AND ag.idSala = " + idSala;
					sqlTemp = sqlTemp + " AND ag.data = '" + formatter.format(dataAgendamento) + "')";
					
					if ((i != arrayDias.length - 1) || (j != arrayHorarios.length - 1)) {
						sqlTemp += " AND ";
					}
				}
			}
			
			sqlTemp = sqlTemp + " ) ";
			return sqlTemp;
		}
		
		@Override
		public List<Sala> getSalasDisponiveisPorTurma2(Turma turma){
			String sqlFinal = "select DISTINCT s from Sala s WHERE s.utilizarNaDistribuicao = true AND s.idTipo = " + turma.getIdTipo() + " AND s.capacidade >= " + turma.getCapacidade() + " AND s.idPredio = " + turma.getIdPredio() + " AND ";
			
			List<Sala> salas = new ArrayList<Sala>();
			Auxiliar auxiliar = new Auxiliar();
			
      for (int h = 0; h < auxiliar.contadorDeGruposComSabado(turma.getHorario()); h++) {
         String grupo = auxiliar.retornaGrupoComSabado(turma.getHorario(), h);
				String[] arrayDias = auxiliar.retornaArrayDias(grupo);
				String turno = auxiliar.retornaTurno(grupo);
				String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);
				if (h > 0) {
					sqlFinal = sqlFinal + " AND ";
				}
				sqlFinal += retornaSql2(arrayDias, turno, arrayHorarios);
			}
			
			Query query = getSession().createQuery(sqlFinal);
			salas = query.list();
			return salas;
		}
		

		private String retornaSql(String[] arrayDias, String turno, String[] arrayHorarios) {
			String sqlTemp = "";
			for (int i = 0; i < arrayDias.length; i++) {
				for (int j = 0; j < arrayHorarios.length; j++) {
					sqlTemp = sqlTemp + " NOT EXISTS(select dt from Distribuicao dt WHERE dt.dia = " + arrayDias[i];
					sqlTemp = sqlTemp + " AND dt.turno = '" + turno + "'";
					sqlTemp = sqlTemp + " AND dt.horario = " + arrayHorarios[j];
					sqlTemp = sqlTemp + " AND dt.idSala = s.id)";
					if ((i != arrayDias.length - 1) || (j != arrayHorarios.length - 1)) {
						sqlTemp += " AND ";
					}
				}
			}
			
			sqlTemp += " ) ";
			return sqlTemp;
		}
		
		private String retornaSql2(String[] arrayDias, String turno, String[] arrayHorarios) {
			String sqlTemp = "";
			for (int i = 0; i < arrayDias.length; i++) {
				for (int j = 0; j < arrayHorarios.length; j++) {
					sqlTemp = sqlTemp + " NOT EXISTS(select dt from Distribuicao dt WHERE dt.dia = " + arrayDias[i];
					sqlTemp = sqlTemp + " AND dt.turno = '" + turno + "'";
					sqlTemp = sqlTemp + " AND dt.horario = " + arrayHorarios[j];
					sqlTemp = sqlTemp + " AND dt.idSala = s.id)";
					
					if ((i != arrayDias.length - 1) || (j != arrayHorarios.length - 1)) {
						sqlTemp = sqlTemp + " AND ";
					}
				}
				
			}
			
			return sqlTemp;
		}
		
		@Override
		public List<Sala> getAllDiponiveisParaDistribuicao(Long idPredio){
			String sql = "select s from Sala s WHERE s.utilizarNaDistribuicao = :utilizarNaDistribuicao AND s.idPredio = :idPredio";
			List<Sala> salas = new ArrayList<Sala>(0);
			
			Query query = getSession().createQuery(sql);
			query.setBoolean("utilizarNaDistribuicao", true);
			query.setLong("idPredio", idPredio);
			salas = query.list();
			
			return salas;
		}
		
		@Override
		public List<Sala> getAllOrdemAlfabetica(){
			DetachedCriteria criteria = criarDetachedCriteria();
			criteria.addOrder(Order.asc(SalaDaoImpl.NOME_SALA));
			List<Sala> retorno = findByDetachedCriteria(criteria);
			return retorno;
		}
		
		@Override
		public List<Sala> getAllByPredio(Long idPredio){
			String sql = "select s from Sala s WHERE s.idPredio = :idPredio ORDER BY s.nome";
			List<Sala> salas = new ArrayList<Sala>(0);

			Query query = getSession().createQuery(sql);
			query.setLong("idPredio", idPredio);

			salas = query.list();

			return salas;
		}
		
		@Override
		public List<Sala> getAllOrdemAlfabetica(Integer startPage, Integer maxPage){
			DetachedCriteria criteria = criarDetachedCriteria();
			criteria.addOrder(Order.asc(SalaDaoImpl.NOME_SALA));
			List<Sala> retorno = findByDetachedCriteria(criteria, startPage, maxPage);
			return retorno;
		}

   @Override
   public Sala getSalaParaTurmasConsecutivas(Long idPeriodo, Long idPredio) {
      String sql =
         "select s from Sala s WHERE s.utilizarNaDistribuicao = :utilizarNaDistribuicao AND s.idPeriodo = :idPeriodo AND s.idPredio = :idPredio";
      List<Sala> salas = new ArrayList<Sala>(0);

      Query query = getSession().createQuery(sql);
      query.setBoolean("utilizarNaDistribuicao", true);
      query.setLong("idPeriodo", idPeriodo);
      query.setLong("idPredio", idPredio);
      salas = query.list();

      return null;
   }

   @Override
   public boolean verificarSalaTemTurma(Long idSala) {
      String sql = "select count(d) from DisponibilidadeSala d WHERE d.idSala = :idSala";
      Query query = getSession().createQuery(sql);
      query.setLong("idSala", idSala);
      Long contador = (Long) query.uniqueResult();

      if (contador > 0) {
         return true;
      }
      return false;
   }

   @Override
   public List<Sala> getSalasDisponiveisPorTurma3(Turma turma, String sql) {
      List<Sala> retorno = new ArrayList<Sala>(0);
      /*
       * Auxiliar auxiliar = new Auxiliar(); String sql =
       * "select DISTINCT s from Sala s WHERE s.utilizarNaDistribuicao = true AND s.idTipo = " + turma.getIdTipo() + " AND s.capacidade >= "
       * + turma.getCapacidade() + " AND s.idPredio = " + turma.getIdPredio() + " AND NOT EXISTS (" +
       * "select ds from DisponibilidadeSala ds WHERE ds.idSala = s.id AND ds.idPeriodo = " + turma.getIdPeriodo() +
       * " AND ds.idHorarioSala IN ("; if (auxiliar.validarHorarioComSabado(turma.getHorario())) { List<Long> idsHorariosTemp = new
       * ArrayList<Long>(0); // List<String> listaDias = new ArrayList<String>(0); int controlador =
       * auxiliar.contadorDeGruposComSabado(turma.getHorario()); for (int h = 0; h < auxiliar.contadorDeGruposComSabado(turma.getHorario());
       * h++) { String grupo = auxiliar.retornaGrupoComSabado(turma.getHorario(), h); // String[] arrayDias =
       * auxiliar.retornaArrayDias(grupo); String turno = auxiliar.retornaTurno(grupo); String[] arrayHorarios =
       * auxiliar.retornaArrayHorarios(grupo); for (int i = 0; i < arrayDias.length; i++) { if (listaDias.contains(arrayDias[i])) {
       * listaDias.add(arrayDias[i]); } for (int k = 0; k < arrayHorarios.length; k++) {
       * idsHorariosTemp.add(this.horarioSalaDao.getByDiaTurnoEHorario(arrayDias[i], turno, arrayHorarios[k]).getId()); } } for (int k = 0;
       * k < arrayHorarios.length; k++) { idsHorariosTemp.add(this.horarioDao.getByTurnoEHorario(turno,
       * Integer.parseInt(arrayHorarios[k])).getId()); } Periodo periodo = this.periodoDao.get(turma.getIdPeriodo()); List<Date> feriados =
       * this.feriadoDao.getDatasFeriadosByPeriodo(periodo.getId()); List<Date> datasParaReserva =
       * ManipuladorDatas.retornaDatasLetivasPorDias(DateUtils.datasEntre(periodo.getDataInicio(), periodo.getDataTermino()), feriados,
       * grupo); Long[] arrayIdsHorarios = (Long[]) idsHorariosTemp.toArray(new Long[idsHorariosTemp.size()]); for (int i = 0; i <
       * arrayIdsHorarios.length; i++) { if (i == arrayIdsHorarios.length - 1) { sql = sql + arrayIdsHorarios[i] +
       * ") AND ds.dataReserva IN ("; } else { sql = sql + arrayIdsHorarios[i] + ","; } } Date[] arrayDatasReserva = (Date[])
       * datasParaReserva.toArray(new Date[datasParaReserva.size()]); DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd"); for (int i
       * = 0; i < arrayDatasReserva.length; i++) { if (i == arrayDatasReserva.length - 1) { sql = sql +
       * formatter.format(arrayDatasReserva[i]) + "))"; } else { sql = sql + formatter.format(arrayDatasReserva[i]) + ","; } } if (h !=
       * controlador - 1) { sql = sql + " AND NOT EXISTS (" +
       * "select ds from DisponibilidadeSala ds WHERE ds.idSala = s.id AND ds.idPeriodo = " + turma.getIdPeriodo() +
       * " AND ds.idHorarioSala IN ("; } } }
       */
      
      Query query = getSession().createQuery(sql);
      retorno = query.list();
      return retorno;
   }

   @Override
   public Integer countByPredio(Long idPredio, List<Long> idsSalasForaDaEstatistica) {
      String sql = "select count(s) from Sala s WHERE s.idPredio = :idPredio AND s.id NOT IN (";
      Long[] arraysIdsSalasForaDaEstatistica = (Long[]) idsSalasForaDaEstatistica.toArray(new Long[idsSalasForaDaEstatistica.size()]);
      for (int i = 0; i < arraysIdsSalasForaDaEstatistica.length; i++) {
         if (i == arraysIdsSalasForaDaEstatistica.length - 1) {
            sql = sql + arraysIdsSalasForaDaEstatistica[i] + ")";
         } else {
            sql = sql + arraysIdsSalasForaDaEstatistica[i] + ",";
         }
      }
      Query query = getSession().createQuery(sql);
      query.setLong("idPredio", idPredio);
      Long contador = (Long) query.uniqueResult();
      Integer retorno = contador.intValue();
      return retorno;
   }

   /*
    * public void setHorarioSalaDao(HorarioSalaDao horarioSalaDao) { this.horarioSalaDao = horarioSalaDao; }
    */

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

   @Override
   public List<Sala> getSalasComOcorrenciasDeChave(Date data) {
      List<Sala> retorno = new ArrayList<Sala>(0);
      DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Calendar dataInicial = new GregorianCalendar();
      Calendar dataFinal = new GregorianCalendar();
      dataInicial.setTime(data);
      dataFinal.setTime(data);
      dataInicial.set(Calendar.HOUR_OF_DAY, 0);
      dataInicial.set(Calendar.MINUTE, 0);
      dataInicial.set(Calendar.SECOND, 0);
      dataFinal.set(Calendar.HOUR_OF_DAY, 23);
      dataFinal.set(Calendar.MINUTE, 59);
      dataFinal.set(Calendar.SECOND, 59);

      String sql =
         "select s from Sala s WHERE s.id IN (select c.idSala from Chave c, HistoricoChave hc WHERE c.id = hc.idChave AND hc.horaRealizacao BETWEEN :dataInicial AND :dataFinal)";
      Query query = getSession().createQuery(sql);
      query.setString("dataInicial", formatter.format(dataInicial));
      query.setString("dataFinal", formatter.format(dataFinal));
      retorno = query.list();
      return retorno;
   }

}

