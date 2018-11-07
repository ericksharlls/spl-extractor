/*    */ package br.ufrn.ct.cronos.web.atualizarpredio;
/*    */ 
/*    */ import br.ufrn.ct.cronos.atualizarpredio.vo.AtualizarPredio;
/*    */ import br.ufrn.ct.cronos.obterpredio.vo.DadosObterPredio;
/*    */ import br.ufrn.ct.cronos.obterpredio.vo.ObterPredio;
/*    */ import br.ufrn.ct.cronos.obterpredio.vo.RespostaObterPredio;
/*    */ import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
/*    */ import dev.home.componente.service.Service;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ import dev.home.componente.service.util.ServiceUtils;
/*    */ import dev.home.componente.web.infra.AbstractFormBean;
/*    */ 
/*    */ public class AtualizarPredioFormBean extends AbstractFormBean
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */   private String nome;
/*    */   private String descricao;
/*    */ 
/*    */   public String atualizarPredio()
/*    */     throws NegocioException
/*    */   {
/* 26 */     AtualizarPredio solicitacao = new AtualizarPredio();
/* 27 */     popularVo(solicitacao);
/* 28 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/* 29 */     service.executa(solicitacao);
/* 30 */     return ControlNavigationFormBean.pageConsultarPredio();
/*    */   }
/*    */ 
/*    */   public String cancelar() {
/* 34 */     return ControlNavigationFormBean.pageConsultarPredio();
/*    */   }
/*    */ 
/*    */   public void obterPredio() throws NegocioException {
/* 38 */     ObterPredio solicitacao = new ObterPredio();
/* 39 */     solicitacao.setId(this.id);
/* 40 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/* 41 */     RespostaObterPredio resposta = (RespostaObterPredio)service.executa(solicitacao);
/* 42 */     this.nome = ((DadosObterPredio)resposta.getObjeto()).getNome();
/* 43 */     this.id = ((DadosObterPredio)resposta.getObjeto()).getId();
/* 44 */     this.descricao = ((DadosObterPredio)resposta.getObjeto()).getDescricao();
/*    */   }
/*    */ 
/*    */   private void popularVo(AtualizarPredio solicitacao) {
/* 48 */     solicitacao.setNome(this.nome);
/* 49 */     solicitacao.setId(this.id);
/* 50 */     solicitacao.setDescricao(this.descricao);
/*    */   }
/*    */ 
/*    */   public Long getId() {
/* 54 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 58 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getNome() {
/* 62 */     return this.nome;
/*    */   }
/*    */ 
/*    */   public void setNome(String nome) {
/* 66 */     this.nome = nome;
/*    */   }
/*    */ 
/*    */   public String getDescricao() {
/* 70 */     return this.descricao;
/*    */   }
/*    */ 
/*    */   public void setDescricao(String descricao) {
/* 74 */     this.descricao = descricao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.atualizarpredio.AtualizarPredioFormBean
 * JD-Core Version:    0.6.2
 */