package br.ufrn.ct.cronos.dao;

import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.entity.Periodo;
import dev.home.componente.dao.hibernate.Dao;

public interface PeriodoDao extends Dao<Long, Periodo> {
	
   public boolean verificarPeriodoTemTurma(Long idPeriodo);

   public boolean verificarIntervaloDatasJaExiste(Date dataInicio, Date dataTermino);

   public List<Periodo> getPeriodoDentroDeIntervalo(Date dataInicio, Date dataTermino);

   public Periodo getPeriodoPorData(Date data);

   public Periodo getPeriodoLetivoAnterior();

   public List<Periodo> findAllOrderByDataTermino(Integer startPage, Integer maxPage);

   public List<Periodo> findAllOrderByDataTermino();

   /**
    * Retorna o período letivo atual. Caso a data atual não esteja dentro de um período letivo, retorna o próximo futuro período letivo.
    * @TODO Comentar Método
    * @return
    */
   public Periodo getPeriodoLetivoAtualOuProximo();
	
}
