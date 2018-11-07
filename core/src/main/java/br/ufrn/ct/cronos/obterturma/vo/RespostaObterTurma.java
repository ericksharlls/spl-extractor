
package br.ufrn.ct.cronos.obterturma.vo;

import dev.home.componente.service.entity.ResponseUnique;

public class RespostaObterTurma extends ResponseUnique<DadosObterTurma> {

   private static final long serialVersionUID = 1L;

   public RespostaObterTurma() {
      super();
   }

   public RespostaObterTurma(DadosObterTurma objeto) {
      setObjeto(objeto);
   }

}
