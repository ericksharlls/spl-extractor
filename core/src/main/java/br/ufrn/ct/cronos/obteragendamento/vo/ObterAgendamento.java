
package br.ufrn.ct.cronos.obteragendamento.vo;

import dev.home.componente.service.entity.Request;

public class ObterAgendamento extends Request {

   private static final long serialVersionUID = 1L;
   private Long id;

   public ObterAgendamento() {
      super();
   }

   /**
    * Recupera o valor do atributo id
    * @return o id
    */
   public Long getId() {
      return id;
   }

   /**
    * Atribui o novo valor de id
    * @param id id que será atribuído
    */
   public void setId(Long id) {
      this.id = id;
   }

}
