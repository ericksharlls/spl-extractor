
package br.ufrn.ct.cronos.consultarsalasdisponiveis.vo;

import dev.home.componente.service.entity.Request;

public class ConsultarSalasDisponiveis extends Request {

   private static final long serialVersionUID = 1L;
   private Long idTurma;
   private Boolean desmembrarHorarioTurma;

   public ConsultarSalasDisponiveis() {
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
    * Recupera o valor do atributo desmembrarHorarioTurma
    * @return o desmembrarHorarioTurma
    */
   public Boolean getDesmembrarHorarioTurma() {
      return desmembrarHorarioTurma;
   }

   /**
    * Atribui o novo valor de desmembrarHorarioTurma
    * @param desmembrarHorarioTurma desmembrarHorarioTurma que será atribuído
    */
   public void setDesmembrarHorarioTurma(Boolean desmembrarHorarioTurma) {
      this.desmembrarHorarioTurma = desmembrarHorarioTurma;
   }
}
