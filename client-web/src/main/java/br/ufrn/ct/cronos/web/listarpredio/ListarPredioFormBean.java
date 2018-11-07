package br.ufrn.ct.cronos.web.listarpredio;
 
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.listarpredio.vo.DadosListarPredio;
import br.ufrn.ct.cronos.listarpredio.vo.ListarPredio;
import br.ufrn.ct.cronos.listarpredio.vo.RespostaListarPredio;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class ListarPredioFormBean extends AbstractFormBean {

		private static final long serialVersionUID = 1L;
		
		public ListarPredioFormBean() throws NegocioException {
			super();
		}
		
		public List<SelectItem> getDados() throws NegocioException {
			List<SelectItem> itens = new ArrayList<SelectItem>();
			ListarPredio solicitacao = new ListarPredio();
			Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
			RespostaListarPredio resposta = (RespostaListarPredio)service.executa(solicitacao);
			List<DadosListarPredio> dados = resposta.getDados();
			if (!dados.isEmpty()) {
				itens = new ArrayList<SelectItem>(dados.size());
				for (DadosListarPredio p : dados) {
					itens.add(new SelectItem(p.getId(), p.getNome()));
				}
			}
			return itens;
		}
		
}
