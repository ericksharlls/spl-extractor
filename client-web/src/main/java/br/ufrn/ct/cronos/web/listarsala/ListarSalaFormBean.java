package br.ufrn.ct.cronos.web.listarsala;
 
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.listarsala.vo.DadosListarSala;
import br.ufrn.ct.cronos.listarsala.vo.ListarSala;
import br.ufrn.ct.cronos.listarsala.vo.RespostaListarSala;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;
 
public class ListarSalaFormBean extends AbstractFormBean {
	
		private static final long serialVersionUID = 1L;
		
		public ListarSalaFormBean() {
			super();
		}
		
		public List<SelectItem> getDados() throws NegocioException {
			List<SelectItem> itens = new ArrayList<SelectItem>();
			ListarSala solicitacao = new ListarSala();
			Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
			RespostaListarSala resposta = (RespostaListarSala)service.executa(solicitacao);
			List<DadosListarSala> dados = resposta.getDados();
			if (!dados.isEmpty()) {
				itens = new ArrayList<SelectItem>(dados.size());
				for (DadosListarSala d : dados) {
					itens.add(new SelectItem(d.getId(), d.getNome()));
				}
			}
			return itens;
		}

}

