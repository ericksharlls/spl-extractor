package br.ufrn.ct.cronos.consultarturma.vo;

import dev.home.componente.service.entity.RequestPaged;

public class ConsultarTurma extends RequestPaged {

   private static final long serialVersionUID = 1L;

   private String departamento;
   private Long idDepartamento;
   private Long idPeriodo;

   public ConsultarTurma() {
      super();
   }

   /**
    * Recupera o valor do atributo departamento
    * @return o departamento
    */
   public String getDepartamento() {
      return departamento;
   }

   /**
    * Atribui o novo valor de departamento
    * @param departamento departamento que será atribuído
    */
   public void setDepartamento(String departamento) {
      this.departamento = departamento;
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

}
