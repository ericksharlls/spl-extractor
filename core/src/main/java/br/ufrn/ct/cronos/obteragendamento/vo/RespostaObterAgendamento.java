
package br.ufrn.ct.cronos.obteragendamento.vo;

import dev.home.componente.service.entity.ResponseUnique;

public class RespostaObterAgendamento extends ResponseUnique<DadosObterAgendamento> {

   private static final long serialVersionUID = 1L;

   public RespostaObterAgendamento() {
      super();
   }

   public RespostaObterAgendamento(DadosObterAgendamento objeto) {
      setObjeto(objeto);
   }
}
