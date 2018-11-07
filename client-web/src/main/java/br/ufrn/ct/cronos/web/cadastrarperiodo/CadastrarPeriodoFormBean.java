package br.ufrn.ct.cronos.web.cadastrarperiodo;

import java.util.Date;
import java.util.Locale;
import br.ufrn.ct.cronos.cadastrarperiodo.vo.CadastrarPeriodo;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class CadastrarPeriodoFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
      
   private String nome;
   private String descricao;
   private Boolean isPeriodoLetivo;
   private Date dataInicio;
   private Date dataTermino;
   private Integer ano, numeroPeriodo;
   private Locale locale = new Locale("pt", "BR");

   public CadastrarPeriodoFormBean() {
      super();
   }

   public String cadastrarPeriodo() throws NegocioException {
      CadastrarPeriodo solicitacao = new CadastrarPeriodo();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);
      limpar();
      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");

      return null;
   }
      
   private void popularVo(CadastrarPeriodo solicitacao) {
      solicitacao.setNome(this.nome);
      solicitacao.setDataInicio(this.dataInicio);
      solicitacao.setDataTermino(this.dataTermino);
      solicitacao.setDescricao(this.descricao);
      solicitacao.setPeriodoLetivo(this.isPeriodoLetivo);
      solicitacao.setAno(this.ano);
      solicitacao.setNumeroPeriodo(this.numeroPeriodo);
   }
      
   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarPeriodo();
   }
      
   private void limpar() {
      this.nome = null;
      this.descricao = null;
      this.dataInicio = null;
      this.dataTermino = null;
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
