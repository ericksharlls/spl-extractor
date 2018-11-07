/*    */ package br.ufrn.ct.cronos.atualizarpredio.vo;
/*    */ 
/*    */ import dev.home.componente.service.entity.Request;
/*    */ 
/*    */ public class AtualizarPredio extends Request
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */   private String nome;
/*    */   private String descricao;
/*    */ 
/*    */   public AtualizarPredio()
/*    */   {
/*    */   }
/*    */ 
/*    */   public AtualizarPredio(Long id, String nome, String descricao)
/*    */   {
/* 18 */     this.id = id;
/* 19 */     this.nome = nome;
/* 20 */     this.descricao = descricao;
/*    */   }
/*    */ 
/*    */   public Long getId()
/*    */   {
/* 25 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 29 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getNome() {
/* 33 */     return this.nome;
/*    */   }
/*    */ 
/*    */   public void setNome(String nome) {
/* 37 */     this.nome = nome;
/*    */   }
/*    */ 
/*    */   public String getDescricao() {
/* 41 */     return this.descricao;
/*    */   }
/*    */ 
/*    */   public void setDescricao(String descricao) {
/* 45 */     this.descricao = descricao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.atualizarpredio.vo.AtualizarPredio
 * JD-Core Version:    0.6.2
 */