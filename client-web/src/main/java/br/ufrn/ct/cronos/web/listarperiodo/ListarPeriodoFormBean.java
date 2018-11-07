package br.ufrn.ct.cronos.web.listarperiodo;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.listarperiodo.vo.DadosListarPeriodo;
import br.ufrn.ct.cronos.listarperiodo.vo.ListarPeriodo;
import br.ufrn.ct.cronos.listarperiodo.vo.RespostaListarPeriodo;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class ListarPeriodoFormBean extends AbstractFormBean {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   public ListarPeriodoFormBean() {
      super();
   }

   public List<SelectItem> getDados() throws NegocioException {
      List<SelectItem> itens = new ArrayList<SelectItem>();
      ListarPeriodo solicitacao = new ListarPeriodo();
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaListarPeriodo resposta = (RespostaListarPeriodo) service.executa(solicitacao);
      List<DadosListarPeriodo> dados = resposta.getDados();
      if (!dados.isEmpty()) {
         itens = new ArrayList<SelectItem>(dados.size());
         for (DadosListarPeriodo p : dados) {
            itens.add(new SelectItem(p.getId(), p.getNome()));
         }
      }
      return itens;
   }

}
