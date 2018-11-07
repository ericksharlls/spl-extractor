package br.ufrn.ct.cronos.web.distribuirturmas;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import br.ufrn.ct.cronos.service.distribuirturmas.ContextServiceDistribuirTurmas;
import br.ufrn.ct.cronos.web.ControlNavigationFormBean;
import br.ufrn.ct.cronos.web.PropertiesLoader;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.web.infra.AbstractFormBean;

public class DistribuirTurmasFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
   private Long idPredio;
   private Long idPeriodo;
   private ContextServiceDistribuirTurmas contextoDistribuicaoTurmas;

   public String distribuirTurmas() throws NegocioException {

      this.contextoDistribuicaoTurmas =
         new ContextServiceDistribuirTurmas(PropertiesLoader.propertiesLoader().getProperty("ServiceDistribuirTurmas"));
      this.contextoDistribuicaoTurmas.executa(this.idPeriodo, this.idPredio);

      FacesContext.getCurrentInstance().addMessage("distribuicaoTurmasForm",
         new FacesMessage(FacesMessage.SEVERITY_INFO, "Opera\u00E7\u00E3o realizada com sucesso.", null));

      return null;
   }

   public String cancelar() {
      return ControlNavigationFormBean.pageConsultarPredio();
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
