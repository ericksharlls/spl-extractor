package br.ufrn.ct.cronos.web.atualizarferiado;

import java.util.Date;
import java.util.Locale;
import br.ufrn.ct.cronos.atualizarferiado.vo.AtualizarFeriado;
import br.ufrn.ct.cronos.obterferiado.vo.ObterFeriado;
import br.ufrn.ct.cronos.obterferiado.vo.RespostaObterFeriado;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class AtualizarFeriadoFormBean extends AbstractFormBean {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   private Long id;
   private String descricao;
   private Date data;
   private Long idPeriodo;
   private Locale locale = new Locale("pt", "BR");

   public AtualizarFeriadoFormBean() {
      super();
   }

   public String atualizarFeriado() throws NegocioException {
      AtualizarFeriado solicitacao = new AtualizarFeriado();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);
      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");
      return ControlNavigationFormBean.pageConsultarFeriado();
   }

   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarFeriado();
   }

   public void obterFeriado() throws NegocioException {
      ObterFeriado solicitacao = new ObterFeriado();
      solicitacao.setId(this.id);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterFeriado resposta = (RespostaObterFeriado) service.executa(solicitacao);
      this.id = resposta.getObjeto().getId();
      this.data = resposta.getObjeto().getData();
      this.descricao = resposta.getObjeto().getDescricao();
      this.idPeriodo = resposta.getObjeto().getIdPeriodo();
   }

   private void popularVo(AtualizarFeriado solicitacao) {
      solicitacao.setId(this.id);
      solicitacao.setData(this.data);
      solicitacao.setDescricao(this.descricao);
      solicitacao.setIdPeriodo(this.idPeriodo);
   }


   /**
    * Recupera o valor do atributo id
    * @return o id
    */
   public Long getId() {
      return id;
   }


   /**
    * Atribui o novo valor de id
    * @param id id que será atribuído
    */
   public void setId(Long id) {
      this.id = id;
   }


   /**
    * Recupera o valor do atributo descricao
    * @return o descricao
    */
   public String getDescricao() {
      return descricao;
   }


   /**
    * Atribui o novo valor de descricao
    * @param descricao descricao que será atribuído
    */
   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }


   /**
    * Recupera o valor do atributo data
    * @return o data
    */
   public Date getData() {
      return data;
   }


   /**
    * Atribui o novo valor de data
    * @param data data que será atribuído
    */
   public void setData(Date data) {
      this.data = data;
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

}
