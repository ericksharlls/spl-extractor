package br.ufrn.ct.cronos.dao;

import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.entity.HistoricoChave;
import dev.home.componente.dao.hibernate.Dao;

public interface HistoricoChaveDao extends Dao<Long, HistoricoChave> {
  
	public HistoricoChave getUltimaOcorrenciaChave(Long idChave);
	public List<HistoricoChave> getEntradasPorPeriodoEChave(Date dataInicial, Date dataFinal, Long idChave);
	public HistoricoChave getUltimaSaidaPorEntrada(HistoricoChave historicoChave);
	public boolean existeOcorrenciaChave(Long idChave);

   public List<HistoricoChave> getHistoricoChavePorData(Date data);

   public List<HistoricoChave> getHistoricoChavePorDataESala(Date data, Long idSala);
	
}

