/*    */ package br.ufrn.ct.cronos.entity;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class Usuario
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */   private String login;
/*    */   private String senha;
/*    */   private Boolean ativo;
/*    */   private Long idFuncionario;
/*    */ 
/*    */   public Usuario()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Usuario(Long id, String login, String senha, Boolean ativo, Long idFuncionario)
/*    */   {
/* 21 */     this.id = id;
/* 22 */     this.login = login;
/* 23 */     this.senha = senha;
/* 24 */     this.ativo = ativo;
/* 25 */     this.idFuncionario = idFuncionario;
/*    */   }
/*    */ 
/*    */   public Long getId() {
/* 29 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 33 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getLogin() {
/* 37 */     return this.login;
/*    */   }
/*    */ 
/*    */   public void setLogin(String login) {
/* 41 */     this.login = login;
/*    */   }
/*    */ 
/*    */   public String getSenha() {
/* 45 */     return this.senha;
/*    */   }
/*    */ 
/*    */   public void setSenha(String senha) {
/* 49 */     this.senha = senha;
/*    */   }
/*    */ 
/*    */   public Boolean getAtivo() {
/* 53 */     return this.ativo;
/*    */   }
/*    */ 
/*    */   public void setAtivo(Boolean ativo) {
/* 57 */     this.ativo = ativo;
/*    */   }
/*    */ 
/*    */   public Long getIdFuncionario() {
/* 61 */     return this.idFuncionario;
/*    */   }
/*    */ 
/*    */   public void setIdFuncionario(Long idFuncionario) {
/* 65 */     this.idFuncionario = idFuncionario;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-persistence-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.entity.Usuario
 * JD-Core Version:    0.6.2
 */