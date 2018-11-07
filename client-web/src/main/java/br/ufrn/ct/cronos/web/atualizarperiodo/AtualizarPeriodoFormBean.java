package br.ufrn.ct.cronos.web.atualizarperiodo;

import java.util.Date;
import java.util.Locale;
import br.ufrn.ct.cronos.atualizarperiodo.vo.AtualizarPeriodo;
import br.ufrn.ct.cronos.obtersemestreletivo.vo.ObterSemestreLetivo;
import br.ufrn.ct.cronos.obtersemestreletivo.vo.RespostaObterSemestreLetivo;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class AtualizarPeriodoFormBean extends AbstractFormBean {
   
   private static final long serialVersionUID = 1L;
      
   private Long id;
   private String nome;
   private String descricao;
   private Date dataInicio;
   private Date dataTermino;
   private Boolean isPeriodoLetivo;
   private Integer ano, numeroPeriodo;
   private Locale locale = new Locale("pt", "BR");
      
     public AtualizarPeriodoFormBean() {
      super();
   }
      
   public String atualizarPeriodo() throws NegocioException {
      AtualizarPeriodo solicitacao = new AtualizarPeriodo();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);
      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");
      return ControlNavigationFormBean.pageConsultarPeriodo();
   }
      
   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarPeriodo();
   }
      
   public void obterPeriodo() throws NegocioException {
      ObterSemestreLetivo solicitacao = new ObterSemestreLetivo();
      solicitacao.setId(this.id);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterSemestreLetivo resposta = (RespostaObterSemestreLetivo) service.executa(solicitacao);
      this.nome = resposta.getObjeto().getNome();
      this.id = resposta.getObjeto().getId();
      this.dataInicio = resposta.getObjeto().getDataInicio();
      this.dataTermino = resposta.getObjeto().getDataTermino();
      this.descricao = resposta.getObjeto().getDescricao();
      this.isPeriodoLetivo = resposta.getObjeto().getIsSemestreLetivo();
      this.ano = resposta.getObjeto().getAno();
      this.numeroPeriodo = resposta.getObjeto().getNumeroPeriodo();
   }
      
   private void popularVo(AtualizarPeriodo solicitacao) {
      solicitacao.setNome(this.nome);
      solicitacao.setId(this.id);
      solicitacao.setDataInicio(this.dataInicio);
      solicitacao.setDataTermino(this.dataTermino);
      solicitacao.setDescricao(this.descricao);
      solicitacao.setIsPeriodoLetivo(this.isPeriodoLetivo);
      solicitacao.setAno(this.ano);
      solicitacao.setNumeroPeriodo(this.numeroPeriodo);
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

   public void setLocale(Locale locale) {
      this.locale = locale;
   }

   public Locale getLocale() {
      return this.locale;
   }

   public Boolean getIsPeriodoLetivo() {
      return isPeriodoLetivo;
   }
      public void setIsPeriodoLetivo(Boolean isPeriodoLetivo) {
      this.isPeriodoLetivo = isPeriodoLetivo;
   }

      public Integer getAno() {
         return ano;
      }

      public void setAno(Integer ano) {
         this.ano = ano;
      }

      public Integer getNumeroPeriodo() {
         return numeroPeriodo;
      }

      public void setNumeroPeriodo(Integer numeroPeriodo) {
         this.numeroPeriodo = numeroPeriodo;
      }

}
