
package br.ufrn.ct.cronos.listarsalaporpredio.vo;

import dev.home.componente.service.entity.Request;

public class ListarSalaPorPredio extends Request {

   private static final long serialVersionUID = 1L;
   private Long idPredio;

   public ListarSalaPorPredio() {
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

}
