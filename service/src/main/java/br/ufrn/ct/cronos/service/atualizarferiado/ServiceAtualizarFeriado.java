package br.ufrn.ct.cronos.service.atualizarferiado;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import br.ufrn.ct.cronos.atualizarferiado.vo.AtualizarFeriado;
import br.ufrn.ct.cronos.atualizarferiado.vo.RespostaAtualizarFeriado;
import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.Feriado;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.error.ErrorCode;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceAtualizarFeriado extends AbstractService<AtualizarFeriado, RespostaAtualizarFeriado> {

   private FeriadoDao feriadoDao;
   private PeriodoDao periodoDao;

   @Override
   public RespostaAtualizarFeriado processa(AtualizarFeriado solicitacao) throws NegocioException {
      Feriado feriado = this.feriadoDao.get(solicitacao.getId());

      feriado.setData(solicitacao.getData());
      feriado.setDescricao(solicitacao.getDescricao());
      feriado.setIdPeriodo(solicitacao.getIdPeriodo());

      this.feriadoDao.merge(feriado);

      return new RespostaAtualizarFeriado();
   }

   @Override
   public void valida(AtualizarFeriado solicitacao) throws NegocioException {
      if (StringUtils.isBlank(solicitacao.getDescricao())) {
         throw new NegocioException(ErrorCode.DESCRICAO_VAZIO);
      }
      if (solicitacao.getData() == null) {
         throw new NegocioException(ErrorCode.DATA_VAZIO);
      }
      if (solicitacao.getIdPeriodo() == 0 || solicitacao.getIdPeriodo() == null) {
         throw new NegocioException(ErrorCode.ID_PERIODO);
      }
      Periodo periodo = this.periodoDao.get(solicitacao.getIdPeriodo());
      if (!(solicitacao.getData().after(DateUtils.addDays(periodo.getDataInicio(), -1)) && solicitacao.getData().before(
         DateUtils.addDays(periodo.getDataTermino(), 1)))) {
         throw new NegocioException(ErrorCode.FERIADO_NAO_CORRESPONDE_PERIODO_INFORMADO);
      }
   }

   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

}
