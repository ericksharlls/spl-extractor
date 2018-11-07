
package br.ufrn.ct.cronos.consultarturmaporcodigoedocente.vo;

import dev.home.componente.service.entity.RequestPaged;

public class ConsultarTurmaPorCodigoEDocente extends RequestPaged {

   private static final long serialVersionUID = 1L;
   private Long idPeriodo;
   private String codigo;
   private String docente;
   private String nomeDisciplina;

   public ConsultarTurmaPorCodigoEDocente(){
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
    * Recupera o valor do atributo codigo
    * @return o codigo
    */
   public String getCodigo() {
      return codigo;
   }

   /**
    * Atribui o novo valor de codigo
    * @param codigo codigo que será atribuído
    */
   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   /**
    * Recupera o valor do atributo docente
    * @return o docente
    */
   public String getDocente() {
      return docente;
   }

   /**
    * Atribui o novo valor de docente
    * @param docente docente que será atribuído
    */
   public void setDocente(String docente) {
      this.docente = docente;
   }

   /**
    * Recupera o valor do atributo nomeDisciplina
    * @return o nomeDisciplina
    */
   public String getNomeDisciplina() {
      return nomeDisciplina;
   }

   /**
    * Atribui o novo valor de nomeDisciplina
    * @param nomeDisciplina nomeDisciplina que será atribuído
    */
   public void setNomeDisciplina(String nomeDisciplina) {
      this.nomeDisciplina = nomeDisciplina;
   }
}
