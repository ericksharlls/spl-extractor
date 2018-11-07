package br.ufrn.ct.cronos.web.consultarturmaporcodigoedocenteformbean;

import javax.faces.model.DataModel;
import br.ufrn.ct.cronos.consultarturmaporcodigoedocente.vo.ConsultarTurmaPorCodigoEDocente;
import br.ufrn.ct.cronos.consultarturmaporcodigoedocente.vo.RespostaConsultarTurmaPorCodigoEDocente;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractConsultarFormBean;
import dev.home.componente.web.infra.paginacao.PaginacaoDataModel;

public class ConsultarTurmaPorCodigoEDocenteFormBean extends AbstractConsultarFormBean {

   private static final long serialVersionUID = 1L;
		
   private String codigo, docente, nomeDisciplina;
   private Long idPeriodo;
   private int scroller;
		
   public ConsultarTurmaPorCodigoEDocenteFormBean() throws NegocioException {
      super();
      this.idPeriodo = new Long(0);
      this.docente = new String("");
   }

   public String pesquisar() {
      getTable().setFirst(0);
      this.scroller = 1;
      return null;
   }
 
   /*
    * Esse metodo zerarScroller era chamado na pagina em: <h:commandButton id="consultar" value="Pesquisar turmas"
    * styleClass="submitButton"> <a4j:support event="onclick" reRender="turmaTable"
    * actionListener="#{consultarTurmaPorCodigoEDocenteFormBean.zerarScroller}" /> </h:commandButton>
    */
   /*
    * public void zerarScroller(ActionEvent event) { getTable().setFirst(0); this.scroller = 1; }
    */
		
   public DataModel getDados() throws NegocioException {
      ConsultarTurmaPorCodigoEDocente solicitacao = new ConsultarTurmaPorCodigoEDocente();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaConsultarTurmaPorCodigoEDocente resposta = (RespostaConsultarTurmaPorCodigoEDocente) service.executa(solicitacao);
      return new PaginacaoDataModel(resposta.getDados(), resposta.getTotalNumeroLinhas());
   }

   private void popularVo(ConsultarTurmaPorCodigoEDocente solicitacao) {
      solicitacao.setIdPeriodo(this.idPeriodo);
      solicitacao.setCodigo(this.codigo);
      solicitacao.setDocente(this.docente);
      solicitacao.setNomeDisciplina(this.nomeDisciplina);
      solicitacao.setMaxPage(getTable().getRows());
      solicitacao.setStartPage(getTable().getFirst());
   }

   public int getLinhas() {
      return 14;
   }

   /**
    * Recupera o valor do atributo codigo
    * @return o codigo
    */
   public String getCodigo() {
      return codigo;
   }

   /**
    * Atribui o novo valor de codigo
    * @param codigo codigo que será atribuído
    */
   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   /**
    * Recupera o valor do atributo docente
    * @return o docente
    */
   public String getDocente() {
      return docente;
   }

   /**
    * Atribui o novo valor de docente
    * @param docente docente que será atribuído
    */
   public void setDocente(String docente) {
      this.docente = docente;
   }

   /**
    * Recupera o valor do atributo nomeDisciplina
    * @return o nomeDisciplina
    */
   public String getNomeDisciplina() {
      return nomeDisciplina;
   }

   /**
    * Atribui o novo valor de nomeDisciplina
    * @param nomeDisciplina nomeDisciplina que será atribuído
    */
   public void setNomeDisciplina(String nomeDisciplina) {
      this.nomeDisciplina = nomeDisciplina;
   }

   /**
    * Recupera o valor do atributo scroller
    * @return o scroller
    */
   public int getScroller() {
      return scroller;
   }

   /**
    * Atribui o novo valor de scroller
    * @param scroller scroller que será atribuído
    */
   public void setScroller(int scroller) {
      this.scroller = scroller;
   }

   public Long getIdPeriodo() {
      return idPeriodo;
   }

   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
   }

}
