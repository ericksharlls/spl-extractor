package br.ufrn.ct.cronos.web.cadastrarferiado;

import java.util.Date;
import java.util.Locale;
import br.ufrn.ct.cronos.cadastrarferiado.vo.CadastrarFeriado;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class CadastrarFeriadoFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;

   private String descricao;
   private Date data;
   private Long idPeriodo;
   private Locale locale = new Locale("pt", "BR");

   public CadastrarFeriadoFormBean() {
      super();
   }

   public String cadastrarFeriado() throws NegocioException {
      CadastrarFeriado solicitacao = new CadastrarFeriado();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);
      limpar();
      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");

      return null;
   }

   private void popularVo(CadastrarFeriado solicitacao) {
      solicitacao.setData(this.data);
      solicitacao.setDescricao(this.descricao);
      solicitacao.setIdPeriodo(this.idPeriodo);
   }

   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarFeriado();
   }

   private void limpar() {
      this.descricao = null;
      this.data = null;
      this.idPeriodo = null;
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
