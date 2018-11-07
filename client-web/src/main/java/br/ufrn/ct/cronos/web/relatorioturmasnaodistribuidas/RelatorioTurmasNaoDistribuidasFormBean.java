package br.ufrn.ct.cronos.web.relatorioturmasnaodistribuidas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import br.ufrn.ct.cronos.relatorioturmasnaodistribuidas.vo.RelatorioTurmasNaoDistribuidas;
import br.ufrn.ct.cronos.relatorioturmasnaodistribuidas.vo.RespostaRelatorioTurmasNaoDistribuidas;
import br.ufrn.ct.cronos.web.PropertiesLoader;
import br.ufrn.ct.cronos.web.ReportUtils;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;
import dev.home.componente.web.relatorio.IReport;

public class RelatorioTurmasNaoDistribuidasFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
   private String departamento;
   private Long idPredio, idPeriodo, idDepartamento;


   public String gerarRelatorioTurmasNaoDistribuidas() throws NegocioException, FileNotFoundException {
      RelatorioTurmasNaoDistribuidas solicitacao = new RelatorioTurmasNaoDistribuidas();
      solicitacao.setIdPeriodo(this.idPeriodo);
      solicitacao.setIdPredio(this.idPredio);
      solicitacao.setIdDepartamento(this.idDepartamento);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaRelatorioTurmasNaoDistribuidas resposta = (RespostaRelatorioTurmasNaoDistribuidas) service.executa(solicitacao);

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

      pmtRel.put("SUBREPORT_DIR", path + "/");

      params.put(IReport.REPORT_FILENAME_KEY, "relatorioTurmasNaoDistribuidas");
      params.put(IReport.REPORT_FILE_KEY, "/WEB-INF/jasper/relatorioTurmasNaoDistribuidas.jasper");
      params.put(IReport.REPORT_DATA_HTML_KEY, resposta.getDados());
      params.put(IReport.REPORT_PARAMETERS_KEY, pmtRel);
      ReportUtils.savePdfReport(getResponse(), getRequest(), params);
      getFacesContext().responseComplete();

      return null;
   }

   public String cancelar() {
      return null;
   }

   public void setDepartamento(String departamento) {
      this.departamento = departamento;
   }

   public String getDepartamento() {
      return this.departamento;
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

   public Long getIdDepartamento() {
      return idDepartamento;
   }

   public void setIdDepartamento(Long idDepartamento) {
      this.idDepartamento = idDepartamento;
   }

}
