
package br.ufrn.ct.cronos.distribuirturmas.vo;

import dev.home.componente.service.entity.Request;

public class DistribuirTurmas extends Request {

   private static final long serialVersionUID = 1L;
   private Long idPredio;
   private Long idPeriodo;

   public DistribuirTurmas() {
      super();
   }

   /**
    * Recupera o valor do atributo idPredio
    * @return o idPredio
    */
   public Long getIdPredio() {
      return idPredio;
   }

   /**
    * Atribui o novo valor de idPredio
    * @param idPredio idPredio que será atribuído
    */
   public void setIdPredio(Long idPredio) {
      this.idPredio = idPredio;
   }

   public Long getIdPeriodo() {
      return idPeriodo;
   }

   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
   }

}
