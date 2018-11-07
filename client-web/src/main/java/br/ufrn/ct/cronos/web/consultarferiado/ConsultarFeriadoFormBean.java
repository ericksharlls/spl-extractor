package br.ufrn.ct.cronos.web.consultarferiado;

import javax.faces.model.DataModel;
import br.ufrn.ct.cronos.consultarferiado.vo.ConsultarFeriado;
import br.ufrn.ct.cronos.consultarferiado.vo.RespostaConsultarFeriado;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractConsultarFormBean;
import dev.home.componente.web.infra.paginacao.PaginacaoDataModel;

public class ConsultarFeriadoFormBean extends AbstractConsultarFormBean {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   public ConsultarFeriadoFormBean() throws NegocioException {
      super();
   }

   public DataModel getDados() throws NegocioException {
      ConsultarFeriado solicitacao = new ConsultarFeriado();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaConsultarFeriado resposta = (RespostaConsultarFeriado) service.executa(solicitacao);

      return new PaginacaoDataModel(resposta.getDados(), resposta.getTotalNumeroLinhas().intValue());
   }

   private void popularVo(ConsultarFeriado solicitacao) {
      solicitacao.setMaxPage(getTable().getRows());
      solicitacao.setStartPage(getTable().getFirst());
   }

   @Override
   public int getLinhas() {
      return 10;
   }

}
