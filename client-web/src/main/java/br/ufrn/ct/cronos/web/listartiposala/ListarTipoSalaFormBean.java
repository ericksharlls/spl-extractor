package br.ufrn.ct.cronos.web.listartiposala;

import br.ufrn.ct.cronos.listartiposala.vo.DadosListarTipoSala;
import br.ufrn.ct.cronos.listartiposala.vo.ListarTipoSala;
import br.ufrn.ct.cronos.listartiposala.vo.RespostaListarTipoSala;
import dev.home.componente.service.Service;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;

public class ListarTipoSalaFormBean extends AbstractFormBean {

		private static final long serialVersionUID = 1L;
		
		public ListarTipoSalaFormBean() throws NegocioException {
			super();
		}

 
		public List<SelectItem> getDados() throws NegocioException {
			List<SelectItem> itens = new ArrayList<SelectItem>();
			ListarTipoSala solicitacao = new ListarTipoSala();
			Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
			RespostaListarTipoSala resposta = (RespostaListarTipoSala) service.executa(solicitacao);
			List<DadosListarTipoSala> dados = resposta.getDados();
			if (!dados.isEmpty()) {
				itens = new ArrayList<SelectItem>(dados.size());
				for (DadosListarTipoSala d : dados) {
					itens.add(new SelectItem(d.getId(), d.getNome()));
				}
			}
			return itens;
		}

}

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.listartiposala.ListarTipoSalaFormBean
 * JD-Core Version:    0.6.2
 */