package br.ufrn.ct.cronos.web.permutarturma;

import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.listarturmasdistribuidas.vo.DadosListarTurmasDistribuidas;
import br.ufrn.ct.cronos.listarturmasdistribuidas.vo.ListarTurmasDistribuidas;
import br.ufrn.ct.cronos.listarturmasdistribuidas.vo.RespostaListarTurmasDistribuidas;
import br.ufrn.ct.cronos.permutarturma.vo.DadosObterTurmaCompleta;
import br.ufrn.ct.cronos.permutarturma.vo.ObterTurmaCompleta;
import br.ufrn.ct.cronos.permutarturma.vo.PermutarTurma;
import br.ufrn.ct.cronos.permutarturma.vo.RespostaObterTurmaCompleta;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class PermutarTurmaFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
   private Long idTurma1;
   private Long idTurma2;
   private Long idPeriodo;
   private Long idPredio;
   private DadosObterTurmaCompleta turma1;
   private DadosObterTurmaCompleta turma2;

   public String permutar() throws NegocioException {
      PermutarTurma solicitacao = new PermutarTurma();
      solicitacao.setIdTurma1(this.idTurma1);
      solicitacao.setIdTurma2(this.idTurma2);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);

      this.idTurma1 = new Long(0);
      this.idTurma2 = new Long(0);
      this.turma1 = new DadosObterTurmaCompleta();
      this.turma2 = new DadosObterTurmaCompleta();

      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");

      return null;
   }

   public String atualizarTurma1() throws NegocioException {
      ObterTurmaCompleta solicitacao = new ObterTurmaCompleta();
      solicitacao.setId(this.idTurma1);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterTurmaCompleta resposta = (RespostaObterTurmaCompleta) service.executa(solicitacao);
      this.turma1 = resposta.getObjeto();
      return null;
   }

   public String atualizarTurma2() throws NegocioException {
      ObterTurmaCompleta solicitacao = new ObterTurmaCompleta();
      solicitacao.setId(this.idTurma2);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterTurmaCompleta resposta = (RespostaObterTurmaCompleta) service.executa(solicitacao);
      this.turma2 = resposta.getObjeto();
      return null;
   }

   public List<SelectItem> getTurmasPorPeriodoEPredio() throws NegocioException {
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

   public String limparDadosTurmas() throws NegocioException {
      this.turma1 = new DadosObterTurmaCompleta();
      this.turma2 = new DadosObterTurmaCompleta();
      return null;
   }

   /**
    * Recupera o valor do atributo idTurma1
    * @return o idTurma1
    */
   public Long getIdTurma1() {
      return idTurma1;
   }

   /**
    * Atribui o novo valor de idTurma1
    * @param idTurma1 idTurma1 que será atribuído
    */
   public void setIdTurma1(Long idTurma1) {
      this.idTurma1 = idTurma1;
   }

   /**
    * Recupera o valor do atributo idTurma2
    * @return o idTurma2
    */
   public Long getIdTurma2() {
      return idTurma2;
   }

   /**
    * Atribui o novo valor de idTurma2
    * @param idTurma2 idTurma2 que será atribuído
    */
   public void setIdTurma2(Long idTurma2) {
      this.idTurma2 = idTurma2;
   }

   /**
    * Recupera o valor do atributo turma1
    * @return o turma1
    */
   public DadosObterTurmaCompleta getTurma1() {
      return turma1;
   }

   /**
    * Atribui o novo valor de turma1
    * @param turma1 turma1 que será atribuído
    */
   public void setTurma1(DadosObterTurmaCompleta turma1) {
      this.turma1 = turma1;
   }

   /**
    * Recupera o valor do atributo turma2
    * @return o turma2
    */
   public DadosObterTurmaCompleta getTurma2() {
      return turma2;
   }

   /**
    * Atribui o novo valor de turma2
    * @param turma2 turma2 que será atribuído
    */
   public void setTurma2(DadosObterTurmaCompleta turma2) {
      this.turma2 = turma2;
   }

   /**
    * Recupera o valor do atributo idPeriodo
    * @return o idPeriodo
    */
   public Long getIdPeriodo() {
      return idPeriodo;
   }

   /**
    * Atribui o novo valor de idPeriodo
    * @param idPeriodo idPeriodo que será atribuído
    */
   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
   }

   /**
    * Recupera o valor do atributo idPredio
    * @return o idPredio
    */
   public Long getIdPredio() {
      return idPredio;
   }

   /**
    * Atribui o novo valor de idPredio
    * @param idPredio idPredio que será atribuído
    */
   public void setIdPredio(Long idPredio) {
      this.idPredio = idPredio;
   }

}
