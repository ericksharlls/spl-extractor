package br.ufrn.ct.cronos.web.removerperiodo;

import java.util.Date;
import br.ufrn.ct.cronos.obtersemestreletivo.vo.ObterSemestreLetivo;
import br.ufrn.ct.cronos.obtersemestreletivo.vo.RespostaObterSemestreLetivo;
import br.ufrn.ct.cronos.removerperiodo.vo.RemoverPeriodo;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class RemoverPeriodoFormBean extends AbstractFormBean {
      
   private static final long serialVersionUID = 1L;
      
   private Long id;
   private String nome;
   private String descricao;
   private Boolean isPeriodoLetivo;
   private Date dataInicio;
   private Date dataTermino;

   public RemoverPeriodoFormBean() {
      super();
   }
      
   public String removerPeriodo() throws NegocioException {
      RemoverPeriodo solicitacao = new RemoverPeriodo();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);
      return ControlNavigationFormBean.pageConsultarPeriodo();
   }

   public void obterPeriodo() throws NegocioException {
      ObterSemestreLetivo solicitacao = new ObterSemestreLetivo();
      solicitacao.setId(this.id);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterSemestreLetivo resposta = (RespostaObterSemestreLetivo) service.executa(solicitacao);
      this.id = resposta.getObjeto().getId();
      this.nome = resposta.getObjeto().getNome();
      this.descricao = resposta.getObjeto().getNome();
      this.dataInicio = resposta.getObjeto().getDataInicio();
      this.dataTermino = resposta.getObjeto().getDataTermino();
      this.isPeriodoLetivo = resposta.getObjeto().getIsSemestreLetivo();
   }
 
   private void popularVo(RemoverPeriodo solicitacao) {
      solicitacao.setId(this.id);
   }
      
   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarPeriodo();
   }
      
   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getDescricao() {
      return this.descricao;
   }

   public void setDescricao(String descricao) {
      this.descricao = descricao;
   }

   public Date getDataInicio() {
      return this.dataInicio;
   }

   public void setDataInicio(Date dataInicio) {
      this.dataInicio = dataInicio;
   }
      
   public Date getDataTermino() {
      return this.dataTermino;
   }
      
   public void setDataTermino(Date dataTermino) {
      this.dataTermino = dataTermino;
   }

   public Boolean getIsPeriodoLetivo() {
      return isPeriodoLetivo;
   }

   public void setIsPeriodoLetivo(Boolean isPeriodoLetivo) {
      this.isPeriodoLetivo = isPeriodoLetivo;
   }
      
}
