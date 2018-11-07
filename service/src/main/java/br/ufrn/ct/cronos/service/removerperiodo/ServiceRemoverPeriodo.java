package br.ufrn.ct.cronos.service.removerperiodo;

import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.removerperiodo.vo.RemoverPeriodo;
import br.ufrn.ct.cronos.removerperiodo.vo.RespostaRemoverPeriodo;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRemoverPeriodo extends AbstractService<RemoverPeriodo, RespostaRemoverPeriodo> {
   
   private PeriodoDao periodoDao;
      
   @Override
   public RespostaRemoverPeriodo processa(RemoverPeriodo solicitacao) throws NegocioException {
      Periodo periodo = new Periodo();
         
      periodo.setId(solicitacao.getId());
         
      this.periodoDao.delete(periodo);
         
      return new RespostaRemoverPeriodo();
   }
      
   @Override
   public void valida(RemoverPeriodo solicitacao) throws NegocioException {
      if (solicitacao.getId() == null) {
         throw new NegocioException(ErrorCode.ID_VAZIO);
      }
      if (this.periodoDao.verificarPeriodoTemTurma(solicitacao.getId())) {
         throw new NegocioException(ErrorCode.PERIODO_TEM_TURMAS_VINCULADAS);
      }
   }
      
   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }
   
}
