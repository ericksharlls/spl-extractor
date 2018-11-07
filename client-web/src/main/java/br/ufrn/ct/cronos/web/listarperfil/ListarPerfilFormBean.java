package br.ufrn.ct.cronos.web.listarperfil;
 
import br.ufrn.ct.cronos.listarperfil.vo.DadosListarPerfil;
import br.ufrn.ct.cronos.listarperfil.vo.ListarPerfil;
import br.ufrn.ct.cronos.listarperfil.vo.RespostaListarPerfil;
import dev.home.componente.service.Service;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;

public class ListarPerfilFormBean extends AbstractFormBean {
	
		private static final long serialVersionUID = 1L;
		
		public ListarPerfilFormBean() throws NegocioException {
			super();
		}

		public List<SelectItem> getDados() throws NegocioException {
			List<SelectItem> itens = new ArrayList<SelectItem>();
			ListarPerfil solicitacao = new ListarPerfil();
			Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
			RespostaListarPerfil resposta = (RespostaListarPerfil) service.executa(solicitacao);
			List<DadosListarPerfil> dados = resposta.getDados();
			if (!dados.isEmpty()) {
				itens = new ArrayList<SelectItem>(dados.size());
				for (DadosListarPerfil p : dados) {
					itens.add(new SelectItem(p.getId(), p.getNome()));
				}
			}
			return itens;
		}
}

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.listarperfil.ListarPerfilFormBean
 * JD-Core Version:    0.6.2
 */