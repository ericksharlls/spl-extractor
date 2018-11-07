package br.ufrn.ct.cronos.web.listarfuncionariodocente;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.listarfuncionariodocente.vo.DadosListarFuncionarioDocente;
import br.ufrn.ct.cronos.listarfuncionariodocente.vo.ListarFuncionarioDocente;
import br.ufrn.ct.cronos.listarfuncionariodocente.vo.RespostaListarFuncionarioDocente;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class ListarFuncionarioDocenteFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;

   public ListarFuncionarioDocenteFormBean() throws NegocioException {
      super();
   }

   public List<SelectItem> getDados() throws NegocioException {
      List<SelectItem> itens = new ArrayList<SelectItem>();
      ListarFuncionarioDocente solicitacao = new ListarFuncionarioDocente();
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaListarFuncionarioDocente resposta = (RespostaListarFuncionarioDocente) service.executa(solicitacao);
      List<DadosListarFuncionarioDocente> dados = resposta.getDados();
      if (!dados.isEmpty()) {
         itens = new ArrayList<SelectItem>(dados.size());
         for (DadosListarFuncionarioDocente p : dados) {
            itens.add(new SelectItem(p.getId(), p.getNome()));
         }
      }
      return itens;
   }

}
