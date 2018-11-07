
package br.ufrn.ct.cronos.web.removeragendamento;

import java.util.Date;
import javax.faces.event.ActionEvent;
import br.ufrn.ct.cronos.obteragendamento.vo.DadosObterAgendamento;
import br.ufrn.ct.cronos.obteragendamento.vo.ObterAgendamento;
import br.ufrn.ct.cronos.obteragendamento.vo.RespostaObterAgendamento;
import br.ufrn.ct.cronos.removeragendamentoporinteressado.vo.RemoverAgendamentoPorInteressado;
import dev.home.componente.service.Service;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class RemoverAgendamentoFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String interessado;
   private String telefone;
   private String motivo;
   private Date data;

   public void obterAgendamento(ActionEvent event) throws NegocioException {
      ObterAgendamento solicitacao = new ObterAgendamento();
      System.out.println("##### Id do agendamento:" + this.id);
      solicitacao.setId(this.id);
      Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterAgendamento resposta = (RespostaObterAgendamento) service.executa(solicitacao);
      this.id = ((DadosObterAgendamento) resposta.getObjeto()).getId();
      this.interessado = ((DadosObterAgendamento) resposta.getObjeto()).getInteressado();
      this.telefone = ((DadosObterAgendamento) resposta.getObjeto()).getTelefone();
      this.motivo = ((DadosObterAgendamento) resposta.getObjeto()).getMotivo();
      // this.data = ((DadosObterAgendamento) resposta.getObjeto()).getData();
   }

   public String removerAgendamento() throws NegocioException {
      RemoverAgendamentoPorInteressado solicitacao = new RemoverAgendamentoPorInteressado();
      // solicitacao.setId(this.id);
      Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);
      return null;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public String getInteressado() {
      return this.interessado;
   }

   public void setInteressado(String interessado) {
      this.interessado = interessado;
   }

   public String getTelefone() {
      return this.telefone;
   }

   public void setTelefone(String telefone) {
      this.telefone = telefone;
   }

   public String getMotivo() {
      return this.motivo;
   }

   public void setMotivo(String motivo) {
      this.motivo = motivo;
   }

   public Date getData() {
      return this.data;
   }

   public void setData(Date data) {
      this.data = data;
   }

}
