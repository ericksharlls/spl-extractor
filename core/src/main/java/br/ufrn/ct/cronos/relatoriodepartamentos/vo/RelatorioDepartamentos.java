
package br.ufrn.ct.cronos.relatoriodepartamentos.vo;

import dev.home.componente.service.entity.Request;

public class RelatorioDepartamentos extends Request {

   private static final long serialVersionUID = 1L;
   private Long idPredio, idPeriodo, idDepartamento;

   public RelatorioDepartamentos() {
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
    * Recupera o valor do atributo idDepartamento
    * @return o idDepartamento
    */
   public Long getIdDepartamento() {
      return idDepartamento;
   }

   /**
    * Atribui o novo valor de idDepartamento
    * @param idDepartamento idDepartamento que será atribuído
    */
   public void setIdDepartamento(Long idDepartamento) {
      this.idDepartamento = idDepartamento;
   }
}
