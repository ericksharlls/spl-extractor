
package br.ufrn.ct.cronos.web.consultarfuncionario;

import javax.faces.model.DataModel;
import br.ufrn.ct.cronos.consultarfuncionario.vo.ConsultarFuncionario;
import br.ufrn.ct.cronos.consultarfuncionario.vo.RespostaConsultarFuncionario;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractConsultarFormBean;
import dev.home.componente.web.infra.paginacao.PaginacaoDataModel;

public class ConsultarFuncionarioFormBean extends AbstractConsultarFormBean {

   private static final long serialVersionUID = 1L;

   private Long idTipoFuncionario;

   public ConsultarFuncionarioFormBean() throws NegocioException {
      super();
   }

   public DataModel getDados() throws NegocioException {
      ConsultarFuncionario solicitacao = new ConsultarFuncionario();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaConsultarFuncionario resposta = (RespostaConsultarFuncionario) service.executa(solicitacao);

      return new PaginacaoDataModel(resposta.getDados(), resposta.getTotalNumeroLinhas().intValue());
   }

   private void popularVo(ConsultarFuncionario solicitacao) {
      solicitacao.setMaxPage(getTable().getRows());
      solicitacao.setStartPage(getTable().getFirst());
      solicitacao.setIdTipoFuncionario(this.idTipoFuncionario);
   }

   public int getLinhas() {
      return 17;
   }

   public Long getIdTipoFuncionario() {
      return idTipoFuncionario;
   }

   public void setIdTipoFuncionario(Long idTipoFuncionario) {
      this.idTipoFuncionario = idTipoFuncionario;
   }

}
