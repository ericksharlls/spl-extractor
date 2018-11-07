package br.ufrn.ct.cronos.web.listarfuncionario;
 
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.listarfuncionario.vo.DadosListarFuncionario;
import br.ufrn.ct.cronos.listarfuncionario.vo.ListarFuncionario;
import br.ufrn.ct.cronos.listarfuncionario.vo.RespostaListarFuncionario;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class ListarFuncionarioFormBean extends AbstractFormBean {
		
   private static final long serialVersionUID = 1L;
		
   public ListarFuncionarioFormBean() throws NegocioException {
      super();
   }
		
   public List<SelectItem> getDados() throws NegocioException {
      List<SelectItem> itens = new ArrayList<SelectItem>();
      ListarFuncionario solicitacao = new ListarFuncionario();
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaListarFuncionario resposta = (RespostaListarFuncionario) service.executa(solicitacao);
      List<DadosListarFuncionario> dados = resposta.getDados();
      if (!dados.isEmpty()) {
         itens = new ArrayList<SelectItem>(dados.size());
         for (DadosListarFuncionario p : dados) {
            itens.add(new SelectItem(p.getId(), p.getNome()));
			}
		}
      return itens;
   }

}
