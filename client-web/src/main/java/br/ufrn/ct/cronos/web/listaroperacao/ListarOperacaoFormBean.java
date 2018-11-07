package br.ufrn.ct.cronos.web.listaroperacao;

import br.ufrn.ct.cronos.listaroperacao.vo.DadosListarOperacao;
import br.ufrn.ct.cronos.listaroperacao.vo.ListarOperacao;
import br.ufrn.ct.cronos.listaroperacao.vo.RespostaListarOperacao;
import dev.home.componente.service.Service;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;

public class ListarOperacaoFormBean extends AbstractFormBean {
	
		private static final long serialVersionUID = 1L;
	
		public ListarOperacaoFormBean() {
			super();
		}
		
		public List<SelectItem> getDados() throws NegocioException {
			List<SelectItem> itens = new ArrayList<SelectItem>();
			ListarOperacao solicitacao = new ListarOperacao();
			Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
			RespostaListarOperacao resposta = (RespostaListarOperacao) service.executa(solicitacao);
			List<DadosListarOperacao> dados = resposta.getDados();
			if (!dados.isEmpty()) {
				itens = new ArrayList<SelectItem>(dados.size());
				for (DadosListarOperacao d : dados) {
					itens.add(new SelectItem(d.getId(), d.getNome()));
				}
			}
			return itens;
		}
	
}

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.listaroperacao.ListarOperacaoFormBean
 * JD-Core Version:    0.6.2
 */