
package br.ufrn.ct.cronos.web.distribuirturmas;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.servlet.http.HttpServletRequest;
import br.ufrn.ct.cronos.consultarturma.vo.ConsultarTurma;
import br.ufrn.ct.cronos.consultarturma.vo.DadosConsultarTurma;
import br.ufrn.ct.cronos.consultarturma.vo.RespostaConsultarTurma;
import br.ufrn.ct.cronos.contadorturma.vo.ContadorTurma;
import br.ufrn.ct.cronos.contadorturma.vo.DadosContadorTurma;
import br.ufrn.ct.cronos.contadorturma.vo.RespostaContadorTurma;
import br.ufrn.ct.cronos.gravarturma.vo.GravarTurma;
import dev.home.componente.service.Service;
import dev.home.componente.service.entity.Request;
import dev.home.componente.service.entity.Response;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.util.ServiceUtils;
import dev.home.componente.web.infra.AbstractConsultarFormBean;
import dev.home.componente.web.infra.paginacao.PaginacaoDataModel;

public class AlterarTurmasFormBean extends AbstractConsultarFormBean {

   private static final long serialVersionUID = 1L;

   private static final String SELECT_ONE_RADIO = "selectOneRadioTurma";
   private static final String TABLE = "turmaTable";
   private static final String FORM = "turmaForm";
   private static final String SEPARADOR = ":";
   private Long idPeriodo, idDepartamento;
   private List<DadosConsultarTurma> objetos = null;

   public AlterarTurmasFormBean() throws NegocioException {
      viewTable();
   }

   public String gravar() throws NegocioException {
      for (DadosConsultarTurma dados : getTurmas(FORM + SEPARADOR + TABLE + SEPARADOR, SEPARADOR + SELECT_ONE_RADIO)) {
         GravarTurma solicitacao = new GravarTurma();

         solicitacao.setId(dados.getId());
         solicitacao.setIdTipo(dados.getIdTipo());
         solicitacao.setDistribuir(dados.getDistribuir());
         solicitacao.setIdPredio(dados.getIdPredio());

         Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
         service.executa(solicitacao);
      }

      addInfoMessage("Opera\u00E7\u00E3o realizada com sucesso.");

      return null;
   }

   public DataModel getDados() throws NegocioException {
      ConsultarTurma solicitacao = new ConsultarTurma();
      popularVo(solicitacao);
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaConsultarTurma resposta = (RespostaConsultarTurma) service.executa(solicitacao);
      setObjetos(resposta.getDados());
      return new PaginacaoDataModel(getObjetos(), resposta.getTotalNumeroLinhas());
   }

   private void viewTable() throws NegocioException {
      ContadorTurma solicitacao = new ContadorTurma();
      Service<Response, Request> service = ServiceUtils.getService(solicitacao.getClass().getSimpleName());
      RespostaContadorTurma resposta = (RespostaContadorTurma) service.executa(solicitacao);
      setVisible(((DadosContadorTurma) resposta.getObjeto()).getTotal() > 0);
   }

   private void popularVo(ConsultarTurma solicitacao) {
      solicitacao.setMaxPage(getTable().getRows());
      solicitacao.setStartPage(getTable().getFirst());
      if (this.idPeriodo != null) {
         solicitacao.setIdPeriodo(this.idPeriodo);
      } else {
         solicitacao.setIdPeriodo(new Long(0));
      }
      if (this.idDepartamento != null) {
         solicitacao.setIdDepartamento(this.idDepartamento);
      } else {
         solicitacao.setIdDepartamento(new Long(0));
      }

   }

   public int getLinhas() {
      return 12;
   }

   private List<DadosConsultarTurma> getTurmas(String startTag, String endTag) {
      Vector<DadosConsultarTurma> retorno = new Vector<DadosConsultarTurma>();
      Enumeration<?> e = getParameterNamesEnumeration(startTag, endTag);
      while (e.hasMoreElements()) {
         String str = (String) e.nextElement();
         if (str.startsWith(startTag) && str.endsWith(endTag)) {
            int index = Integer.parseInt(str.substring(startTag.length(), str.indexOf(endTag, startTag.length())));
            index = index - getTable().getFirst();
            retorno.add((this.objetos.get(index)));
         }
      }

      return retorno;
   }

   protected Enumeration<?> getParameterNamesEnumeration(String startTag, String endTag) {
      return ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getParameterNames();
   }

   public void setObjetos(List<DadosConsultarTurma> objetos) {
      this.objetos = objetos;
   }

   public List<DadosConsultarTurma> getObjetos() {
      return this.objetos;
   }

   public Long getIdPeriodo() {
      return idPeriodo;
   }

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
