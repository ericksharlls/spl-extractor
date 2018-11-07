package br.ufrn.ct.cronos.cadastrarusuario.vo;

import dev.home.componente.service.entity.Request;

public class CadastrarUsuario extends Request {

   private static final long serialVersionUID = 1L;
   private String login;
   private String senha;
   private String senhaConfirmacao;
   private Long idPapel;
   private Long idFuncionario;

   public CadastrarUsuario() {
      super();
   }

   public CadastrarUsuario(String login, String senha, String senhaConfirmacao, Long idPapel, Long idFuncionario) {
      this.login = login;
      this.senha = senha;
      this.senhaConfirmacao = senhaConfirmacao;
      this.idPapel = idPapel;
      this.idFuncionario = idFuncionario;
   }

   public String getLogin() {
      return this.login;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   public String getSenha() {
      return this.senha;
   }

   public void setSenha(String senha) {
      this.senha = senha;
   }

   public String getSenhaConfirmacao() {
      return this.senhaConfirmacao;
   }

   public void setSenhaConfirmacao(String senhaConfirmacao) {
      this.senhaConfirmacao = senhaConfirmacao;
   }

   public Long getIdPapel() {
      return this.idPapel;
   }

   public void setIdPapel(Long idPapel) {
      this.idPapel = idPapel;
   }

   public Long getIdFuncionario() {
      return this.idFuncionario;
   }

   public void setIdFuncionario(Long idFuncionario) {
      this.idFuncionario = idFuncionario;
   }

}
