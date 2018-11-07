package br.ufrn.ct.cronos.web.consultaragendamento;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.consultaragendamento.vo.ConsultarAgendamento;
import br.ufrn.ct.cronos.consultaragendamento.vo.DadosConsultarAgendamento;
import br.ufrn.ct.cronos.consultaragendamento.vo.RespostaConsultarAgendamento;
import br.ufrn.ct.cronos.obteragendamento.vo.ObterAgendamento;
import br.ufrn.ct.cronos.obteragendamento.vo.RespostaObterAgendamento;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractConsultarFormBean;
import dev.home.componente.web.infra.paginacao.PaginacaoDataModel;

public class ConsultarAgendamentoFormBean extends AbstractConsultarFormBean {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   private String interessado, motivo;
   private Date dataInicial;
   private Date dataFinal;
   private int scroller;
   private Long idPeriodo, idPredio;
   private DadosConsultarAgendamento agendamento;
   private Locale locale = new Locale("pt", "BR");

   private List<SelectItem> datasAgendamento;

   public ConsultarAgendamentoFormBean() {
      super();
      this.agendamento = new DadosConsultarAgendamento();
      this.datasAgendamento = new ArrayList<SelectItem>(0);
   }

   public void zerarScroller(ActionEvent event) {
      getTable().setFirst(0);
      this.scroller = 1;
   }

   public DataModel getDados() throws NegocioException {
      ConsultarAgendamento solicitacao = new ConsultarAgendamento();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaConsultarAgendamento resposta = (RespostaConsultarAgendamento) service.executa(solicitacao);
      return new PaginacaoDataModel(resposta.getDados(), resposta.getTotalNumeroLinhas());
   }
   
   private void popularVo(ConsultarAgendamento solicitacao) {
      solicitacao.setInteressado(this.interessado);
      solicitacao.setMotivo(this.motivo);
      solicitacao.setDataInicial(this.dataInicial);
      solicitacao.setDataFinal(this.dataFinal);
      solicitacao.setIdPeriodo(this.idPeriodo);
      solicitacao.setIdPredio(this.idPredio);
      solicitacao.setMaxPage(getTable().getRows());
      solicitacao.setStartPage(getTable().getFirst());
   }

   @Override
   public int getLinhas() {
      return 14;
   }

   /**
    * Recupera o valor do atributo interessado
    * @return o interessado
    */
   public String getInteressado() {
      return interessado;
   }

   /**
    * Atribui o novo valor de interessado
    * @param interessado interessado que será atribuído
    */
   public void setInteressado(String interessado) {
      this.interessado = interessado;
   }

   /**
    * Recupera o valor do atributo motivo
    * @return o motivo
    */
   public String getMotivo() {
      return motivo;
   }

   /**
    * Atribui o novo valor de motivo
    * @param motivo motivo que será atribuído
    */
   public void setMotivo(String motivo) {
      this.motivo = motivo;
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

   /**
    * Recupera o valor do atributo dataInicial
    * @return o dataInicial
    */
   public Date getDataInicial() {
      return dataInicial;
   }

   /**
    * Atribui o novo valor de dataInicial
    * @param dataInicial dataInicial que será atribuído
    */
   public void setDataInicial(Date dataInicial) {
      this.dataInicial = dataInicial;
   }

   /**
    * Recupera o valor do atributo dataFinal
    * @return o dataFinal
    */
   public Date getDataFinal() {
      return dataFinal;
   }

   /**
    * Atribui o novo valor de dataFinal
    * @param dataFinal dataFinal que será atribuído
    */
   public void setDataFinal(Date dataFinal) {
      this.dataFinal = dataFinal;
   }

   /**
    * Recupera o valor do atributo locale
    * @return o locale
    */
   public Locale getLocale() {
      return locale;
   }

   /**
    * Atribui o novo valor de locale
    * @param locale locale que será atribuído
    */
   public void setLocale(Locale locale) {
      this.locale = locale;
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

   public DadosConsultarAgendamento getAgendamento() {
      return agendamento;
   }

   public void setAgendamento(DadosConsultarAgendamento ag) throws NegocioException {
      ObterAgendamento solicitacao = new ObterAgendamento();
      solicitacao.setId(ag.getIdAgendamento());
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterAgendamento resposta = (RespostaObterAgendamento) service.executa(solicitacao);
      this.agendamento.setIdAgendamento(resposta.getObjeto().getId());
      this.agendamento.setInteressado(resposta.getObjeto().getInteressado());
      this.agendamento.setMotivo(resposta.getObjeto().getMotivo());
      this.agendamento.setSala(resposta.getObjeto().getSala());
      this.agendamento.setTelefone(resposta.getObjeto().getTelefone());
      this.datasAgendamento = new ArrayList<SelectItem>(0);
      for (String string : resposta.getObjeto().getDatas()) {
         SelectItem item = new SelectItem(string);
         this.datasAgendamento.add(item);
      }
   }

   public List<SelectItem> getDatasAgendamento() {
      return datasAgendamento;
   }

   public void setDatasAgendamento(List<SelectItem> datasAgendamento) {
      this.datasAgendamento = datasAgendamento;
   }

}
