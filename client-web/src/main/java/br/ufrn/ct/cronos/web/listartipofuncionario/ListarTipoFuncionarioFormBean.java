package br.ufrn.ct.cronos.web.listartipofuncionario;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.listartipofuncionario.vo.DadosListarTipoFuncionario;
import br.ufrn.ct.cronos.listartipofuncionario.vo.ListarTipoFuncionario;
import br.ufrn.ct.cronos.listartipofuncionario.vo.RespostaListarTipoFuncionario;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;


public class ListarTipoFuncionarioFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;

   public ListarTipoFuncionarioFormBean() {
      super();
   }

   public List<SelectItem> getDados() throws NegocioException {
      List<SelectItem> itens = new ArrayList<SelectItem>();
      ListarTipoFuncionario solicitacao = new ListarTipoFuncionario();
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaListarTipoFuncionario resposta = (RespostaListarTipoFuncionario) service.executa(solicitacao);
      List<DadosListarTipoFuncionario> dados = resposta.getDados();
      if (!dados.isEmpty()) {
         itens = new ArrayList<SelectItem>(dados.size());
         for (DadosListarTipoFuncionario d : dados) {
            itens.add(new SelectItem(d.getId(), d.getNome()));
         }
      }
      return itens;
   }

}

