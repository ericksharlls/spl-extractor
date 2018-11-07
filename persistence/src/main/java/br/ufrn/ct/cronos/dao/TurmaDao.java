package br.ufrn.ct.cronos.dao;

import java.util.List;

import br.ufrn.ct.cronos.entity.EntidadeRelatorioTurmaDepartamento;
import br.ufrn.ct.cronos.entity.Turma;
import dev.home.componente.dao.hibernate.Dao;

public interface TurmaDao extends Dao<Long, Turma> {
  
	public List<Turma> getAllOrdemAlfabetica();
	public List<Turma> getAllOrdemAlfabetica(Integer startPage, Integer maxPage);
		
	public List<String> getAllNomesDocentes(Long idPredio);
	public List<Turma> getTurmasPorDocente(String docente, Long idPredio);
	public void refresh(Turma t);

	public List<String> getDepartamentos();
	public List<Turma> getTurmasPorDepartamento(String departamento);
	public List<Turma> getTurmasNaoDistribuidas(Long idPredio);

   public List<Turma> getTurmasPorCodigoDocenteOuDisciplina(Long idPeriodo, Integer startPage, Integer maxPage, String codigo,
      String docente, String nomeDisciplina);

   public Integer contadorTurmasPorCodigoDocenteOuDisciplina(Long idPeriodo, String codigo, String docente, String nomeDisciplina);
   
   public Turma getByIdTurmaSIGAA(Long idTurmaSIGAA);


   // Mudanças para 2013.2
   public List<Turma> getTurmasPorDepartamentoEPeriodo(Integer startPage, Integer maxPage, String departamento, Long idPeriodo);

   public List<Turma> getTurmasPorDepartamentoEPeriodoOrderByNomeDisciplina(Integer startPage, Integer maxPage, Long idDepartamento,
      Long idPeriodo);

   public Integer contadorTurmasPorDepartamentoEPeriodo(Long idDepartamento, Long idPeriodo);

   public List<Turma> getTurmasPorDocente(String docente, Long idPeriodo, Long idPredio);

   public List<String> getAllNomesDocentes(Long idPeriodo, Long idPredio);

   public List<Turma> getTurmasNaoDistribuidas(Long idPeriodo, Long idPredio);

   public List<Turma> getTurmasDistribuidasPorPeriodoEPredio(Long idPeriodo, Long idPredio);

   public List<Turma> getTurmasPorPeriodoEPredio(Long idPeriodo, Long idPredio);

   public List<Turma> getTurmasPorPeriodoPredioEDepartamentoOrderByNomeDisciplina(Long idPeriodo, Long idPredio, Long idDepartamento);

   public List<Turma> getTurmasComSalasCheais(Long idPredio, Long idPeriodo);

   public List<Turma> getTurmasNaoDistribuidas(Long idPredio, Long idPeriodo, Long idDepartamento);

   public List<Turma> getTurmasPorDepartamento(Long idPredio, Long idPeriodo, Long idDepartamento);

   public List<Turma> getAllBySalaTempIsNotNull(Long idPeriodo);

   public Turma getTurmaAnteriorSemelhantePorPeriodo(Turma turma, Long idPeriodo);

   public boolean turmaJaFoiDistribuida(Long idTurma);

   public List<Turma> getTurmasPorPeriodo(Long idPeriodo);

   // Mudancas para 2017.2
   public List<Turma> getTurmasPorDocente(Long idDocente, Long idPeriodo, Long idPredio);

   public boolean turmaTemMaisDeUmaSala(Long idTurma);
   
   // Mudancas para 2018.2
   public List<EntidadeRelatorioTurmaDepartamento> getRelatorioAlocacaoTurmasPorDepartamento(Long idPredio, Long idPeriodo, Long idDepartamento);
   public List<Turma> getTurmasPorCodigoEDisciplina(Long idPeriodo, Integer startPage, Integer maxPage, String codigo, String nomeDisciplina);
   public Integer contadorTurmasPorCodigoEDisciplina(Long idPeriodo, String codigo, String nomeDisciplina);
  
}
