
package br.ufrn.ct.cronos.obterfuncionario.vo;

import dev.home.componente.service.entity.Request;

public class ObterFuncionario extends Request {

   private static final long serialVersionUID = 1L;
   private Long id;

   public ObterFuncionario() {
      super();
   }

   public ObterFuncionario(Long id) {
      this.id = id;
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long id) {
      this.id = id;
   }
}
