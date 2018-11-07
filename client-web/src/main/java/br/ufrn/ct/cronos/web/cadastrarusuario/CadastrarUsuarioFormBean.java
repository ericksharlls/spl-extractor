package br.ufrn.ct.cronos.web.cadastrarusuario;

import br.ufrn.ct.cronos.cadastrarusuario.vo.CadastrarUsuario;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class CadastrarUsuarioFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
   private String login;
   private String senha;
   private String senhaConfirmacao;
   private Long idPerfil;
   private Long idFuncionario;

   public String cadastrarUsuario() throws NegocioException {
      CadastrarUsuario solicitacao = new CadastrarUsuario();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);

      limpar();
      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");

      return null;
   }

   private void popularVo(CadastrarUsuario solicitacao) {
      solicitacao.setLogin(this.login);
      solicitacao.setSenha(this.senha);
      solicitacao.setSenhaConfirmacao(this.senhaConfirmacao);
      solicitacao.setIdPapel(this.idPerfil);
      solicitacao.setIdFuncionario(this.idFuncionario);
   }

   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarUsuario();
   }

   private void limpar() {
      this.login = null;
      this.senha = null;
      this.senhaConfirmacao = null;
      this.idPerfil = new Long(0L);
      this.idFuncionario = new Long(0);
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

   public Long getIdPerfil() {
      return this.idPerfil;
   }

   public void setIdPerfil(Long idPerfil) {
      this.idPerfil = idPerfil;
   }

   public Long getIdFuncionario() {
      return this.idFuncionario;
   }

   public void setIdFuncionario(Long idFuncionario) {
      this.idFuncionario = idFuncionario;
   }

}
