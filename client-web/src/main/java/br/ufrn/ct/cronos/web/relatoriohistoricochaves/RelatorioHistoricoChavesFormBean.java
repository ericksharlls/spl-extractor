package br.ufrn.ct.cronos.web.relatoriohistoricochaves;

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
import br.ufrn.ct.cronos.listarsalaporpredio.vo.DadosListarSalaPorPredio;
import br.ufrn.ct.cronos.listarsalaporpredio.vo.ListarSalaPorPredio;
import br.ufrn.ct.cronos.listarsalaporpredio.vo.RespostaListarSalaPorPredio;
import br.ufrn.ct.cronos.relatoriohistoricochave.vo.RelatorioHistoricoChave;
import br.ufrn.ct.cronos.relatoriohistoricochave.vo.RespostaRelatorioHistoricoChave;
import br.ufrn.ct.cronos.relatoriohistoricochaveporsala.vo.DadosHistoricoChavePorSala;
import br.ufrn.ct.cronos.relatoriohistoricochaveporsala.vo.RelatorioHistoricoChavePorSala;
import br.ufrn.ct.cronos.relatoriohistoricochaveporsala.vo.RespostaRelatorioHistoricoChavePorSala;
import br.ufrn.ct.cronos.web.PropertiesLoader;
import br.ufrn.ct.cronos.web.ReportUtils;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractFormBean;
import dev.home.componente.web.relatorio.IReport;

public class RelatorioHistoricoChavesFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;

   private Date data;
   private Locale locale = new Locale("pt", "BR");

   private Long idSala;
   private Long idPredio;

   public RelatorioHistoricoChavesFormBean() {
      super();
      this.idPredio = new Long(0);
      this.idSala = new Long(0);
      this.data = new Date();
   }

   public String gerarRelatorioEmArquivoPDF() throws NegocioException, IOException {
      RelatorioHistoricoChave solicitacao = new RelatorioHistoricoChave();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaRelatorioHistoricoChave resposta = (RespostaRelatorioHistoricoChave) service.executa(solicitacao);

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

      params.put(IReport.REPORT_FILENAME_KEY, "relatorioHistoricoChaves");
      params.put(IReport.REPORT_FILE_KEY, "/WEB-INF/jasper/relatorioControleChaves.jasper");
      params.put(IReport.REPORT_DATA_HTML_KEY, resposta.getDados());
      params.put("SUBREPORT_DIR", path + "/WEB-INF/jasper/");
      params.put("SUBREPORT_FILE", "/WEB-INF/jasper/subrelatorioControleChaves.jasper");
      params.put(IReport.REPORT_PARAMETERS_KEY, pmtRel);

      ReportUtils.savePdfReport(getResponse(), getRequest(), params);
      getFacesContext().responseComplete();

      return null;
   }

   public List<DadosHistoricoChavePorSala> getDados() throws NegocioException {
      RelatorioHistoricoChavePorSala solicitacao = new RelatorioHistoricoChavePorSala();
      popularVoHistoricoChavePorSala(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaRelatorioHistoricoChavePorSala resposta = (RespostaRelatorioHistoricoChavePorSala) service.executa(solicitacao);
      return resposta.getDados();
   }

   public String gerarRelatorioHistoricoChaves() {
      return null;
   }

   private void popularVo(RelatorioHistoricoChave solicitacao) {
      solicitacao.setData(this.data);
   }

   private void popularVoHistoricoChavePorSala(RelatorioHistoricoChavePorSala solicitacao) {
      solicitacao.setData(this.data);
      solicitacao.setIdSala(this.idSala);
   }

   public List<SelectItem> getSalasPorPredio() throws NegocioException {
      List<SelectItem> itens = new ArrayList<SelectItem>();
      ListarSalaPorPredio solicitacao = new ListarSalaPorPredio();
      solicitacao.setIdPredio(this.getIdPredio());
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

   /**
    * Recupera o valor do atributo data
    * @return o data
    */
   public Date getData() {
      return data;
   }

   /**
    * Atribui o novo valor de data
    * @param dataInicial data que será atribuído
    */
   public void setData(Date data) {
      this.data = data;
   }

   /**
    * Recupera o valor do atributo locale
    * @return o locale
    */
   public Locale getLocale() {
      return locale;
   }

   /**
    * Atribui o novo valor de locale
    * @param locale locale que será atribuído
    */
   public void setLocale(Locale locale) {
      this.locale = locale;
   }

   public Long getIdSala() {
      return idSala;
   }

   public void setIdSala(Long idSala) {
      this.idSala = idSala;
   }

   public Long getIdPredio() {
      return idPredio;
   }

   public void setIdPredio(Long idPredio) {
      this.idPredio = idPredio;
   }
}
