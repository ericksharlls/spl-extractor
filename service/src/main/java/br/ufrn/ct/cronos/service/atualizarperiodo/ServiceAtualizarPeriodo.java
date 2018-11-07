package br.ufrn.ct.cronos.service.atualizarperiodo;

import org.apache.commons.lang.StringUtils;
import br.ufrn.ct.cronos.atualizarperiodo.vo.AtualizarPeriodo;
import br.ufrn.ct.cronos.atualizarperiodo.vo.RespostaAtualizarPeriodo;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.error.ErrorCode;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.utils.date.DateUtils;

public class ServiceAtualizarPeriodo extends AbstractService<AtualizarPeriodo, RespostaAtualizarPeriodo> {
   
   private PeriodoDao periodoDao;
      
   @Override
   public RespostaAtualizarPeriodo processa(AtualizarPeriodo solicitacao) throws NegocioException {
      Periodo periodo = this.periodoDao.get(solicitacao.getId());
      periodo.setDataInicio(solicitacao.getDataInicio());
      periodo.setDataTermino(solicitacao.getDataTermino());
      periodo.setDescricao(solicitacao.getDescricao());
      periodo.setNome(solicitacao.getNome());
      periodo.setIsPeriodoLetivo(solicitacao.getIsPeriodoLetivo());
      periodo.setAno(solicitacao.getAno());
      periodo.setNumero(solicitacao.getNumeroPeriodo());
         
      this.periodoDao.merge(periodo);
         
      return new RespostaAtualizarPeriodo();
   }

   @Override
   public void valida(AtualizarPeriodo solicitacao) throws NegocioException {
      if (StringUtils.isBlank(solicitacao.getNome())) {
         throw new NegocioException(ErrorCode.NOME_VAZIO);
      }
      if (StringUtils.isBlank(solicitacao.getDescricao())) {
         throw new NegocioException(ErrorCode.DESCRICAO_VAZIO);
      }
      if (solicitacao.getIsPeriodoLetivo() == null) {
         throw new NegocioException(ErrorCode.PERIODO_LETIVO_VAZIO);
      }
      if (solicitacao.getDataInicio() == null) {
         throw new NegocioException(ErrorCode.DATA_INICIAL_VAZIO);
      }
      if (solicitacao.getDataTermino() == null) {
         throw new NegocioException(ErrorCode.DATA_FINAL_VAZIO);
      }
      if (DateUtils.isBefore(solicitacao.getDataTermino(), solicitacao.getDataInicio())) {
         throw new NegocioException(ErrorCode.DATA_TERMINO_MENOR_DATA_INICIO);
      }
      if (solicitacao.getAno() == 0 || solicitacao.getAno() == null) {
         throw new NegocioException(ErrorCode.ANO_PERIODO_VAZIO);
      }
      if (solicitacao.getNumeroPeriodo() == 0 || solicitacao.getNumeroPeriodo() == null) {
         throw new NegocioException(ErrorCode.NUMERO_PERIODO_VAZIO);
      }

      Periodo periodo = this.periodoDao.get(solicitacao.getId());
      if (this.periodoDao.getPeriodoDentroDeIntervalo(solicitacao.getDataInicio(), solicitacao.getDataTermino()).size() > 0) {
         for (Periodo p : this.periodoDao.getPeriodoDentroDeIntervalo(solicitacao.getDataInicio(), solicitacao.getDataTermino())) {
            if (!p.getId().equals(periodo.getId())) {
               throw new NegocioException(ErrorCode.PERIODO_INVALIDO);
            }
         }
      }
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }
      
}
