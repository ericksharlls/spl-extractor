package br.ufrn.ct.cronos.dao;

import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import dev.home.componente.dao.hibernate.Dao;

public interface SalaDao extends Dao<Long, Sala> {
	
	public List<Sala> getByPredio(Long idPredio, Integer startPage, Integer maxPage);
	public Integer countByPredio(Long idPredio);
	public List<Sala> getSalasDisponiveisPorTurma(Turma turma);
	public List<Sala> getSalasDisponiveisPorHorario(String horario);
	public boolean verificarDisponibilidadeSala(String horario, Date dataAgendamento, Long idSala);
	public List<Sala> getSalasDisponiveisPorTurma2(Turma turma);
	
	public List<Sala> getAllDiponiveisParaDistribuicao(Long idPredio);
	public List<Sala> getAllOrdemAlfabetica();
	public List<Sala> getAllOrdemAlfabetica(Integer startPage, Integer maxPage);
	
	public List<Sala> getAllByPredio(Long idPredio);

   // Mudancas para 2013.2
   public Sala getSalaParaTurmasConsecutivas(Long idPeriodo, Long idPredio);

   public boolean verificarSalaTemTurma(Long idSala);

   public List<Sala> getSalasDisponiveisPorTurma3(Turma turma, String sql);

   public Integer countByPredio(Long idPredio, List<Long> idsSalasForaDaEstatistica);

   // Mudancas para 2017.2
   public List<Sala> getSalasComOcorrenciasDeChave(Date data);
  
}
