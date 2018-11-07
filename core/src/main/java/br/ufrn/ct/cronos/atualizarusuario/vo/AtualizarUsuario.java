package br.ufrn.ct.cronos.atualizarusuario.vo;

import dev.home.componente.service.entity.Request;

public class AtualizarUsuario extends Request {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String login;
   private String senha;
   private String senhaConfirmacao;
   private Long idPapel;
   private Boolean desabilitarUsuario;

   public AtualizarUsuario() {
      super();
   }

   public AtualizarUsuario(Long id, String login, String senha,
		String senhaConfirmacao, Long idPapel,
		Boolean desabilitarUsuario) {
	   super();
	   this.id = id;
	   this.login = login;
	   this.senha = senha;
	   this.senhaConfirmacao = senhaConfirmacao;
	   this.idPapel = idPapel;
	   this.desabilitarUsuario = desabilitarUsuario;
   }

   public Long getId() {
	   return id;
   }

   public void setId(Long id) {
	   this.id = id;
   }

   public String getLogin() {
	   return login;
   }

   public void setLogin(String login) {
	   this.login = login;
   }

   public String getSenha() {
	   return senha;
   }

   public void setSenha(String senha) {
	   this.senha = senha;
   }

   public String getSenhaConfirmacao() {
	   return senhaConfirmacao;
   }

   public void setSenhaConfirmacao(String senhaConfirmacao) {
	   this.senhaConfirmacao = senhaConfirmacao;
   }

   public Long getIdPapel() {
	   return idPapel;
   }

   public void setIdPapel(Long idPapel) {
	   this.idPapel = idPapel;
   }

	public Boolean getDesabilitarUsuario() {
		return desabilitarUsuario;
	}

	public void setDesabilitarUsuario(Boolean desabilitarUsuario) {
		this.desabilitarUsuario = desabilitarUsuario;
	}

}
