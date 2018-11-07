package br.ufrn.ct.cronos.entity;

import java.io.Serializable;

public class TurmaDocente implements Serializable {

   private static final long serialVersionUID = 1L;

   private Long idTurma, idDocente;

   public TurmaDocente() {
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
    * Recupera o valor do atributo idDocente
    * @return o idDocente
    */
   public Long getIdDocente() {
      return idDocente;
   }

   /**
    * Atribui o novo valor de idDocente
    * @param idDocente idDocente que será atribuído
    */
   public void setIdDocente(Long idDocente) {
      this.idDocente = idDocente;
   }

}
