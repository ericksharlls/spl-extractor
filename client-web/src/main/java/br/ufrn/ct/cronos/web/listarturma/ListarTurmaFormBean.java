package br.ufrn.ct.cronos.web.listarturma;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.listarturma.vo.DadosListarTurma;
import br.ufrn.ct.cronos.listarturma.vo.ListarTurma;
import br.ufrn.ct.cronos.listarturma.vo.RespostaListarTurma;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class ListarTurmaFormBean extends AbstractFormBean {

		private static final long serialVersionUID = 1L;
		
		public ListarTurmaFormBean() {
			super();
		}
		
		public List<SelectItem> getDados() throws NegocioException {
			List<SelectItem> itens = new ArrayList<SelectItem>();
			ListarTurma solicitacao = new ListarTurma();
			Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
			RespostaListarTurma resposta = (RespostaListarTurma)service.executa(solicitacao);
			List<DadosListarTurma> dados = resposta.getDados();
			if (!dados.isEmpty()) {
				itens = new ArrayList<SelectItem>(dados.size());
				for (DadosListarTurma d : dados) {
					itens.add(new SelectItem(d.getId(), d.getNome()));
				}
			}
			return itens;
		}
		
}
