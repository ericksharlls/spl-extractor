package br.ufrn.ct.cronos.consultarsala.vo;

import dev.home.componente.service.entity.RequestPaged;

public class ConsultarSala extends RequestPaged {

   private static final long serialVersionUID = 1L;

   private Long idPredio;

   public ConsultarSala() {
      super();
   }

   public Long getIdPredio() {
      return idPredio;
   }

   public void setIdPredio(Long idPredio) {
      this.idPredio = idPredio;
   }

}
