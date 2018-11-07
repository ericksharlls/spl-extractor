/*    */ package br.ufrn.ct.cronos.entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class Predio
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */   private String nome;
/*    */   private String descricao;
/*    */ 
/*    */   public Predio()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Predio(Long id, String nome, String descricao)
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
/*    */   public void setId(Long id) {
/* 28 */     this.id = id;
/*    */   }
/*    */   public String getNome() {
/* 31 */     return this.nome;
/*    */   }
/*    */   public void setNome(String nome) {
/* 34 */     this.nome = nome;
/*    */   }
/*    */   public String getDescricao() {
/* 37 */     return this.descricao;
/*    */   }
/*    */   public void setDescricao(String descricao) {
/* 40 */     this.descricao = descricao;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-persistence-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.entity.Predio
 * JD-Core Version:    0.6.2
 */