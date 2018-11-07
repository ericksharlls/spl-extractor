
package br.ufrn.ct.cronos.permutarturma.vo;

import dev.home.componente.service.entity.Request;

public class PermutarTurma extends Request {

   private static final long serialVersionUID = 1L;
   private Long idTurma1;
   private Long idTurma2;

   public PermutarTurma() {
      super();
   }

   /**
    * Recupera o valor do atributo idTurma1
    * @return o idTurma1
    */
   public Long getIdTurma1() {
      return idTurma1;
   }

   /**
    * Atribui o novo valor de idTurma1
    * @param idTurma1 idTurma1 que será atribuído
    */
   public void setIdTurma1(Long idTurma1) {
      this.idTurma1 = idTurma1;
   }

   /**
    * Recupera o valor do atributo idTurma2
    * @return o idTurma2
    */
   public Long getIdTurma2() {
      return idTurma2;
   }

   /**
    * Atribui o novo valor de idTurma2
    * @param idTurma2 idTurma2 que será atribuído
    */
   public void setIdTurma2(Long idTurma2) {
      this.idTurma2 = idTurma2;
   }

}
