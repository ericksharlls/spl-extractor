/*    */ package br.ufrn.ct.cronos.obterusuario.vo;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class DadosObterUsuario
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private Long id;
/*    */   private String login;
/*    */   private String senha;
/*    */   private String senhaConfirmacao;
/*    */   private String nomeFuncionario;
/*    */   private Long idPerfil;
/*    */ 
/*    */   public DadosObterUsuario()
/*    */   {
/*    */   }
/*    */ 
/*    */   public DadosObterUsuario(Long id, String login, String senha, String senhaConfirmacao, String nomeFuncionario, Long idPerfil)
/*    */   {
/* 20 */     this.id = id;
/* 21 */     this.login = login;
/* 22 */     this.senha = senha;
/* 23 */     this.senhaConfirmacao = senhaConfirmacao;
/* 24 */     this.nomeFuncionario = nomeFuncionario;
/* 25 */     this.idPerfil = idPerfil;
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
/*    */   public String getSenhaConfirmacao() {
/* 53 */     return this.senhaConfirmacao;
/*    */   }
/*    */ 
/*    */   public void setSenhaConfirmacao(String senhaConfirmacao) {
/* 57 */     this.senhaConfirmacao = senhaConfirmacao;
/*    */   }
/*    */ 
/*    */   public String getNomeFuncionario() {
/* 61 */     return this.nomeFuncionario;
/*    */   }
/*    */ 
/*    */   public void setNomeFuncionario(String nomeFuncionario) {
/* 65 */     this.nomeFuncionario = nomeFuncionario;
/*    */   }
/*    */ 
/*    */   public Long getIdPerfil() {
/* 69 */     return this.idPerfil;
/*    */   }
/*    */ 
/*    */   public void setIdPerfil(Long idPerfil) {
/* 73 */     this.idPerfil = idPerfil;
/*    */   }
/*    */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-core-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.obterusuario.vo.DadosObterUsuario
 * JD-Core Version:    0.6.2
 */