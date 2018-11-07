package br.ufrn.ct.cronos.web.consultarsalasparamarcacoes;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.SimpleSelection;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import br.ufrn.ct.cronos.agendarhorario.vo.AgendarHorario;
import br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo.ConsultarSalasParaMarcacoes;
import br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo.DadosConsultarSalasParaMarcacoes;
import br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo.RespostaConsultarSalasParaMarcacoes;
import br.ufrn.ct.cronos.relatoriotermocompromisso.vo.RelatorioTermoCompromisso;
import br.ufrn.ct.cronos.relatoriotermocompromisso.vo.RespostaRelatorioTermoCompromisso;
import br.ufrn.ct.cronos.web.PropertiesLoader;
import br.ufrn.ct.cronos.web.ReportUtils;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractConsultarFormBean;
import dev.home.componente.web.relatorio.IReport;

public class ConsultarSalasParaMarcacoesFormBean extends AbstractConsultarFormBean {

   private static final long serialVersionUID = 1L;
		
   private String horarioTurma, horarioTurmaOpcional, motivo, horarioAgendamento;
   private Date dataAgendamento, dataAgendamentoInicio, dataAgendamentoTermino;
   private String data;
		
   private List<Date> datasParaAgendamento;
   private List<String> diasDaSemana;
   private Long idSala, idPeriodo, idPredio, idInteressado;
   private Locale locale = new Locale("pt", "BR");

   private SimpleSelection selection;
   private ExtendedTableDataModel<DadosConsultarSalasParaMarcacoes> dataModel;
   private List<DadosConsultarSalasParaMarcacoes> salas = new ArrayList<DadosConsultarSalasParaMarcacoes>();
   private List<DadosConsultarSalasParaMarcacoes> salasSelecionadas = new ArrayList<DadosConsultarSalasParaMarcacoes>();

   public ExtendedTableDataModel<DadosConsultarSalasParaMarcacoes> getDadosDataModel() throws NegocioException {
      dataModel = new ExtendedTableDataModel<DadosConsultarSalasParaMarcacoes>(new DataProvider<DadosConsultarSalasParaMarcacoes>() {
				   private static final long serialVersionUID = 1L;
				   @Override
         public DadosConsultarSalasParaMarcacoes getItemByKey(Object key) {
            for (DadosConsultarSalasParaMarcacoes c : salas) {
               if (key.equals(getKey(c))) {
                  return c;
					   }
				   }
            return null;
         }

         public List<DadosConsultarSalasParaMarcacoes> getItemsByRange(int firstRow, int endRow) {
            return salas.subList(firstRow, endRow);
         }

         @Override
         public Object getKey(DadosConsultarSalasParaMarcacoes item) {
            return item.getNome();
         }

         @Override
         public int getRowCount() {
            return salas.size();
         }
      });

      return this.dataModel;
   }

   public String consultar() throws NegocioException {
      ConsultarSalasParaMarcacoes solicitacao = new ConsultarSalasParaMarcacoes();
      if (this.horarioTurma == null) {
         this.horarioTurma = new String("");
      }

      if (this.horarioTurmaOpcional != null && !this.horarioTurmaOpcional.equals("")) {
         this.horarioAgendamento = this.horarioTurma + " e " + this.horarioTurmaOpcional;
      } else {
         this.horarioAgendamento = this.horarioTurma;
      }

      solicitacao.setIdPeriodo(this.idPeriodo);
      solicitacao.setIdPredio(this.idPredio);
      solicitacao.setHorarioTurma(this.horarioTurma);
      solicitacao.setHorarioTurmaOpcional(this.horarioTurmaOpcional);
      solicitacao.setDatasParaAgendamento(this.datasParaAgendamento);
      solicitacao.setDataAgendamentoInicio(this.dataAgendamentoInicio);
      solicitacao.setDataAgendamentoTermino(this.dataAgendamentoTermino);
      solicitacao.setDiasDaSemana(this.diasDaSemana);
      this.salas.clear();
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaConsultarSalasParaMarcacoes resposta = (RespostaConsultarSalasParaMarcacoes) service.executa(solicitacao);
      this.salas = resposta.getDados();
      return null;
   }

