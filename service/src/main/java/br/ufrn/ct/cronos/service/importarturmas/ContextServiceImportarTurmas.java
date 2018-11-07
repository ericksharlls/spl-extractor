package br.ufrn.ct.cronos.service.importarturmas;

import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.factory.BeanFactory;

public class ContextServiceImportarTurmas {

   private IServiceImportarTurmas importarTurmas;
   private Integer idCentro;

   public ContextServiceImportarTurmas(Integer idCentro) {
      this.idCentro = idCentro;
      switch (idCentro) {
         case 442:
            this.importarTurmas = (IServiceImportarTurmas) BeanFactory.getInstance().getBean("ImportarTurmasCCHLAVersao02");
            break;
         case 445:
            this.importarTurmas = (IServiceImportarTurmas) BeanFactory.getInstance().getBean("ImportarTurmasCTVersao02");
            break;
         case 351:
            this.importarTurmas = (IServiceImportarTurmas) BeanFactory.getInstance().getBean("ImportarTurmasEAJ");
            break;
      }

   }

   public void executa(String clientId, String clientSecret) throws NegocioException {
      this.importarTurmas.valida();
      this.importarTurmas.processa(this.idCentro, clientId, clientSecret);
   }

}
