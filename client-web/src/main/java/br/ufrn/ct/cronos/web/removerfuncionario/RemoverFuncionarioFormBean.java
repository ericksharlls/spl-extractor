
package br.ufrn.ct.cronos.web.removerfuncionario;

import br.ufrn.ct.cronos.obterfuncionario.vo.ObterFuncionario;
import br.ufrn.ct.cronos.obterfuncionario.vo.RespostaObterFuncionario;
import br.ufrn.ct.cronos.removerfuncionario.vo.RemoverFuncionario;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class RemoverFuncionarioFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String nome;
   private String matricula;
   private String email;

   public String removerFuncionario() throws NegocioException {
      RemoverFuncionario solicitacao = new RemoverFuncionario();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);
      return ControlNavigationFormBean.pageConsultarFuncionario();
   }

   public void obterFuncionario() throws NegocioException {
      ObterFuncionario solicitacao = new ObterFuncionario();
      solicitacao.setId(this.id);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterFuncionario resposta = (RespostaObterFuncionario) service.executa(solicitacao);
      this.id = resposta.getObjeto().getId();
      this.nome = resposta.getObjeto().getNome();
      this.email = resposta.getObjeto().getEmail();
      this.matricula = resposta.getObjeto().getMatricula();
   }

   private void popularVo(RemoverFuncionario solicitacao) {
      solicitacao.setId(this.id);
   }

   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarFuncionario();
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getMatricula() {
      return this.matricula;
   }

   public void setMatricula(String matricula) {
      this.matricula = matricula;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

}