   public String agendar() throws NegocioException, FileNotFoundException {
      AgendarHorario solicitacao = new AgendarHorario();
 
      solicitacao.setDatasParaAgendamento(this.datasParaAgendamento);
      solicitacao.setHorario(this.horarioTurma);
      solicitacao.setHorarioOpcional(this.horarioTurmaOpcional);
      solicitacao.setIdSala(this.idSala);
      solicitacao.setMotivo(this.motivo);
      solicitacao.setIdInteressado(this.idInteressado);
      solicitacao.setDataAgendamentoInicio(this.dataAgendamentoInicio);
      solicitacao.setDataAgendamentoTermino(this.dataAgendamentoTermino);
      solicitacao.setIdPeriodo(this.idPeriodo);
      solicitacao.setDiasDaSemana(this.diasDaSemana);
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      solicitacao.setLoginUsuario(authentication.getName());
      // solicitacao.setInteressado(this.interessado);
      // solicitacao.setTelefone(this.telefone);
      // solicitacao.setIdentificadorInteressado(this.identificadorInteressado);

      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      service.executa(solicitacao);
			
      RelatorioTermoCompromisso solicitacaoTermoCompromisso = new RelatorioTermoCompromisso();
      solicitacaoTermoCompromisso.setDatasParaAgendamento(this.datasParaAgendamento);
      solicitacaoTermoCompromisso.setHorario(this.horarioTurma);
      solicitacaoTermoCompromisso.setHorarioOpcional(this.horarioTurmaOpcional);
      solicitacaoTermoCompromisso.setMotivo(this.motivo);
      // solicitacaoTermoCompromisso.setInteressado(this.interessado);
      solicitacaoTermoCompromisso.setIdSala(this.idSala);
      // solicitacaoTermoCompromisso.setIdentificadorInteressado(this.identificadorInteressado);
      solicitacaoTermoCompromisso.setIdPeriodo(this.idPeriodo);
      solicitacaoTermoCompromisso.setDiasDaSemana(this.diasDaSemana);
      solicitacaoTermoCompromisso.setIdInteressado(this.idInteressado);
      solicitacaoTermoCompromisso.setDataAgendamentoInicio(this.dataAgendamentoInicio);
      solicitacaoTermoCompromisso.setDataAgendamentoTermino(this.dataAgendamentoTermino);

			
      Service<Response, Request> serviceTermoCompromisso = ServiceUtils.getService(solicitacaoTermoCompromisso.getClass().getSimpleName());
      serviceTermoCompromisso.executa(solicitacaoTermoCompromisso);

      RespostaRelatorioTermoCompromisso respostaTermoCompromisso =
         (RespostaRelatorioTermoCompromisso) serviceTermoCompromisso.executa(solicitacaoTermoCompromisso);

      // ---------------------
      /*
       * String path = ((HttpServletRequest) getFacesContext().getExternalContext().getRequest()).getSession().getServletContext()
       * .getRealPath("/WEB-INF/jasper/"); Map<Object, Object> params = new HashMap<Object, Object>(); HashMap<String, Object> pmtRel = new
       * HashMap<String, Object>(); String pathImagem = ((HttpServletRequest)
       * getFacesContext().getExternalContext().getRequest()).getSession().getServletContext() .getRealPath("/layout/imagens/UFRN.jpg");
       * File imagem = new File(pathImagem); InputStream inputStreamDaImagem = new FileInputStream(imagem); pmtRel.put("logoUFRN",
       * inputStreamDaImagem);
       */

      String path = ((HttpServletRequest) getFacesContext().getExternalContext().getRequest()).getSession().getServletContext()
               .getRealPath("/WEB-INF/jasper/");

      Map<Object, Object> params = new HashMap<Object, Object>();
      HashMap<String, Object> pmtRel = new HashMap<String, Object>();

      String pathImagemLogoDireita =
         ((HttpServletRequest) getFacesContext().getExternalContext().getRequest()).getSession().getServletContext()
                  .getRealPath("/layout/imagens/logoDireita.jpg");

      String nomeArquivoLogoEsquerda = PropertiesLoader.propertiesLoader().getProperty("logoEsquerdaRelatorios");
      String pathImagemLogoEsquerda =
         ((HttpServletRequest) getFacesContext().getExternalContext().getRequest()).getSession().getServletContext()
                  .getRealPath("/layout/imagens/" + nomeArquivoLogoEsquerda);

      File imagemLogoDireita = new File(pathImagemLogoDireita);
      File imagemLogoEsquerda = new File(pathImagemLogoEsquerda);

      InputStream inputStreamDaImagemLogoDireita = new FileInputStream(imagemLogoDireita);
      InputStream inputStreamDaImagemLogoEsquerda = new FileInputStream(imagemLogoEsquerda);

      pmtRel.put("logoDireita", inputStreamDaImagemLogoDireita);
      pmtRel.put("logoEsquerda", inputStreamDaImagemLogoEsquerda);

      // --------------------------------

      String nomeCentro = PropertiesLoader.propertiesLoader().getProperty("Centro");

      params.put(IReport.REPORT_FILENAME_KEY, "TermoDeCompromisso");
      params.put(IReport.REPORT_FILE_KEY, "/WEB-INF/jasper/termoCompromisso" + nomeCentro + ".jasper");
      params.put(IReport.REPORT_DATA_HTML_KEY, respostaTermoCompromisso.getDados());
      params.put(IReport.SUBREPORT_DIR_KEY, path + "/WEB-INF/jasper/");
      params.put(IReport.REPORT_PARAMETERS_KEY, pmtRel);

      ReportUtils.savePdfReport(getResponse(), getRequest(), params);

			
      limpar();
      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso");

      getFacesContext().responseComplete();

      return null;
   }
 
