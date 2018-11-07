package br.ufrn.ct.cronos.web.listarturmadistribuidas;
 
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.listarturmasdistribuidas.vo.DadosListarTurmasDistribuidas;
import br.ufrn.ct.cronos.listarturmasdistribuidas.vo.ListarTurmasDistribuidas;
import br.ufrn.ct.cronos.listarturmasdistribuidas.vo.RespostaListarTurmasDistribuidas;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;
 
public class ListarTurmasDistribuidasFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;

   public ListarTurmasDistribuidasFormBean() {
      super();
   }
 
   public List<SelectItem> getDados() throws NegocioException {
      List<SelectItem> itens = new ArrayList<SelectItem>();
      ListarTurmasDistribuidas solicitacao = new ListarTurmasDistribuidas();

      solicitacao.setIdPeriodo(new Long(0));
      solicitacao.setIdPredio(new Long(0));

      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaListarTurmasDistribuidas resposta = (RespostaListarTurmasDistribuidas) service.executa(solicitacao);
      List<DadosListarTurmasDistribuidas> dados = resposta.getDados();
      if (!dados.isEmpty()) {
         itens = new ArrayList<SelectItem>(dados.size());
         for (DadosListarTurmasDistribuidas d : dados) {
            itens.add(new SelectItem(d.getId(), d.getNome()));
			}
		}
      return itens;
   }
		
}
