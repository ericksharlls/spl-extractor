/*    */ package br.ufrn.ct.cronos.cadastrarpredio.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.Request;
/*    */ 
/*    */ public class CadastrarPredio extends Request
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String nome;
/*    */   private String descricao;
/*    */ 
/*    */   public CadastrarPredio()
/*    */   {
/*    */   }
/*    */ 
/*    */   public CadastrarPredio(String nome, String descricao)
/*    */   {
/* 17 */     this.nome = nome;
/* 18 */     this.descricao = descricao;
/*    */   }
/*    */ 
/*    */   public String getNome() {
/* 22 */     return this.nome;
/*    */   }
/*    */ 
/*    */   public void setNome(String nome) {
/* 26 */     this.nome = nome;
/*    */   }
/*    */ 
/*    */   public String getDescricao() {
/* 30 */     return this.descricao;
/*    */   }
/*    */ 
/*    */   public void setDescricao(String descricao) {
/* 34 */     this.descricao = descricao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.cadastrarpredio.vo.CadastrarPredio
 * JD-Core Version:    0.6.2
 */