package br.ufrn.ct.cronos.web.obterhorariosalaformbean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import br.ufrn.ct.cronos.listarsalaporpredio.vo.DadosListarSalaPorPredio;
import br.ufrn.ct.cronos.listarsalaporpredio.vo.ListarSalaPorPredio;
import br.ufrn.ct.cronos.listarsalaporpredio.vo.RespostaListarSalaPorPredio;
import br.ufrn.ct.cronos.obterhorariosala.vo.DadosTurma;
import br.ufrn.ct.cronos.obterhorariosala.vo.ObterHorarioSala;
import br.ufrn.ct.cronos.obterhorariosala.vo.RespostaObterHorarioSala;
import br.ufrn.ct.cronos.removeragendamentoporinteressado.vo.RemoverAgendamentoPorInteressado;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;

public class ObterHorarioSalaFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
		
   private String stringHorario, interessado, telefone, motivo, turno;
   private Integer horario, dia;
   private Date data;
		
   private Long idAgendamento;
   private Long idSala;
   private Long idPredio;
   private Long idPeriodo;
   private List<DadosTurma> objetos = null;
		
   public ObterHorarioSalaFormBean() {
      super();
      this.turno = new String("M");
      this.idPredio = new Long(0);
      this.idPeriodo = new Long(0);
      this.idSala = new Long(0);
   }

   public List<DadosTurma> getDados() throws NegocioException {
      ObterHorarioSala solicitacao = new ObterHorarioSala();
      solicitacao.setIdSala(this.idSala);
      solicitacao.setTurno(this.turno);
      solicitacao.setIdPeriodo(this.idPeriodo);
			
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterHorarioSala resposta = (RespostaObterHorarioSala) service.executa(solicitacao);
      setObjetos(resposta.getDados());
      return getObjetos();
   }
		
   public String apagarAgendamento() throws NegocioException {
      RemoverAgendamentoPorInteressado solicitacao = new RemoverAgendamentoPorInteressado();
      solicitacao.setIdAgendamento(this.idAgendamento);
			
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);
			
      limpar();
			
      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso");
			
      return null;
   }
 
   public void atualizarSala(ActionEvent event) {
      HtmlSelectOneMenu val = (HtmlSelectOneMenu) event.getComponent().findComponent("sala");
      Long id = Long.valueOf(val.getValue().toString());
      this.idSala = id;
   }
		 
   public void atualizarHorariosSala(ActionEvent event) {
      this.idSala = new Long(0);
   }
		
   public List<SelectItem> getSalasPorPredio() throws NegocioException {
      List<SelectItem> itens = new ArrayList<SelectItem>();
      ListarSalaPorPredio solicitacao = new ListarSalaPorPredio();
      solicitacao.setIdPredio(this.idPredio);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaListarSalaPorPredio resposta = (RespostaListarSalaPorPredio) service.executa(solicitacao);
      List<DadosListarSalaPorPredio> dados = resposta.getDados();
      if (!dados.isEmpty()) {
         itens = new ArrayList<SelectItem>(dados.size());
         for (DadosListarSalaPorPredio d : dados) {
            itens.add(new SelectItem(d.getId(), d.getNome()));
			}
		}
      return itens;
   }
		
   private void limpar() {
      this.data = null;
      this.interessado = new String();
      this.motivo = new String();
      this.telefone = new String();
   }
 
   public void setObjetos(List<DadosTurma> objetos) {
      this.objetos = objetos;
   }
 
   public List<DadosTurma> getObjetos() {
      return this.objetos;
   }
		
   public void setIdSala(Long idSala) {
      this.idSala = idSala;
   }
 
   public Long getIdSala() {
      return this.idSala;
   }
		
   public void setDia(Integer dia) {
      this.dia = dia;
   }
		
   public Integer getDia() {
      return this.dia;
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
 
   public Date getData() {
      return this.data;
   }
		
   public void setData(Date data) {
      this.data = data;
   }
 
   public void setMotivo(String motivo) {
      this.motivo = motivo;
   }
		
   public String getMotivo() {
      return this.motivo;
   }
		
   public void setTurno(String turno) {
      this.turno = turno;
   }
		
   public String getTurno() {
      return this.turno;
   }
		
   public void setStringHorario(String stringHorario) {
      this.stringHorario = stringHorario;
   }
		
   public String getStringHorario() {
      return this.stringHorario;
   }
		
   public void setHorario(Integer horario) {
      this.horario = horario;
   }
		
   public Integer getHorario() {
      return this.horario;
   }

   public void setIdAgendamento(Long idAgendamento) {
      this.idAgendamento = idAgendamento;
   }
		
   public Long getIdAgendamento() {
      return this.idAgendamento;
   }
		
   public void setIdPredio(Long idPredio) {
      this.idPredio = idPredio;
   }
		
   public Long getIdPredio() {
      return this.idPredio;
   }

   public Long getIdPeriodo() {
      return idPeriodo;
   }

   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
   }

}
