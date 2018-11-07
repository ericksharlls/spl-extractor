package br.ufrn.ct.cronos.service.cadastrarperiodo;

import org.apache.commons.lang.StringUtils;
import br.ufrn.ct.cronos.cadastrarperiodo.vo.CadastrarPeriodo;
import br.ufrn.ct.cronos.cadastrarperiodo.vo.RespostaCadastrarPeriodo;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.error.ErrorCode;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.utils.date.DateUtils;

public class ServiceCadastrarPeriodo extends AbstractService<CadastrarPeriodo, RespostaCadastrarPeriodo> {
      
   private PeriodoDao periodoDao;
      
   @Override
   public RespostaCadastrarPeriodo processa(CadastrarPeriodo solicitacao) throws NegocioException {
      Periodo periodo = new Periodo();

      periodo.setDataInicio(solicitacao.getDataInicio());
      periodo.setDataTermino(solicitacao.getDataTermino());
      periodo.setDescricao(solicitacao.getDescricao());
      periodo.setNome(solicitacao.getNome());
      periodo.setIsPeriodoLetivo(solicitacao.getPeriodoLetivo());
      periodo.setAno(solicitacao.getAno());
      periodo.setNumero(solicitacao.getNumeroPeriodo());

      this.periodoDao.merge(periodo);

      return new RespostaCadastrarPeriodo();
   }
      
   @Override
   public void valida(CadastrarPeriodo solicitacao) throws NegocioException {
      if (StringUtils.isBlank(solicitacao.getNome())) {
         throw new NegocioException(ErrorCode.NOME_VAZIO);
      }
      if (StringUtils.isBlank(solicitacao.getDescricao())) {
         throw new NegocioException(ErrorCode.DESCRICAO_VAZIO);
      }
      if (solicitacao.getPeriodoLetivo() == null) {
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
      if (this.periodoDao.verificarIntervaloDatasJaExiste(solicitacao.getDataInicio(), solicitacao.getDataTermino())) {
         throw new NegocioException(ErrorCode.PERIODO_INVALIDO);
      }

   }
      
   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

}
