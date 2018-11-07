
package br.ufrn.ct.cronos.obtersemestreletivo.vo;

import dev.home.componente.service.entity.ResponseUnique;

public class RespostaObterSemestreLetivo extends ResponseUnique<DadosObterSemestreLetivo> {

   private static final long serialVersionUID = 1L;

   public RespostaObterSemestreLetivo() {
      super();
   }

   public RespostaObterSemestreLetivo(DadosObterSemestreLetivo objeto) {
      setObjeto(objeto);
   }
}
