
package br.ufrn.ct.cronos.consultarturma.vo;

import java.io.Serializable;

public class DadosConsultarTurma implements Serializable {

   private static final long serialVersionUID = 1L;
   private Long id;
   private Long idTipo;
   private Long idPredio;
   private String docente;
   private String nomeDisciplina;
   private String tipoTurma;
   private String local;
   private String horario;
   private Boolean distribuir;
   private String numero;

   public DadosConsultarTurma() {
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

   /**
    * Recupera o valor do atributo idTipo
    * @return o idTipo
    */
   public Long getIdTipo() {
      return idTipo;
   }

   /**
    * Atribui o novo valor de idTipo
    * @param idTipo idTipo que será atribuído
    */
   public void setIdTipo(Long idTipo) {
      this.idTipo = idTipo;
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

   /**
    * Recupera o valor do atributo tipoTurma
    * @return o tipoTurma
    */
   public String getTipoTurma() {
      return tipoTurma;
   }

   /**
    * Atribui o novo valor de tipoTurma
    * @param tipoTurma tipoTurma que será atribuído
    */
   public void setTipoTurma(String tipoTurma) {
      this.tipoTurma = tipoTurma;
   }

   /**
    * Recupera o valor do atributo local
    * @return o local
    */
   public String getLocal() {
      return local;
   }

   /**
    * Atribui o novo valor de local
    * @param local local que será atribuído
    */
   public void setLocal(String local) {
      this.local = local;
   }

   /**
    * Recupera o valor do atributo horario
    * @return o horario
    */
   public String getHorario() {
      return horario;
   }

   /**
    * Atribui o novo valor de horario
    * @param horario horario que será atribuído
    */
   public void setHorario(String horario) {
      this.horario = horario;
   }

   /**
    * Recupera o valor do atributo distribuir
    * @return o distribuir
    */
   public Boolean getDistribuir() {
      return distribuir;
   }

   /**
    * Atribui o novo valor de distribuir
    * @param distribuir distribuir que será atribuído
    */
   public void setDistribuir(Boolean distribuir) {
      this.distribuir = distribuir;
   }

   /**
    * Recupera o valor do atributo numero
    * @return o numero
    */
   public String getNumero() {
      return numero;
   }

   /**
    * Atribui o novo valor de numero
    * @param numero numero que será atribuído
    */
   public void setNumero(String numero) {
      this.numero = numero;
   }

}
