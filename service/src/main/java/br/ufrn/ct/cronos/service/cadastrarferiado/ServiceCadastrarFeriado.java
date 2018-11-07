package br.ufrn.ct.cronos.service.cadastrarferiado;

import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import br.ufrn.ct.cronos.cadastrarferiado.vo.CadastrarFeriado;
import br.ufrn.ct.cronos.cadastrarferiado.vo.RespostaCadastrarFeriado;
import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.Feriado;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.error.ErrorCode;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceCadastrarFeriado extends AbstractService<CadastrarFeriado, RespostaCadastrarFeriado> {

   private FeriadoDao feriadoDao;
   private PeriodoDao periodoDao;

   @Override
   public RespostaCadastrarFeriado processa(CadastrarFeriado solicitacao) throws NegocioException {
      Feriado feriado = new Feriado();

      feriado.setData(solicitacao.getData());
      feriado.setDescricao(solicitacao.getDescricao());
      feriado.setIdPeriodo(solicitacao.getIdPeriodo());

      this.feriadoDao.save(feriado);

      return new RespostaCadastrarFeriado();
   }

   @Override
   public void valida(CadastrarFeriado solicitacao) throws NegocioException {
      if (StringUtils.isBlank(solicitacao.getDescricao())) {
         throw new NegocioException(ErrorCode.DESCRICAO_VAZIO);
      }
      if (solicitacao.getData() == null) {
         throw new NegocioException(ErrorCode.DATA_FERIADO_VAZIO);
      }
      if (solicitacao.getIdPeriodo() == 0 || solicitacao.getIdPeriodo() == null) {
         throw new NegocioException(ErrorCode.ID_PERIODO);
      }
      Periodo periodo = this.periodoDao.get(solicitacao.getIdPeriodo());
      Date dataInicio = periodo.getDataInicio();
      Date dataTermino = periodo.getDataTermino();
      if (!(solicitacao.getData().after(DateUtils.addDays(dataInicio, -1)) && solicitacao.getData().before(
         DateUtils.addDays(dataTermino, 1)))) {
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
