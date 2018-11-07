
package br.ufrn.ct.cronos.web.atualizarfuncionario;

import br.ufrn.ct.cronos.atualizarfuncionario.vo.AtualizarFuncionario;
import br.ufrn.ct.cronos.obterfuncionario.vo.ObterFuncionario;
import br.ufrn.ct.cronos.obterfuncionario.vo.RespostaObterFuncionario;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class AtualizarFuncionarioFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String nome;
   private String matricula;
   private String email;
   private String cpf;
   private String dddTelefone;
   private String telefone;
   private Long idTipoFuncionario;

   public String atualizarFuncionario() throws NegocioException {
      AtualizarFuncionario solicitacao = new AtualizarFuncionario();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);

      limpar();
      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");

      return null;
   }

   private void popularVo(AtualizarFuncionario solicitacao) {
      solicitacao.setId(this.id);
      solicitacao.setNome(this.nome);
      solicitacao.setEmail(this.email);
      solicitacao.setMatricula(this.matricula);
      solicitacao.setCpf(this.cpf);
      solicitacao.setDddTelefone(this.dddTelefone);
      solicitacao.setTelefone(this.telefone);
      solicitacao.setIdTipoFuncionario(this.idTipoFuncionario);
   }

   public void obterFuncionario() throws NegocioException {
      ObterFuncionario solicitacao = new ObterFuncionario();
      solicitacao.setId(this.id);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterFuncionario resposta = (RespostaObterFuncionario) service.executa(solicitacao);
      this.nome = resposta.getObjeto().getNome();
      this.id = resposta.getObjeto().getId();
      this.email = resposta.getObjeto().getEmail();
      this.matricula = resposta.getObjeto().getMatricula();
      this.cpf = resposta.getObjeto().getCpf();
      this.dddTelefone = resposta.getObjeto().getDddTelefone();
      this.telefone = resposta.getObjeto().getTelefone();
      this.idTipoFuncionario = resposta.getObjeto().getIdTipoFuncionario();
   }

   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarFuncionario();
   }

   private void limpar() {
      this.matricula = null;
      this.nome = null;
      this.email = null;
      this.telefone = null;
      this.cpf = null;
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

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public String getCpf() {
      return cpf;
   }

   public void setCpf(String cpf) {
      this.cpf = cpf;
   }

   public String getTelefone() {
      return telefone;
   }

   public void setTelefone(String telefone) {
      this.telefone = telefone;
   }

   public Long getIdTipoFuncionario() {
      return idTipoFuncionario;
   }

   public void setIdTipoFuncionario(Long idTipoFuncionario) {
      this.idTipoFuncionario = idTipoFuncionario;
   }

   public String getDddTelefone() {
      return dddTelefone;
   }

   public void setDddTelefone(String dddTelefone) {
      this.dddTelefone = dddTelefone;
   }

}
