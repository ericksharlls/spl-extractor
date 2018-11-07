/*    */ package br.ufrn.ct.cronos.consultarpredio.vo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DadosConsultarPredio
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */   private String nome;
/*    */ 
/*    */   public DadosConsultarPredio()
/*    */   {
/*    */   }
/*    */ 
/*    */   public DadosConsultarPredio(Long id, String nome)
/*    */   {
/* 19 */     this.id = id;
/* 20 */     this.nome = nome;
/*    */   }
/*    */ 
/*    */   public Long getId() {
/* 24 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 28 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getNome() {
/* 32 */     return this.nome;
/*    */   }
/*    */ 
/*    */   public void setNome(String nome) {
/* 36 */     this.nome = nome;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.consultarpredio.vo.DadosConsultarPredio
 * JD-Core Version:    0.6.2
 */