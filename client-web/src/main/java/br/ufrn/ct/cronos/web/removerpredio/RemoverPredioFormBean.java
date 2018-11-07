/*    */ package br.ufrn.ct.cronos.web.removerpredio;
/*    */ 
/*    */ import br.ufrn.ct.cronos.obterpredio.vo.DadosObterPredio;
/*    */ import br.ufrn.ct.cronos.obterpredio.vo.ObterPredio;
/*    */ import br.ufrn.ct.cronos.obterpredio.vo.RespostaObterPredio;
/*    */ import br.ufrn.ct.cronos.removerpredio.vo.RemoverPredio;
/*    */ import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
/*    */ import dev.home.componente.service.Service;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ import dev.home.componente.service.util.ServiceUtils;
/*    */ import dev.home.componente.web.infra.AbstractFormBean;
/*    */ 
/*    */ public class RemoverPredioFormBean extends AbstractFormBean
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */   private String nome;
/*    */ 
/*    */   public String removerPredio()
/*    */     throws NegocioException
/*    */   {
/* 26 */     RemoverPredio solicitacao = new RemoverPredio();
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
/*    */   }
/*    */ 
/*    */   private void popularVo(RemoverPredio solicitacao) {
/* 47 */     solicitacao.setId(this.id);
/*    */   }
/*    */ 
/*    */   public Long getId() {
/* 51 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 55 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getNome() {
/* 59 */     return this.nome;
/*    */   }
/*    */ 
/*    */   public void setNome(String nome) {
/* 63 */     this.nome = nome;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.removerpredio.RemoverPredioFormBean
 * JD-Core Version:    0.6.2
 */