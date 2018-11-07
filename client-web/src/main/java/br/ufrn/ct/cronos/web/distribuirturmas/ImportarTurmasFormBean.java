package br.ufrn.ct.cronos.web.distribuirturmas;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import br.ufrn.ct.cronos.service.importarturmas.ContextServiceImportarTurmas;
import br.ufrn.ct.cronos.web.PropertiesLoader;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.web.infra.AbstractFormBean;

public class ImportarTurmasFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
   private ContextServiceImportarTurmas contextoImportacaoTurmas;

   public String importarTurmas() throws NegocioException {
      this.contextoImportacaoTurmas =
         new ContextServiceImportarTurmas(Integer.valueOf(PropertiesLoader.propertiesLoader().getProperty("IdCentro")));
      this.contextoImportacaoTurmas.executa(PropertiesLoader.propertiesLoader().getProperty("clientId"), PropertiesLoader
               .propertiesLoader().getProperty("clientSecret"));

      FacesContext.getCurrentInstance().addMessage("importacaoTurmasForm",
         new FacesMessage(FacesMessage.SEVERITY_INFO, "Opera\u00E7\u00E3o realizada com sucesso.", null));

      return null;
   }

}