   public void adicionarData() {
      if (!this.datasParaAgendamento.contains(this.dataAgendamento)) {
         this.datasParaAgendamento.add(this.dataAgendamento);
      }
      if (this.datasParaAgendamento == null) {
         this.datasParaAgendamento = new ArrayList<Date>();
      }
      this.dataAgendamento = null;
      this.salas.clear();
   }

   public void removerData() throws ParseException {
      if (this.datasParaAgendamento == null) {
         this.datasParaAgendamento = new ArrayList<Date>();
      }
      SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
      Date d = new Date(format.parse(this.data).getTime());
      this.datasParaAgendamento.remove(d);
      this.data = new String();
      this.salas.clear();
   }

   public List<SelectItem> getDatas() {
      List<SelectItem> items = new ArrayList<SelectItem>();
      DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      if (this.datasParaAgendamento == null) {
         this.datasParaAgendamento = new ArrayList<Date>();
      }
      for (Date data : this.datasParaAgendamento) {
         SelectItem item = new SelectItem(formatter.format(data), formatter.format(data));
         items.add(item);
		}
      return items;
   }

   public List<SelectItem> getDiasSemana() {
      List<SelectItem> items = new ArrayList<SelectItem>(0);
      if (this.diasDaSemana == null) {
         this.diasDaSemana = new ArrayList<String>();
      }

      SelectItem itemSegunda = new SelectItem("2", "Segunda-feira");
      SelectItem itemTerca = new SelectItem("3", "Ter\u00E7a-feira");
      SelectItem itemQuarta = new SelectItem("4", "Quarta-feira");
      SelectItem itemQuinta = new SelectItem("5", "Quinta-feira");
      SelectItem itemSexta = new SelectItem("6", "Sexta-feira");
      SelectItem itemSabado = new SelectItem("7", "S\u00E1bado");

      items.add(itemSegunda);
      items.add(itemTerca);
      items.add(itemQuarta);
      items.add(itemQuinta);
      items.add(itemSexta);
      items.add(itemSabado);

      return items;
   }

