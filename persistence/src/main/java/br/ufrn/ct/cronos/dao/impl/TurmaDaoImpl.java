package br.ufrn.ct.cronos.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.transform.AliasToBeanConstructorResultTransformer;
import org.hibernate.transform.AliasToBeanResultTransformer;

import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.entity.EntidadeRelatorioTurmaDepartamento;
import br.ufrn.ct.cronos.entity.Turma;
import dev.home.componente.dao.hibernate.impl.AbstractDao;

public class TurmaDaoImpl extends AbstractDao<Long, Turma> implements TurmaDao {
	
	private static final String CODIGO_DISCIPLINA = "codigoDisciplina";

	public TurmaDaoImpl() {
		super();
	}
	
	@Override
	public List<Turma> getAllOrdemAlfabetica(Integer startPage, Integer maxPage){
		DetachedCriteria criteria = criarDetachedCriteria();
		criteria.addOrder(Order.asc(TurmaDaoImpl.CODIGO_DISCIPLINA));
		List<Turma> retorno = findByDetachedCriteria(criteria, startPage, maxPage);
		return retorno;
	}
	
	@Override
   public List<Turma> getTurmasPorDepartamentoEPeriodo(Integer startPage, Integer maxPage, String departamento, Long idPeriodo) {
      String sql =
         "select t from Turma t WHERE t.departamento = :departamento AND t.idPeriodo = :idPeriodo ORDER BY t.codigoDisciplina,t.numero";
      List<Turma> retorno = new ArrayList<Turma>();

      Query query = getSession().createQuery(sql);
      query.setString("departamento", departamento);
      query.setLong("idPeriodo", idPeriodo);

      query.setFirstResult(startPage);
      query.setMaxResults(maxPage);

      retorno = query.list();

		return retorno;
	}
	
	@Override
   public Integer contadorTurmasPorDepartamentoEPeriodo(Long idDepartamento, Long idPeriodo) {
      String sql =
         "select count(t) from Turma t WHERE t.idDepartamento = :idDepartamento AND t.idPeriodo = :idPeriodo ORDER BY t.codigoDisciplina,t.numero";

      Query query = getSession().createQuery(sql);
      query.setLong("idDepartamento", idDepartamento);
      query.setLong("idPeriodo", idPeriodo);

      Long retorno = (Long) query.uniqueResult();

      return retorno.intValue();

	}
 
	@Override
	public List<String> getAllNomesDocentes(Long idPredio) {
		String sql = "select distinct(t.docente) from Turma t WHERE t.idPredio = :idPredio";
		List<String> docentes = new ArrayList<String>();
		
		Query query = getSession().createQuery(sql);
		query.setLong("idPredio", idPredio);
		docentes = query.list();
		
		return docentes;
	}
	
	@Override
	public List<Turma> getTurmasPorDocente(String docente, Long idPredio){
		String sql = "select t from Turma t WHERE t.docente = :docente AND t.idPredio = :idPredio ORDER BY t.horario";
		List<Turma> turmas = new ArrayList<Turma>();
		
		Query query = getSession().createQuery(sql);
		query.setString("docente", docente);
		query.setLong("idPredio", idPredio);
		turmas = query.list();
		
		return turmas;
	}

	@Override
	public void refresh(Turma t){
		getSession().refresh(t);
	}

	@Override
   public List<Turma> getTurmasDistribuidasPorPeriodoEPredio(Long idPeriodo, Long idPredio) {
      String sql =
         "select DISTINCT t from DisponibilidadeSala ds, Turma t WHERE ds.idTurma = t.id AND t.idPredio = :idPredio AND t.idPeriodo = :idPeriodo ORDER BY t.codigoDisciplina";
      List<Turma> turmas = new ArrayList<Turma>(0);
		Query query = getSession().createQuery(sql);
      query.setLong("idPredio", idPredio);
      query.setLong("idPeriodo", idPeriodo);
		turmas = query.list();
		
		return turmas;
	}
	
