package br.ufrn.ct.cronos.web.manterturmasantigas;

import br.ufrn.ct.cronos.distribuirturmasantigas.vo.DistribuirTurmasAntigas;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class ManterTurmasAntigasFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;

   private Long idPeriodo;

   public String distribuirTurmasAntigas() throws NegocioException {
      DistribuirTurmasAntigas solicitacao = new DistribuirTurmasAntigas();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);

      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");

      return null;
   }

   private void popularVo(DistribuirTurmasAntigas solicitacao) {
      solicitacao.setIdPeriodo(this.idPeriodo);
   }

   public Long getIdPeriodo() {
      return idPeriodo;
   }

   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
   }

}
