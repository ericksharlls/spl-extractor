
package br.ufrn.ct.cronos.web.relatoriohorariosalas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import br.ufrn.ct.cronos.relatoriohorariosalas.vo.RelatorioHorarioSalas;
import br.ufrn.ct.cronos.relatoriohorariosalas.vo.RespostaRelatorioHorarioSalas;
import br.ufrn.ct.cronos.web.PropertiesLoader;
import br.ufrn.ct.cronos.web.ReportUtils;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;
import dev.home.componente.web.relatorio.IReport;

public class RelatorioUtilizacaoHorariosSalasFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
   private Date dataInicial;
   private Date dataFinal;
   private Long idPredio;
   private Locale locale = new Locale("pt", "BR");

   public String gerarRelatorioHorarioSalas() throws NegocioException, IOException {
      RelatorioHorarioSalas solicitacao = new RelatorioHorarioSalas();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaRelatorioHorarioSalas resposta = (RespostaRelatorioHorarioSalas) service.executa(solicitacao);

      // -----------------------------------------

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

      // ----------------------------------------

      pmtRel.put("SUBREPORT_DIR", path + "/");

      params.put("SUBREPORT_DIR", path + "/WEB-INF/jasper/");

      params.put(IReport.REPORT_FILENAME_KEY, "relatorioUtilizacaoHorariosSalas");
      params.put(IReport.REPORT_FILE_KEY, "/WEB-INF/jasper/relatorioUtilizacaoHorariosSalas.jasper");
      params.put(IReport.REPORT_DATA_HTML_KEY, resposta.getDados());
      params.put("SUBREPORT_DIR", path + "/WEB-INF/jasper/");
      params.put("SUBREPORT_FILE", "/WEB-INF/jasper/subRelatorioUtilizacaoHorariosSalas.jasper");
      params.put(IReport.REPORT_PARAMETERS_KEY, pmtRel);

      ReportUtils.savePdfReport(getResponse(), getRequest(), params);
      getFacesContext().responseComplete();

      return null;
   }

   private void popularVo(RelatorioHorarioSalas solicitacao) {
      solicitacao.setDataInicial(this.dataInicial);
      solicitacao.setDataFinal(this.dataFinal);
      solicitacao.setIdPredio(this.idPredio);
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

   public void setLocale(Locale locale) {
      this.locale = locale;
   }

   public Locale getLocale() {
      return this.locale;
   }

   public Long getIdPredio() {
      return idPredio;
   }

   public void setIdPredio(Long idPredio) {
      this.idPredio = idPredio;
   }

}
