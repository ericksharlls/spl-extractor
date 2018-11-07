/*    */ package br.ufrn.ct.cronos.web.cadastrarpredio;
/*    */ 
/*    */ import br.ufrn.ct.cronos.cadastrarpredio.vo.CadastrarPredio;
/*    */ import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
/*    */ import dev.home.componente.service.Service;
/*    */ import dev.home.componente.service.excecao.NegocioException;
/*    */ import dev.home.componente.service.util.ServiceUtils;
/*    */ import dev.home.componente.web.infra.AbstractFormBean;
/*    */ 
/*    */ public class CadastrarPredioFormBean extends AbstractFormBean
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String nome;
/*    */   private String descricao;
/*    */ 
/*    */   public String cadastrarPredio()
/*    */     throws NegocioException
/*    */   {
/* 23 */     CadastrarPredio solicitacao = new CadastrarPredio();
/* 24 */     popularVo(solicitacao);
/* 25 */     Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
/* 26 */     service.executa(solicitacao);
/* 27 */     return ControlNavigationFormBean.pageConsultarPredio();
/*    */   }
/*    */ 
/*    */   public String cancelar() {
/* 31 */     return ControlNavigationFormBean.pageConsultarPredio();
/*    */   }
/*    */ 
/*    */   private void popularVo(CadastrarPredio solicitacao) {
/* 35 */     solicitacao.setNome(this.nome);
/* 36 */     solicitacao.setDescricao(this.descricao);
/*    */   }
/*    */ 
/*    */   public String getNome() {
/* 40 */     return this.nome;
/*    */   }
/*    */ 
/*    */   public void setNome(String nome) {
/* 44 */     this.nome = nome;
/*    */   }
/*    */ 
/*    */   public String getDescricao() {
/* 48 */     return this.descricao;
/*    */   }
/*    */ 
/*    */   public void setDescricao(String descricao) {
/* 52 */     this.descricao = descricao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.cadastrarpredio.CadastrarPredioFormBean
 * JD-Core Version:    0.6.2
 */