/*    */ package br.ufrn.ct.cronos.listaroperacao.vo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DadosListarOperacao
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */   private String nome;
/*    */ 
/*    */   public DadosListarOperacao()
/*    */   {
/*    */   }
/*    */ 
/*    */   public DadosListarOperacao(Long id, String nome)
/*    */   {
/* 18 */     this.id = id;
/* 19 */     this.nome = nome;
/*    */   }
/*    */ 
/*    */   public Long getId() {
/* 23 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 27 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getNome() {
/* 31 */     return this.nome;
/*    */   }
/*    */ 
/*    */   public void setNome(String nome) {
/* 35 */     this.nome = nome;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.listaroperacao.vo.DadosListarOperacao
 * JD-Core Version:    0.6.2
 */