package br.ufrn.ct.cronos.web.desalocarturma;
 
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.desalocarturma.vo.DesalocarTurma;
import br.ufrn.ct.cronos.listarturmasdistribuidas.vo.DadosListarTurmasDistribuidas;
import br.ufrn.ct.cronos.listarturmasdistribuidas.vo.ListarTurmasDistribuidas;
import br.ufrn.ct.cronos.listarturmasdistribuidas.vo.RespostaListarTurmasDistribuidas;
import br.ufrn.ct.cronos.permutarturma.vo.DadosObterTurmaCompleta;
import br.ufrn.ct.cronos.permutarturma.vo.ObterTurmaCompleta;
import br.ufrn.ct.cronos.permutarturma.vo.RespostaObterTurmaCompleta;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;
 
public class DesalocarTurmaFormBean extends AbstractFormBean {
   
   private static final long serialVersionUID = 1L;
   private Long idTurma, idPeriodo, idPredio;
   private DadosObterTurmaCompleta turma;
      
   public DesalocarTurmaFormBean() {
      super();
   }
      
   public String desalocar() throws NegocioException {
      DesalocarTurma solicitacao = new DesalocarTurma();
      solicitacao.setIdTurma(getIdTurma());
         
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);
         
      setIdTurma(new Long(0));
      setTurma(new DadosObterTurmaCompleta());
      setIdPeriodo(new Long(0));
      setIdPredio(new Long(0));

      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");
         
      return null;
   }
      
   public String atualizarTurma(ActionEvent e) throws NegocioException {
      ObterTurmaCompleta solicitacao = new ObterTurmaCompleta();
      solicitacao.setId(getIdTurma());
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterTurmaCompleta resposta = (RespostaObterTurmaCompleta) service.executa(solicitacao);
      setTurma(resposta.getObjeto());
      return null;
   }

   public List<SelectItem> getTurmasDistribuidas() throws NegocioException {
      List<SelectItem> itens = new ArrayList<SelectItem>();
      ListarTurmasDistribuidas solicitacao = new ListarTurmasDistribuidas();
      if (this.idPeriodo == null) {
         solicitacao.setIdPeriodo(new Long(0));
      } else {
         solicitacao.setIdPeriodo(this.idPeriodo);
      }
      if (this.idPredio == null) {
         solicitacao.setIdPredio(new Long(0));
      } else {
         solicitacao.setIdPredio(this.idPredio);
      }

      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaListarTurmasDistribuidas resposta = (RespostaListarTurmasDistribuidas) service.executa(solicitacao);
      List<DadosListarTurmasDistribuidas> dados = resposta.getDados();
      if (!dados.isEmpty()) {
         itens = new ArrayList<SelectItem>(dados.size());
         for (DadosListarTurmasDistribuidas d : dados) {
            itens.add(new SelectItem(d.getId(), d.getNome()));
         }
      }
      return itens;
   }

   public String limparDadosTurma() throws NegocioException {
      this.turma = new DadosObterTurmaCompleta();
      return null;
   }
      
   public void setIdTurma(Long idTurma) {
      this.idTurma = idTurma;
   }
      
   public Long getIdTurma() {
      return this.idTurma;
   }
      
   public void setTurma(DadosObterTurmaCompleta turma) {
      this.turma = turma;
   }
 
   public DadosObterTurmaCompleta getTurma() {
      return this.turma;
   }

   public Long getIdPeriodo() {
      return idPeriodo;
   }

   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
   }

   public Long getIdPredio() {
      return idPredio;
   }

   public void setIdPredio(Long idPredio) {
      this.idPredio = idPredio;
   }
      
}
