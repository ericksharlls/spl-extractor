package br.ufrn.ct.cronos.web.consultarsalasparamarcacoes;

import br.ufrn.ct.cronos.agendarhorario.vo.AgendarHorario;
import br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo.ConsultarSalasParaMarcacoes;
import br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo.DadosConsultarSalasParaMarcacoes;
import br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo.RespostaConsultarSalasParaMarcacoes;
import dev.home.componente.service.Service;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractConsultarFormBean;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.model.SelectItem;
import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.SimpleSelection;
 
public class ConsultarSalasParaMarcacoesFormBeanFuturo extends AbstractConsultarFormBean {

	@Override
	public int getLinhas() {
		// TODO Auto-generated method stub
		return 0;
	}
	/*
		private static final long serialVersionUID = 1L;
   
		private String horarioTurma;
		private String interessado;
		private String telefone;
		private String motivo;
		private Date dataAgendamento;
		private String data;
		private List<Date> datasParaAgendamento;
		private Long idSala;
		private Locale locale = new Locale("pt", "BR");
		private SimpleSelection selection;
		private ExtendedTableDataModel<DadosConsultarSalasParaMarcacoes> dataModel;
		private List<DadosConsultarSalasParaMarcacoes> salas = new ArrayList();
		private List<DadosConsultarSalasParaMarcacoes> salasSelecionadas = new ArrayList();
 
		public ExtendedTableDataModel<DadosConsultarSalasParaMarcacoes> getDadosDataModel() throws NegocioException {
			this.dataModel = new ExtendedTableDataModel(new DataProvider(){
				
				private static final long serialVersionUID = 1L;
 
				public DadosConsultarSalasParaMarcacoes getItemByKey(Object key){
					for (DadosConsultarSalasParaMarcacoes c : ConsultarSalasParaMarcacoesFormBeanFuturo.this.salas) {
						if (key.equals(getKey(c))) {
							return c;
						}
					}
					return null;
				}
 
				public List<DadosConsultarSalasParaMarcacoes> getItemsByRange(int firstRow, int endRow) {
					return ConsultarSalasParaMarcacoesFormBeanFuturo.this.salas.subList(firstRow, endRow);
				}
 
				public Object getKey(DadosConsultarSalasParaMarcacoes item){
					return item.getNome();
				}
 
				public int getRowCount(){
					return ConsultarSalasParaMarcacoesFormBeanFuturo.this.salas.size();
				}
			});
			return this.dataModel;
		}
 
		public String consultar() throws NegocioException{
			ConsultarSalasParaMarcacoes solicitacao = new ConsultarSalasParaMarcacoes();
			if (this.horarioTurma == null) {
				this.horarioTurma = new String("");
			}
			solicitacao.setHorarioTurma(this.horarioTurma);
 
			this.salas.clear();
 
			Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
			RespostaConsultarSalasParaMarcacoes resposta = (RespostaConsultarSalasParaMarcacoes)service.executa(solicitacao);
 
			this.salas = resposta.getDados();
 
			return null;
		}
 
		public String agendar() throws NegocioException {
			AgendarHorario solicitacao = new AgendarHorario();
 
			solicitacao.setHorario(this.horarioTurma);
			solicitacao.setIdSala(this.idSala);
			solicitacao.setInteressado(this.interessado);
			solicitacao.setMotivo(this.motivo);
			solicitacao.setTelefone(this.telefone);
 
			Service service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
			service.executa(solicitacao);
 
			limpar();
 
			addInfoMessage("Operação realizada com sucesso");
 
			return null;
		}
 
		public void adicionarData() {
			System.out.println("### AdicionarData ###");
			if (this.datasParaAgendamento == null) {
				this.datasParaAgendamento = new ArrayList();
			}
			this.datasParaAgendamento.add(this.dataAgendamento);
			this.dataAgendamento = null;
		}
 
		public void removerData() throws ParseException {
			System.out.println("### RemoverData ###");
			if (this.datasParaAgendamento == null) {
				this.datasParaAgendamento = new ArrayList();
			}
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date d = new Date(format.parse(this.data).getTime());
 
			this.datasParaAgendamento.remove(d);
			this.data = new String();
		}
 
		public List<SelectItem> getDatas() {
			List items = new ArrayList();
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			if (this.datasParaAgendamento == null) {
				this.datasParaAgendamento = new ArrayList();
			}
			for (Date data : this.datasParaAgendamento) {
				SelectItem item = new SelectItem(formatter.format(data), formatter.format(data));
				items.add(item);
			}
			return items;
		}
 
		private void limpar() {
			this.dataAgendamento = null;
			this.horarioTurma = new String();
			this.interessado = new String();
			this.motivo = new String();
			this.telefone = new String();
			this.idSala = new Long(0L);
			this.salas.clear();
 
			this.salasSelecionadas.clear();
		}
 
		public SimpleSelection getSelection(){
			return this.selection;
		}
 
		public void setSelection(SimpleSelection selection) {
			this.selection = selection;
		}
 
		public ExtendedTableDataModel<DadosConsultarSalasParaMarcacoes> getDataModel() {
			return this.dataModel;
		}
 
		public void setDataModel(ExtendedTableDataModel<DadosConsultarSalasParaMarcacoes> dataModel){
			this.dataModel = dataModel;
		}
 
		public List<DadosConsultarSalasParaMarcacoes> getSalas() {
			return this.salas;
		}
 
		public void setSalas(List<DadosConsultarSalasParaMarcacoes> salas) {
			this.salas = salas;
		}
 
		public List<DadosConsultarSalasParaMarcacoes> getSalasSelecionadas() {
			return this.salasSelecionadas;
		}
 
		public void setSalasSelecionadas(List<DadosConsultarSalasParaMarcacoes> salasSelecionadas) {
			this.salasSelecionadas = salasSelecionadas;
		}
 
		public void setHorarioTurma(String horarioTurma) {
			this.horarioTurma = horarioTurma;
		}
 
		public String getHorarioTurma() {
			return this.horarioTurma;
		}
 
		public Date getDataAgendamento() {
			return this.dataAgendamento;
		}
 
		public void setDataAgendamento(Date dataAgendamento) {
			this.dataAgendamento = dataAgendamento;
		}
 
		public int getLinhas(){
			return 0;
		}
 
		public void setLocale(Locale locale) {
			this.locale = locale;
		}
 
		public Locale getLocale() {
			return this.locale;
		}
 
		public void setIdSala(Long idSala) {
			this.idSala = idSala;
		}
 
		public Long getIdSala() {
			return this.idSala;
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
 
		public String getMotivo(){
			return this.motivo;
		}
 
		public void setMotivo(String motivo){
			this.motivo = motivo;
		}
 
		public void setDatasParaAgendamento(List<Date> datasParaAgendamento){
			this.datasParaAgendamento = datasParaAgendamento;
		}
 
		public List<Date> getDatasParaAgendamento(){
			return this.datasParaAgendamento;
		}
 
		public void setData(String data){
			this.data = data;
		}
 
		public String getData(){
			return this.data;
		}
*/
}

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-client-web-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.web.consultarsalasparamarcacoes.ConsultarSalasParaMarcacoesFormBeanFuturo
 * JD-Core Version:    0.6.2
 */