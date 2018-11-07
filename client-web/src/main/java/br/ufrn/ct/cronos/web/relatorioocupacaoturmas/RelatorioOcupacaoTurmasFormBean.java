
package br.ufrn.ct.cronos.web.relatorioocupacaoturmas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import br.ufrn.ct.cronos.listarturma.vo.DadosListarTurma;
import br.ufrn.ct.cronos.listarturma.vo.ListarTurma;
import br.ufrn.ct.cronos.listarturma.vo.RespostaListarTurma;
import br.ufrn.ct.cronos.obterturma.vo.DadosObterTurma;
import br.ufrn.ct.cronos.obterturma.vo.ObterTurma;
import br.ufrn.ct.cronos.obterturma.vo.RespostaObterTurma;
import br.ufrn.ct.cronos.relatorioocupacaoturmas.vo.RelatorioOcupacaoTurmas;
import br.ufrn.ct.cronos.relatorioocupacaoturmas.vo.RespostaRelatorioOcupacaoTurmas;
import br.ufrn.ct.cronos.web.PropertiesLoader;
import br.ufrn.ct.cronos.web.ReportUtils;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;
import dev.home.componente.web.relatorio.IReport;

public class RelatorioOcupacaoTurmasFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
   private Date dataInicial;
   private Date dataFinal;
   private Long idTurma;
   private Locale locale = new Locale("pt", "BR");
   private DadosObterTurma turma;
   private Long idPeriodo, idPredio, idDepartamento;

   public RelatorioOcupacaoTurmasFormBean() {
      if (this.turma == null) {
         this.turma = new DadosObterTurma();
         this.turma.setNomeDisciplina(" - ");
         this.turma.setDocente(" - ");
         this.turma.setHorario(" - ");
         this.turma.setSala(" - ");
      }
   }

   public String gerarRelatorioOcupacaoTurmas() throws NegocioException, IOException {
      RelatorioOcupacaoTurmas solicitacao = new RelatorioOcupacaoTurmas();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaRelatorioOcupacaoTurmas resposta = (RespostaRelatorioOcupacaoTurmas) service.executa(solicitacao);

      // ----------------------------------------------------
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

      // ----------------------------------------------------

      pmtRel.put("SUBREPORT_DIR", path + "/");

      params.put("SUBREPORT_DIR", path + "/WEB-INF/jasper/");

      params.put(IReport.REPORT_FILENAME_KEY, "relatorioHorariosTurmas");
      params.put(IReport.REPORT_FILE_KEY, "/WEB-INF/jasper/relatorioHorariosTurmas.jasper");
      params.put(IReport.REPORT_DATA_HTML_KEY, resposta.getDados());
      params.put("SUBREPORT_DIR", path + "/WEB-INF/jasper/");
      params.put("SUBREPORT_FILE", "/WEB-INF/jasper/subRelatorioHorariosTurmas.jasper");
      params.put(IReport.REPORT_PARAMETERS_KEY, pmtRel);

      ReportUtils.savePdfReport(getResponse(), getRequest(), params);
      getFacesContext().responseComplete();

      return null;
   }

   private void popularVo(RelatorioOcupacaoTurmas solicitacao) {
      solicitacao.setDataInicial(this.dataInicial);
      solicitacao.setDataFinal(this.dataFinal);
      solicitacao.setIdTurma(this.idTurma);
      solicitacao.setIdPredio(this.idPredio);
      solicitacao.setIdPeriodo(this.idPeriodo);
   }

   public String atualizarTurma() throws NegocioException {
      ObterTurma solicitacao = new ObterTurma();
      solicitacao.setId(this.idTurma);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaObterTurma resposta = (RespostaObterTurma) service.executa(solicitacao);
      setTurma((DadosObterTurma) resposta.getObjeto());
      return null;
   }

   public List<SelectItem> getTurmasPorPeriodoEPredio() throws NegocioException {
      List<SelectItem> itens = new ArrayList<SelectItem>();
      ListarTurma solicitacao = new ListarTurma();
      if (this.idPeriodo == null) {
         solicitacao.setIdPeriodo(new Long(0));
      } else {
         solicitacao.setIdPeriodo(this.idPeriodo);
      }
      if (this.idPredio == null) {
         solicitacao.setIdPredio(new Long(0));
      } else {
         solicitacao.setIdPredio(this.idPredio);
      }
      if (this.idDepartamento == null) {
         solicitacao.setIdDepartamento(new Long(0));
      } else {
         solicitacao.setIdDepartamento(this.idDepartamento);
      }

      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaListarTurma resposta = (RespostaListarTurma) service.executa(solicitacao);
      List<DadosListarTurma> dados = resposta.getDados();
      if (!dados.isEmpty()) {
         itens = new ArrayList<SelectItem>(dados.size());
         for (DadosListarTurma d : dados) {
            itens.add(new SelectItem(d.getId(), d.getNome()));
         }
      }
      return itens;
   }

   public String limparDadosTurma() throws NegocioException {
      this.turma = new DadosObterTurma();
      this.turma.setNomeDisciplina(" - ");
      this.turma.setDocente(" - ");
      this.turma.setHorario(" - ");
      this.turma.setPerfil(" - ");
      this.turma.setSala(" - ");
      this.turma.setCapacidade(new Integer(0));
      return null;
   }

   public Date getDataInicial() {
      return this.dataInicial;
   }

   public void setDataInicial(Date dataInicial) {
      this.dataInicial = dataInicial;
   }

   public Date getDataFinal() {
      return this.dataFinal;
   }

   public void setDataFinal(Date dataFinal) {
      this.dataFinal = dataFinal;
   }

   public Locale getLocale() {
      return this.locale;
   }

   public void setLocale(Locale locale) {
      this.locale = locale;
   }

   public Long getIdTurma() {
      return this.idTurma;
   }

   public void setIdTurma(Long idTurma) {
      this.idTurma = idTurma;
   }

   public void setTurma(DadosObterTurma turma) {
      this.turma = turma;
   }

   public DadosObterTurma getTurma() {
      return this.turma;
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

   /**
    * Recupera o valor do atributo idDepartamento
    * @return o idDepartamento
    */
   public Long getIdDepartamento() {
      return idDepartamento;
   }

   /**
    * Atribui o novo valor de idDepartamento
    * @param idDepartamento idDepartamento que será atribuído
    */
   public void setIdDepartamento(Long idDepartamento) {
      this.idDepartamento = idDepartamento;
   }
}
