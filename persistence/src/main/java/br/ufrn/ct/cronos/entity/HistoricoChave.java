
package br.ufrn.ct.cronos.entity;

import java.io.Serializable;
import java.util.Date;

public class HistoricoChave implements Serializable {

   private static final long serialVersionUID = 1L;
   private Long id;
   private Date horaRealizacao;
   private Long idOperacao;
   private Long idChave;
   private Long idUsuario;
   private Long idResponsavel;

   public HistoricoChave() {
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
    * Recupera o valor do atributo horaRealizacao
    * @return o horaRealizacao
    */
   public Date getHoraRealizacao() {
      return horaRealizacao;
   }

   /**
    * Atribui o novo valor de horaRealizacao
    * @param horaRealizacao horaRealizacao que será atribuído
    */
   public void setHoraRealizacao(Date horaRealizacao) {
      this.horaRealizacao = horaRealizacao;
   }

   /**
    * Recupera o valor do atributo idOperacao
    * @return o idOperacao
    */
   public Long getIdOperacao() {
      return idOperacao;
   }

   /**
    * Atribui o novo valor de idOperacao
    * @param idOperacao idOperacao que será atribuído
    */
   public void setIdOperacao(Long idOperacao) {
      this.idOperacao = idOperacao;
   }

   /**
    * Recupera o valor do atributo idChave
    * @return o idChave
    */
   public Long getIdChave() {
      return idChave;
   }

   /**
    * Atribui o novo valor de idChave
    * @param idChave idChave que será atribuído
    */
   public void setIdChave(Long idChave) {
      this.idChave = idChave;
   }

   /**
    * Recupera o valor do atributo idUsuario
    * @return o idUsuario
    */
   public Long getIdUsuario() {
      return idUsuario;
   }

   /**
    * Atribui o novo valor de idUsuario
    * @param idUsuario idUsuario que será atribuído
    */
   public void setIdUsuario(Long idUsuario) {
      this.idUsuario = idUsuario;
   }

   /**
    * Recupera o valor do atributo idResponsavel
    * @return o idResponsavel
    */
   public Long getIdResponsavel() {
      return idResponsavel;
   }

   /**
    * Atribui o novo valor de idResponsavel
    * @param idResponsavel idResponsavel que será atribuído
    */
   public void setIdResponsavel(Long idResponsavel) {
      this.idResponsavel = idResponsavel;
   }
}
