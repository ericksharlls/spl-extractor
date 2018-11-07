package br.ufrn.ct.cronos.web.removerferiado;

import java.util.Date;
import br.ufrn.ct.cronos.obterferiado.vo.ObterFeriado;
import br.ufrn.ct.cronos.obterferiado.vo.RespostaObterFeriado;
import br.ufrn.ct.cronos.removerferiado.vo.RemoverFeriado;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class RemoverFeriadoFormBean extends AbstractFormBean {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   private Long id;
   private String descricao, nomePeriodo;
   private Date data;

   public RemoverFeriadoFormBean() {
      super();
   }

   public String removerFeriado() throws NegocioException {
      RemoverFeriado solicitacao = new RemoverFeriado();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);
      return ControlNavigationFormBean.pageConsultarFeriado();
   }

   public void obterFeriado() throws NegocioException {
      ObterFeriado solicitacao = new ObterFeriado();
      solicitacao.setId(this.id);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterFeriado resposta = (RespostaObterFeriado) service.executa(solicitacao);
      this.id = resposta.getObjeto().getId();
      this.descricao = resposta.getObjeto().getDescricao();
      this.data = resposta.getObjeto().getData();
      this.nomePeriodo = resposta.getObjeto().getNomePeriodo();
   }

   private void popularVo(RemoverFeriado solicitacao) {
      solicitacao.setId(this.id);
   }

   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarFeriado();
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

   public String getNomePeriodo() {
      return nomePeriodo;
   }

   public void setNomePeriodo(String nomePeriodo) {
      this.nomePeriodo = nomePeriodo;
   }

}
