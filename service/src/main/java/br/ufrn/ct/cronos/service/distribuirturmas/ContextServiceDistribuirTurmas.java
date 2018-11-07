package br.ufrn.ct.cronos.service.distribuirturmas;

import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.service.factory.BeanFactory;

public class ContextServiceDistribuirTurmas {

   private IServiceDistribuirTurmas distribuirTurmas;

   public ContextServiceDistribuirTurmas(String nameServiceDistribuirTurmas) {
      this.distribuirTurmas = (IServiceDistribuirTurmas) BeanFactory.getInstance().getBean(nameServiceDistribuirTurmas);
   }

   public void executa(Long idPeriodo, Long idPredio) throws NegocioException {
      this.distribuirTurmas.valida(idPeriodo, idPredio);
      this.distribuirTurmas.processa(idPeriodo, idPredio);
   }

}
