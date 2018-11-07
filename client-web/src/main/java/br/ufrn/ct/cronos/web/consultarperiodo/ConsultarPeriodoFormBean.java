package br.ufrn.ct.cronos.web.consultarperiodo;

import javax.faces.model.DataModel;
import br.ufrn.ct.cronos.consultarperiodo.vo.ConsultarPeriodo;
import br.ufrn.ct.cronos.consultarperiodo.vo.RespostaConsultarPeriodo;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractConsultarFormBean;
import dev.home.componente.web.infra.paginacao.PaginacaoDataModel;

public class ConsultarPeriodoFormBean extends AbstractConsultarFormBean {

   private static final long serialVersionUID = 1L;

   public ConsultarPeriodoFormBean() throws NegocioException {
      super();
   }

   public DataModel getDados() throws NegocioException {
      ConsultarPeriodo solicitacao = new ConsultarPeriodo();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaConsultarPeriodo resposta = (RespostaConsultarPeriodo) service.executa(solicitacao);

      return new PaginacaoDataModel(resposta.getDados(), resposta.getTotalNumeroLinhas().intValue());
   }

   private void popularVo(ConsultarPeriodo solicitacao) {
      solicitacao.setMaxPage(getTable().getRows());
      solicitacao.setStartPage(getTable().getFirst());
   }

   public int getLinhas() {
      return 10;
   }

}