	@Override
	public List<Turma> getTurmasPorDepartamento(String departamento){
		String sql = "select t from Turma t WHERE t.departamento = '" + departamento + "' ORDER BY t.codigoDisciplina";
		List<Turma> turmas = new ArrayList<Turma>();
		
		Query query = getSession().createQuery(sql);
		turmas = query.list();
 
		return turmas;
	}
	
	@Override
	public List<String> getDepartamentos(){
		String sql = "select DISTINCT(t.departamento) from Turma t";
		List<String> departamentos = new ArrayList<String>();
		
		Query query = getSession().createQuery(sql);
		departamentos = query.list();
		
		return departamentos;
	}
 
	@Override
	public List<Turma> getTurmasNaoDistribuidas(Long idPredio){
		String sql = "select t from Turma t WHERE t.distribuir = :distribuir AND t.idPredio = :idPredio";
		List<Turma> turmas = new ArrayList<Turma>();
		
		Query query = getSession().createQuery(sql);
		query.setBoolean("distribuir", true);
		query.setLong("idPredio", idPredio);
		turmas = query.list();
		
		return turmas;
	}
	
	@Override
	public List<Turma> getAllOrdemAlfabetica(){
		DetachedCriteria criteria = criarDetachedCriteria();
		criteria.addOrder(Order.asc(TurmaDaoImpl.CODIGO_DISCIPLINA));
		List<Turma> retorno = findByDetachedCriteria(criteria);
		return retorno;
	}
	
	@Override
   public List<Turma> getTurmasComSalasCheais(Long idPredio, Long idPeriodo) {
      String sql =
         "select DISTINCT t from Turma t, DisponibilidadeSala ds, Sala s WHERE t.idPredio = :idPredio AND t.idPeriodo = :idPeriodo AND t.id = ds.idTurma "
            + " AND ds.idSala = s.id AND t.alunosMatriculados >= s.capacidade ORDER BY t.nomeDisciplina";
		List<Turma> turmas = new ArrayList<Turma>();
 
		Query query = getSession().createQuery(sql);
      query.setLong("idPredio", idPredio);
      query.setLong("idPeriodo", idPeriodo);
		turmas = query.list();
		
		return turmas;
	}
	
	@Override
   public List<Turma> getTurmasPorCodigoDocenteOuDisciplina(Long idPeriodo, Integer startPage, Integer maxPage, String codigo,
      String docente,
      String nomeDisciplina) {
		List<Turma> retorno = new ArrayList<Turma>(0);
      String sql =
         "select DISTINCT t FROM Turma t, TurmaDocente td WHERE t.id = td.idTurma AND t.idPeriodo = :idPeriodo AND t.codigoDisciplina LIKE :codigo AND "
            + "t.nomeDisciplina LIKE :nomeDisciplina AND td.idDocente IN (select f.id from Funcionario f WHERE f.nome LIKE :docente)";
		
		Query query = getSession().createQuery(sql);
		
      query.setLong("idPeriodo", idPeriodo);
		query.setString("codigo", "%" + codigo + "%");
		query.setString("docente", "%" + docente + "%");
      query.setString("nomeDisciplina", "%" + nomeDisciplina + "%");
		
		query.setFirstResult(startPage);
		query.setMaxResults(maxPage);
		
		retorno = query.list();
		
		return retorno;
	}
 
	@Override
   public Integer contadorTurmasPorCodigoDocenteOuDisciplina(Long idPeriodo, String codigo, String docente, String nomeDisciplina) {
      /*
       * String sql =
       * "select count(DISTINCT t) from Turma t WHERE t.idPeriodo = :idPeriodo AND t.codigoDisciplina LIKE :codigo AND t.docente LIKE :docente AND t.nomeDisciplina LIKE :nomeDisciplina ORDER BY t.codigoDisciplina"
       * ;
       */
      String sql =
         "select COUNT(DISTINCT t) FROM Turma t, TurmaDocente td WHERE t.id = td.idTurma AND t.idPeriodo = :idPeriodo AND t.codigoDisciplina LIKE :codigo AND "
            + "t.nomeDisciplina LIKE :nomeDisciplina AND td.idDocente IN (select f.id from Funcionario f WHERE f.nome LIKE :docente)";
		
		Query query = getSession().createQuery(sql);
		
      query.setLong("idPeriodo", idPeriodo);
		query.setString("codigo", "%" + codigo + "%");
      query.setString("nomeDisciplina", "%" + nomeDisciplina + "%");
      query.setString("docente", "%" + docente + "%");
		
		Long retorno = (Long) query.uniqueResult();
		
		return retorno.intValue();
	}

