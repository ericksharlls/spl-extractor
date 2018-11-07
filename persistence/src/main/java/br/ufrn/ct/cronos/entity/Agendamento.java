
package br.ufrn.ct.cronos.entity;

import java.io.Serializable;
import java.util.Date;

public class Agendamento implements Serializable {

   private static final long serialVersionUID = 1L;
   private Long id;
   private String motivo;
   private Long idFuncionario;
   private Long idPeriodo;
   private Long idUsuarioSistema;
   private Date horaRealizacaoAgendamento;

   public Agendamento() {
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
    * Recupera o valor do atributo motivo
    * @return o motivo
    */
   public String getMotivo() {
      return motivo;
   }


   /**
    * Atribui o novo valor de motivo
    * @param motivo motivo que será atribuído
    */
   public void setMotivo(String motivo) {
      this.motivo = motivo;
   }


   /**
    * Recupera o valor do atributo idFuncionario
    * @return o idFuncionario
    */
   public Long getIdFuncionario() {
      return idFuncionario;
   }


   /**
    * Atribui o novo valor de idFuncionario
    * @param idFuncionario idFuncionario que será atribuído
    */
   public void setIdFuncionario(Long idFuncionario) {
      this.idFuncionario = idFuncionario;
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
    * Recupera o valor do atributo idUsuarioSistema
    * @return o idUsuarioSistema
    */
   public Long getIdUsuarioSistema() {
      return idUsuarioSistema;
   }

   /**
    * Atribui o novo valor de idUsuarioSistema
    * @param idUsuarioSistema idUsuarioSistema que será atribuído
    */
   public void setIdUsuarioSistema(Long idUsuarioSistema) {
      this.idUsuarioSistema = idUsuarioSistema;
   }

   /**
    * Recupera o valor do atributo horaRealizacaoAgendamento
    * @return o horaRealizacaoAgendamento
    */
   public Date getHoraRealizacaoAgendamento() {
      return horaRealizacaoAgendamento;
   }

   /**
    * Atribui o novo valor de horaRealizacaoAgendamento
    * @param horaRealizacaoAgendamento horaRealizacaoAgendamento que será atribuído
    */
   public void setHoraRealizacaoAgendamento(Date horaRealizacaoAgendamento) {
      this.horaRealizacaoAgendamento = horaRealizacaoAgendamento;
   }

}
