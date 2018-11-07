
package br.ufrn.ct.cronos.permutarturma.vo;

import dev.home.componente.service.entity.ResponseUnique;

public class RespostaObterTurmaCompleta extends ResponseUnique<DadosObterTurmaCompleta> {

   private static final long serialVersionUID = 1L;

   public RespostaObterTurmaCompleta() {
      super();
   }

   public RespostaObterTurmaCompleta(DadosObterTurmaCompleta objeto) {
      setObjeto(objeto);
   }
}
