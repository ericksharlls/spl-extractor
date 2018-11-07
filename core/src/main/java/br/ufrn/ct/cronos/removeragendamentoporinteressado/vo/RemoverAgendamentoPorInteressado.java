
package br.ufrn.ct.cronos.removeragendamentoporinteressado.vo;

import dev.home.componente.service.entity.Request;

public class RemoverAgendamentoPorInteressado extends Request {

   private static final long serialVersionUID = 1L;
   private Long idAgendamento;

   public RemoverAgendamentoPorInteressado() {
      super();
   }

   /**
    * Recupera o valor do atributo idAgendamento
    * @return o idAgendamento
    */
   public Long getIdAgendamento() {
      return idAgendamento;
   }

   /**
    * Atribui o novo valor de idAgendamento
    * @param idAgendamento idAgendamento que será atribuído
    */
   public void setIdAgendamento(Long idAgendamento) {
      this.idAgendamento = idAgendamento;
   }

}
