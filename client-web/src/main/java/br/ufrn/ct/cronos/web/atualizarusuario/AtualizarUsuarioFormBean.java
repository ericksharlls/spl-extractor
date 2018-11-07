package br.ufrn.ct.cronos.web.atualizarusuario;

import br.ufrn.ct.cronos.atualizarusuario.vo.AtualizarUsuario;
import br.ufrn.ct.cronos.obterusuario.vo.ObterUsuario;
import br.ufrn.ct.cronos.obterusuario.vo.RespostaObterUsuario;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class AtualizarUsuarioFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String login;
   private String senha;
   private String senhaConfirmacao;
   private String nomeFuncionario;
   private Long idPapel;
   private boolean desabilitarUsuario;
   
   public AtualizarUsuarioFormBean(){
	   this.desabilitarUsuario = false;
   }

   public String atualizarUsuario() throws NegocioException {
      try {
         AtualizarUsuario solicitacao = new AtualizarUsuario();
         popularVo(solicitacao);
         Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
         service.executa(solicitacao);

         limpar();
         addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");

      } catch (NegocioException e) {
         obterUsuario();
         throw new NegocioException(e.getErrorCode());
      }

      return null;
   }

   private void popularVo(AtualizarUsuario solicitacao) {
      solicitacao.setId(this.id);
      solicitacao.setIdPapel(this.idPapel);
      solicitacao.setLogin(this.login);
      solicitacao.setSenha(this.senha);
      solicitacao.setSenhaConfirmacao(this.senhaConfirmacao);
      solicitacao.setDesabilitarUsuario(this.desabilitarUsuario);
   }

   public void obterUsuario() throws NegocioException {
      ObterUsuario solicitacao = new ObterUsuario();
      solicitacao.setId(this.id);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterUsuario resposta = (RespostaObterUsuario) service.executa(solicitacao);
      this.id = resposta.getObjeto().getId();
      this.nomeFuncionario = resposta.getObjeto().getNomeFuncionario();
      this.idPapel = resposta.getObjeto().getIdPerfil();
      this.login = resposta.getObjeto().getLogin();
      this.senha = resposta.getObjeto().getSenha();
      this.senhaConfirmacao = resposta.getObjeto().getSenhaConfirmacao();
   }

   private void limpar() {
      this.id = new Long(0L);
      this.nomeFuncionario = new String("-");
      this.login = new String("-");
      this.senha = null;
      this.senhaConfirmacao = null;
      this.idPapel = new Long(0L);
   }

   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarUsuario();
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
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

   public void setNomeFuncionario(String nomeFuncionario) {
      this.nomeFuncionario = nomeFuncionario;
   }

   public String getNomeFuncionario() {
      return this.nomeFuncionario;
   }

   public boolean isDesabilitarUsuario() {
	   return desabilitarUsuario;
   }

   public void setDesabilitarUsuario(boolean desabilitarUsuario) {
	   this.desabilitarUsuario = desabilitarUsuario;
   }

}
