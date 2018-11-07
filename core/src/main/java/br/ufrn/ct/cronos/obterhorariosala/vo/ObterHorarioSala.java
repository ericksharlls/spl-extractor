
package br.ufrn.ct.cronos.obterhorariosala.vo;

import dev.home.componente.service.entity.Request;

public class ObterHorarioSala extends Request {

   private static final long serialVersionUID = 1L;
   private Long idSala, idPeriodo;
   private String turno;

   public ObterHorarioSala() {
      super();
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
    * Recupera o valor do atributo turno
    * @return o turno
    */
   public String getTurno() {
      return turno;
   }

   /**
    * Atribui o novo valor de turno
    * @param turno turno que será atribuído
    */
   public void setTurno(String turno) {
      this.turno = turno;
   }
}
