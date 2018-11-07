
package br.ufrn.ct.cronos.alocarturma.vo;

import dev.home.componente.service.entity.Request;

public class AlocarTurma extends Request {

   private static final long serialVersionUID = 1L;
   private Long idTurma;
   private Long idSala;
   private String horario;
   private Boolean horarioDesmembrado;

   public AlocarTurma() {
      super();
   }

   /**
    * Recupera o valor do atributo idTurma
    * @return o idTurma
    */
   public Long getIdTurma() {
      return idTurma;
   }

   /**
    * Atribui o novo valor de idTurma
    * @param idTurma idTurma que será atribuído
    */
   public void setIdTurma(Long idTurma) {
      this.idTurma = idTurma;
   }

   /**
    * Recupera o valor do atributo idSala
    * @return o idSala
    */
   public Long getIdSala() {
      return idSala;
   }

   /**
    * Atribui o novo valor de idSala
    * @param idSala idSala que será atribuído
    */
   public void setIdSala(Long idSala) {
      this.idSala = idSala;
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
    * Recupera o valor do atributo horarioDesmembrado
    * @return o horarioDesmembrado
    */
   public Boolean getHorarioDesmembrado() {
      return horarioDesmembrado;
   }

   /**
    * Atribui o novo valor de horarioDesmembrado
    * @param horarioDesmembrado horarioDesmembrado que será atribuído
    */
   public void setHorarioDesmembrado(Boolean horarioDesmembrado) {
      this.horarioDesmembrado = horarioDesmembrado;
   }
}
