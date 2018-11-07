package br.ufrn.ct.cronos.web.listardepartamento;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.listardepartamento.vo.DadosListarDepartamento;
import br.ufrn.ct.cronos.listardepartamento.vo.ListarDepartamento;
import br.ufrn.ct.cronos.listardepartamento.vo.RespostaListarDepartamento;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;


public class ListarDepartamentoFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;

   public ListarDepartamentoFormBean() {
      super();
   }

   public List<SelectItem> getDados() throws NegocioException {
      List<SelectItem> itens = new ArrayList<SelectItem>();
      ListarDepartamento solicitacao = new ListarDepartamento();
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaListarDepartamento resposta = (RespostaListarDepartamento) service.executa(solicitacao);
      List<DadosListarDepartamento> dados = resposta.getDados();
      if (!dados.isEmpty()) {
         itens = new ArrayList<SelectItem>(dados.size());
         for (DadosListarDepartamento d : dados) {
            itens.add(new SelectItem(d.getId(), d.getNome()));
         }
      }
      return itens;
   }

}