   private void limpar() {
      this.idPeriodo = null;
      this.idPredio = null;
      this.idInteressado = null;
      this.dataAgendamento = null;
      this.horarioTurma = new String();
      this.horarioAgendamento = new String();
      this.motivo = new String();
      this.idSala = new Long(0);
      this.salas.clear();
      this.salasSelecionadas.clear();
   }

   public SimpleSelection getSelection() {
      return this.selection;
   }

   public void setSelection(SimpleSelection selection) {
      this.selection = selection;
   }

   public ExtendedTableDataModel<DadosConsultarSalasParaMarcacoes> getDataModel() {
      return this.dataModel;
   }

   public void setDataModel(ExtendedTableDataModel<DadosConsultarSalasParaMarcacoes> dataModel) {
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
 
   public int getLinhas() {
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
 
   public String getMotivo() {
      return this.motivo;
   }
 
   public void setMotivo(String motivo) {
      this.motivo = motivo;
   }
 
   public void setDatasParaAgendamento(List<Date> datasParaAgendamento) {
      this.datasParaAgendamento = datasParaAgendamento;
   }
 
   public List<Date> getDatasParaAgendamento() {
      return this.datasParaAgendamento;
   }
 
   public void setData(String data) {
      this.data = data;
   }
 
   public String getData() {
      return this.data;
   }

   public void setHorarioTurmaOpcional(String horarioTurmaOpcional) {
      this.horarioTurmaOpcional = horarioTurmaOpcional;
   }
 
   public String getHorarioTurmaOpcional() {
      return this.horarioTurmaOpcional;
	}

   public Date getDataAgendamentoInicio() {
      return dataAgendamentoInicio;
   }

      public void setDataAgendamentoInicio(Date dataAgendamentoInicio) {
      this.dataAgendamentoInicio = dataAgendamentoInicio;
   }

   public Date getDataAgendamentoTermino() {
      return dataAgendamentoTermino;
   }

   public void setDataAgendamentoTermino(Date dataAgendamentoTermino) {
      this.dataAgendamentoTermino = dataAgendamentoTermino;
   }

   /**
    * Recupera o valor do atributo idPeriodo
    * @return o idPeriodo
    */
   public Long getIdPeriodo() {
      return idPeriodo;
   }

   /**
    * Atribui o novo valor de idPeriodo
    * @param idPeriodo idPeriodo que será atribuído
    */
   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
   }

   /**
    * Recupera o valor do atributo idPredio
    * @return o idPredio
    */
   public Long getIdPredio() {
      return idPredio;
   }

   /**
    * Atribui o novo valor de idPredio
    * @param idPredio idPredio que será atribuído
    */
   public void setIdPredio(Long idPredio) {
      this.idPredio = idPredio;
   }

   public String getHorarioAgendamento() {
      return horarioAgendamento;
   }

   public void setHorarioAgendamento(String horarioAgendamento) {
      this.horarioAgendamento = horarioAgendamento;
   }

   public Long getIdInteressado() {
      return idInteressado;
   }

   public void setIdInteressado(Long idInteressado) {
      this.idInteressado = idInteressado;
   }

   /**
    * Recupera o valor do atributo diasDaSemana
    * @return o diasDaSemana
    */
   public List<String> getDiasDaSemana() {
      return diasDaSemana;
   }

   /**
    * Atribui o novo valor de diasDaSemana
    * @param diasDaSemana diasDaSemana que será atribuído
    */
   public void setDiasDaSemana(List<String> diasDaSemana) {
      this.diasDaSemana = diasDaSemana;
   }
}
