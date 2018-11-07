package br.ufrn.ct.cronos.service.removerferiado;

import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.entity.Feriado;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.removerferiado.vo.RemoverFeriado;
import br.ufrn.ct.cronos.removerferiado.vo.RespostaRemoverFeriado;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRemoverFeriado extends AbstractService<RemoverFeriado, RespostaRemoverFeriado> {

   private FeriadoDao feriadoDao;

   @Override
   public RespostaRemoverFeriado processa(RemoverFeriado solicitacao) throws NegocioException {
      Feriado feriado = this.feriadoDao.get(solicitacao.getId());

      feriado.setId(solicitacao.getId());

      this.feriadoDao.delete(feriado);

      return new RespostaRemoverFeriado();
   }

   @Override
   public void valida(RemoverFeriado solicitacao) throws NegocioException {
      if (solicitacao.getId() == null) {
         throw new NegocioException(ErrorCode.ID_VAZIO);
      }
   }

   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

}