   @Override
   public List<Turma> getTurmasPorDocente(String docente, Long idPeriodo, Long idPredio) {
      String sql =
         "select t from Turma t WHERE t.docente = :docente AND t.idPeriodo = :idPeriodo AND t.idPredio = :idPredio ORDER BY t.horario";
      List<Turma> turmas = new ArrayList<Turma>();

      Query query = getSession().createQuery(sql);
      query.setString("docente", docente);
      query.setLong("idPeriodo", idPeriodo);
      query.setLong("idPredio", idPredio);
      turmas = query.list();

      return turmas;
   }

   @Override
   public List<String> getAllNomesDocentes(Long idPeriodo, Long idPredio) {
      String sql = "select distinct(t.docente) from Turma t WHERE t.idPeriodo = :idPeriodo AND t.idPredio = :idPredio";
      List<String> docentes = new ArrayList<String>();

      Query query = getSession().createQuery(sql);
      query.setLong("idPeriodo", idPeriodo);
      query.setLong("idPredio", idPredio);
      docentes = query.list();

      return docentes;
   }

   @Override
   public List<Turma> getTurmasNaoDistribuidas(Long idPeriodo, Long idPredio) {
      String sql = "select t from Turma t WHERE t.distribuir = :distribuir AND t.idPeriodo = :idPeriodo AND t.idPredio = :idPredio";
      List<Turma> turmas = new ArrayList<Turma>();

      Query query = getSession().createQuery(sql);
      query.setBoolean("distribuir", true);
      query.setLong("idPeriodo", idPeriodo);
      query.setLong("idPredio", idPredio);

      turmas = query.list();

      return turmas;
   }

   @Override
   public List<Turma> getTurmasPorPeriodoEPredio(Long idPeriodo, Long idPredio) {
      String sql =
         "select t from Turma t WHERE t.idPredio = :idPredio AND t.idPeriodo = :idPeriodo ORDER BY t.codigoDisciplina,t.numero";
      List<Turma> turmas = new ArrayList<Turma>(0);
      Query query = getSession().createQuery(sql);
      query.setLong("idPredio", idPredio);
      query.setLong("idPeriodo", idPeriodo);
      turmas = query.list();

      return turmas;

   }

   @Override
   public List<Turma> getTurmasNaoDistribuidas(Long idPredio, Long idPeriodo, Long idDepartamento) {
      String sql =
         "select t from Turma t WHERE t.idDepartamento = :idDepartamento AND t.idPredio = :idPredio AND t.idPeriodo = :idPeriodo AND "
            +
            "t.id NOT IN(select distinct(ds.idTurma) from DisponibilidadeSala ds WHERE ds.idPeriodo = :idPeriodo AND ds.idTurma IS NOT NULL) ORDER BY t.nomeDisciplina";
      List<Turma> turmas = new ArrayList<Turma>();

      Query query = getSession().createQuery(sql);
      query.setLong("idDepartamento", idDepartamento);
      query.setLong("idPredio", idPredio);
      query.setLong("idPeriodo", idPeriodo);
      turmas = query.list();

      return turmas;
   }

   @Override
   public List<Turma> getTurmasPorDepartamento(Long idPredio, Long idPeriodo, Long idDepartamento) {
      String sql =
         "select t from Turma t WHERE t.idDepartamento = :idDepartamento AND t.idPredio = :idPredio AND t.idPeriodo = :idPeriodo ORDER BY t.nomeDisciplina";
      List<Turma> turmas = new ArrayList<Turma>();

      Query query = getSession().createQuery(sql);
      query.setLong("idDepartamento", idDepartamento);
      query.setLong("idPredio", idPredio);
      query.setLong("idPeriodo", idPeriodo);
      turmas = query.list();

      return turmas;
   }

