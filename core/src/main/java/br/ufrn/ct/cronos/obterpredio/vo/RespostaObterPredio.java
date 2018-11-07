
package br.ufrn.ct.cronos.obterpredio.vo;

import dev.home.componente.service.entity.ResponseUnique;

public class RespostaObterPredio extends ResponseUnique<DadosObterPredio> {

   private static final long serialVersionUID = 1L;

   public RespostaObterPredio() {
      super();
   }

   public RespostaObterPredio(DadosObterPredio objeto) {
      setObjeto(objeto);
   }
}
