
package br.ufrn.ct.cronos.web.agendamento;

import dev.home.componente.web.infra.AbstractFormBean;

public class AgendamentoFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
   private String horarioTurma;

   public void setHorarioTurma(String horarioTurma) {
      this.horarioTurma = horarioTurma;
   }

   public String getHorarioTurma() {
      return this.horarioTurma;
   }
}