   @Override
   public List<Turma> getAllBySalaTempIsNotNull(Long idPeriodo) {
      String sql =
         "select t from Turma t WHERE t.idPeriodo = :idPeriodo AND t.idSalaTemp IS NOT NUll";
      List<Turma> turmas = new ArrayList<Turma>(0);
      Query query = getSession().createQuery(sql);
      query.setLong("idPeriodo", idPeriodo);
      turmas = query.list();
      return turmas;
   }

   @Override
   public Turma getTurmaAnteriorSemelhantePorPeriodo(Turma turma, Long idPeriodo) {
      Turma retorno = null;
      String sql =
         "select t from Turma t WHERE t.codigoDisciplina = :codigoDisciplina AND t.horario = :horario AND t.numero = :numero AND t.idPeriodo = :idPeriodo AND t.id <> :idTurma";
      Query query = getSession().createQuery(sql);
      query.setString("codigoDisciplina", turma.getCodigoDisciplina());
      query.setString("horario", turma.getHorario());
      query.setString("numero", turma.getNumero());
      query.setLong("idPeriodo", idPeriodo);
      query.setLong("idTurma", turma.getId());
      retorno = (Turma) query.uniqueResult();

      return retorno;
   }

   @Override
   public List<Turma> getTurmasPorDepartamentoEPeriodoOrderByNomeDisciplina(Integer startPage, Integer maxPage, Long idDepartamento,
      Long idPeriodo) {
      String sql =
         "select t from Turma t WHERE t.idDepartamento = :idDepartamento AND t.idPeriodo = :idPeriodo ORDER BY t.nomeDisciplina,t.numero";
      List<Turma> retorno = new ArrayList<Turma>();

      Query query = getSession().createQuery(sql);
      query.setLong("idDepartamento", idDepartamento);
      query.setLong("idPeriodo", idPeriodo);

      query.setFirstResult(startPage);
      query.setMaxResults(maxPage);

      retorno = query.list();

      return retorno;

   }

   @Override
   public List<Turma> getTurmasPorPeriodoPredioEDepartamentoOrderByNomeDisciplina(Long idPeriodo, Long idPredio, Long idDepartamento) {
      String sql =
         "select t from Turma t WHERE t.idPredio = :idPredio AND t.idPeriodo = :idPeriodo AND t.idDepartamento = :idDepartamento ORDER BY t.nomeDisciplina,t.numero";
      List<Turma> turmas = new ArrayList<Turma>(0);
      Query query = getSession().createQuery(sql);
      query.setLong("idPredio", idPredio);
      query.setLong("idPeriodo", idPeriodo);
      query.setLong("idDepartamento", idDepartamento);
      turmas = query.list();

      return turmas;
   }

   @Override
   public boolean turmaJaFoiDistribuida(Long idTurma) {
      String sql =
         "select count(ds) from DisponibilidadeSala ds WHERE ds.idTurma = :idTurma";

      Query query = getSession().createQuery(sql);
      query.setLong("idTurma", idTurma);

      Long retorno = (Long) query.uniqueResult();
      if (retorno > 0) {
         return true;
      }
      return false;
   }

   @Override
   public List<Turma> getTurmasPorPeriodo(Long idPeriodo) {
      String sql = "select t from Turma t WHERE t.idPeriodo = :idPeriodo";
      List<Turma> turmas = new ArrayList<Turma>(0);
      Query query = getSession().createQuery(sql);
      query.setLong("idPeriodo", idPeriodo);
      turmas = query.list();

      return turmas;
   }

   @Override
   public Turma getByIdTurmaSIGAA(Long idTurmaSIGAA) {
      String sql = "select t from Turma t WHERE t.idTurmaSIGAA = :idTurmaSIGAA";
      Turma turma = null;
      Query query = getSession().createQuery(sql);
      query.setLong("idTurmaSIGAA", idTurmaSIGAA);
      turma = (Turma) query.uniqueResult();

      return turma;
   }

