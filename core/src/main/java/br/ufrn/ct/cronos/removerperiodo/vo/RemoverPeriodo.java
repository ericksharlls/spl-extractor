
package br.ufrn.ct.cronos.removerperiodo.vo;

import dev.home.componente.service.entity.Request;

public class RemoverPeriodo extends Request {

   private static final long serialVersionUID = 1L;
   private Long id;

   public RemoverPeriodo() {
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
