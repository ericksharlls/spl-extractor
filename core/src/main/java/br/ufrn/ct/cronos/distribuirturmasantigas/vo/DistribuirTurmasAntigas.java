package br.ufrn.ct.cronos.distribuirturmasantigas.vo;

import dev.home.componente.service.entity.Request;

public class DistribuirTurmasAntigas extends Request {

   private static final long serialVersionUID = 1L;

   private Long idPeriodo;

   public DistribuirTurmasAntigas() {
      super();
   }

   public Long getIdPeriodo() {
      return idPeriodo;
   }

   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
   }

}
