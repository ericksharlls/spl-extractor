package br.ufrn.ct.cronos.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisponibilidadeSala implements Serializable {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   private Long id;
   private Date dataReserva;
   private Long idPeriodo;
   private Long idSala;
   private Long idHorarioSala;
   private Long idTurma;
   private Long idAgendamento;

   public DisponibilidadeSala() {
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
    * Recupera o valor do atributo dataReserva
    * @return o dataReserva
    */
   public Date getDataReserva() {
      return dataReserva;
   }

   /**
    * Atribui o novo valor de dataReserva
    * @param dataReserva dataReserva que será atribuído
    */
   public void setDataReserva(Date dataReserva) {
      this.dataReserva = dataReserva;
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
    * Recupera o valor do atributo idHorarioSala
    * @return o idHorarioSala
    */
   public Long getIdHorarioSala() {
      return idHorarioSala;
   }

   /**
    * Atribui o novo valor de idHorarioSala
    * @param idHorarioSala idHorarioSala que será atribuído
    */
   public void setIdHorarioSala(Long idHorarioSala) {
      this.idHorarioSala = idHorarioSala;
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
    * Recupera o valor do atributo idAgendamento
    * @return o idAgendamento
    */
   public Long getIdAgendamento() {
      return idAgendamento;
   }

   /**
    * Atribui o novo valor de idAgendamento
    * @param idAgendamento idAgendamento que será atribuído
    */
   public void setIdAgendamento(Long idAgendamento) {
      this.idAgendamento = idAgendamento;
   }
   
   public String getDataFormatoBrasileiro() {
      DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      return formatter.format(this.dataReserva);
   }
}