   @Override
   public List<Turma> getTurmasPorDocente(Long idDocente, Long idPeriodo, Long idPredio) {
      String sql =
         "select t from Turma t, TurmaDocente td WHERE t.id = td.idTurma AND td.idDocente = :idDocente AND t.idPeriodo = :idPeriodo AND t.idPredio = :idPredio ORDER BY t.horario";
      List<Turma> turmas = new ArrayList<Turma>();
      Query query = getSession().createQuery(sql);
      query.setLong("idDocente", idDocente);
      query.setLong("idPeriodo", idPeriodo);
      query.setLong("idPredio", idPredio);
      turmas = query.list();

      return turmas;
   }

   @Override
   public boolean turmaTemMaisDeUmaSala(Long idTurma) {
      String sql = "select count(DISTINCT s) from Sala s, DisponibilidadeSala ds WHERE ds.idSala = s.id AND ds.idTurma = :idTurma";
      Query query = getSession().createQuery(sql);
      query.setLong("idTurma", idTurma);
      Long retorno = (Long) query.uniqueResult();
      if (retorno.intValue() > 1) {
         return true;
      }
      return false;
   }

   @Override
	public List<EntidadeRelatorioTurmaDepartamento> getRelatorioAlocacaoTurmasPorDepartamento(Long idPredio, Long idPeriodo,Long idDepartamento) {
	   	String sql =
	         "select DAYOFWEEK(ds.dataReserva) as dia, h.turno as turno, ds.dataReserva as dataReserva, ds.idSala as idSala, ds.idPeriodo as idPeriodo, ds.idHorarioSala as idHorarioSala, ds.idTurma as idTurma from DisponibilidadeSala ds, " +
	         "Horario h WHERE ds.idHorarioSala = h.id AND ds.idPeriodo = :idPeriodo AND ds.idTurma IN (select t.id from Turma t WHERE t.idPeriodo = :idPeriodo AND " +
	         "t.idDepartamento = :idDepartamento AND t.idPredio = :idPredio) GROUP BY ds.idTurma, ds.idSala, DAYOFWEEK(ds.dataReserva), ds.idHorarioSala";
	      List<EntidadeRelatorioTurmaDepartamento> turmas = new ArrayList<EntidadeRelatorioTurmaDepartamento>();

	      Query query = getSession().createQuery(sql);
	      query.setLong("idDepartamento", idDepartamento);
	      query.setLong("idPredio", idPredio);
	      query.setLong("idPeriodo", idPeriodo);
	      
	      turmas = query.setResultTransformer(new AliasToBeanResultTransformer(EntidadeRelatorioTurmaDepartamento.class)).list();

	      return turmas;
   }
   
   @Override
   public List<Turma> getTurmasPorCodigoEDisciplina(Long idPeriodo, Integer startPage, Integer maxPage, String codigo, String nomeDisciplina) {
	   List<Turma> retorno = new ArrayList<Turma>(0);
	   String sql = "select DISTINCT t FROM Turma t WHERE t.idPeriodo = :idPeriodo AND t.codigoDisciplina LIKE :codigo AND t.nomeDisciplina LIKE :nomeDisciplina";
		
		Query query = getSession().createQuery(sql);
		
		query.setLong("idPeriodo", idPeriodo);
		query.setString("codigo", "%" + codigo + "%");
		query.setString("nomeDisciplina", "%" + nomeDisciplina + "%");
		
		query.setFirstResult(startPage);
		query.setMaxResults(maxPage);
		
		retorno = query.list();
		
		return retorno;
	}
   
   @Override
   public Integer contadorTurmasPorCodigoEDisciplina(Long idPeriodo, String codigo, String nomeDisciplina) {
	   String sql =	"select COUNT(DISTINCT t) FROM Turma t WHERE t.idPeriodo = :idPeriodo AND t.codigoDisciplina LIKE :codigo AND t.nomeDisciplina LIKE :nomeDisciplina";
		
	   Query query = getSession().createQuery(sql);
	   query.setLong("idPeriodo", idPeriodo);
	   query.setString("codigo", "%" + codigo + "%");
	   query.setString("nomeDisciplina", "%" + nomeDisciplina + "%");
	   Long retorno = (Long) query.uniqueResult();
		
	   return retorno.intValue();
	}
	
}
