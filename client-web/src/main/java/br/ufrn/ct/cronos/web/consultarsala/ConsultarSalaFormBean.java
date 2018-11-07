package br.ufrn.ct.cronos.web.consultarsala;

import javax.faces.model.DataModel;
import br.ufrn.ct.cronos.consultarsala.vo.ConsultarSala;
import br.ufrn.ct.cronos.consultarsala.vo.RespostaConsultarSala;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractConsultarFormBean;
import dev.home.componente.web.infra.paginacao.PaginacaoDataModel;

public class ConsultarSalaFormBean extends AbstractConsultarFormBean {
   
   private static final long serialVersionUID = 1L;

   private Long idPredio;
      
   public ConsultarSalaFormBean() throws NegocioException {
      // viewTable();
   }
      
   public DataModel getDados() throws NegocioException {
      ConsultarSala solicitacao = new ConsultarSala();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaConsultarSala resposta = (RespostaConsultarSala) service.executa(solicitacao);

      return new PaginacaoDataModel(resposta.getDados(), resposta.getTotalNumeroLinhas().intValue());
   }
      
   /*
    * private void viewTable() throws NegocioException { ContadorSala solicitacao = new ContadorSala(); Service<Response, Request> service =
    * ServiceUtils.getService(solicitacao.getClass().getSimpleName()); RespostaContadorSala resposta = (RespostaContadorSala)
    * service.executa(solicitacao); setVisible(resposta.getObjeto().getTotal() <= 0 ? false : true); }
    */

   private void popularVo(ConsultarSala solicitacao) {
      solicitacao.setMaxPage(getTable().getRows());
      solicitacao.setStartPage(getTable().getFirst());
      solicitacao.setIdPredio(this.idPredio);
   }

   public int getLinhas() {
      return 10;
   }

   public Long getIdPredio() {
      return idPredio;
   }

   public void setIdPredio(Long idPredio) {
      this.idPredio = idPredio;
   }

}
