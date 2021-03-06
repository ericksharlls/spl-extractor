package br.ufrn.ct.cronos.relatorioocupacaohorariossalas.vo;

import dev.home.componente.service.entity.Request;

public class RelatorioOcupacaoHorariosSalas extends Request {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   private Long idPeriodo, idPredio;

   public RelatorioOcupacaoHorariosSalas() {
      super();
   }

   /**
    * Recupera o valor do atributo idPeriodo
    * @return o idPeriodo
    */
   public Long getIdPeriodo() {
      return idPeriodo;
   }

   /**
    * Atribui o novo valor de idPeriodo
    * @param idPeriodo idPeriodo que será atribuído
    */
   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
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
