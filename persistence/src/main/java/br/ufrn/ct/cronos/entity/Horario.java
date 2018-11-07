package br.ufrn.ct.cronos.entity;

import java.io.Serializable;
import java.util.Date;

public class Horario implements Serializable {

   private static final long serialVersionUID = 1L;

   private Long id;
   private Integer horario;
   private Date inicioHorario;
   private Date terminoHorario;
   private Date horarioIntermediario;
   private String turno;
   private Date inicioHorarioAbsoluto;
   private Date terminoHorarioAbsoluto;

   public Horario() {
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
    * Recupera o valor do atributo horario
    * @return o horario
    */
   public Integer getHorario() {
      return horario;
   }


   /**
    * Atribui o novo valor de horario
    * @param horario horario que será atribuído
    */
   public void setHorario(Integer horario) {
      this.horario = horario;
   }


   /**
    * Recupera o valor do atributo inicioHorario
    * @return o inicioHorario
    */
   public Date getInicioHorario() {
      return inicioHorario;
   }


   /**
    * Atribui o novo valor de inicioHorario
    * @param inicioHorario inicioHorario que será atribuído
    */
   public void setInicioHorario(Date inicioHorario) {
      this.inicioHorario = inicioHorario;
   }


   /**
    * Recupera o valor do atributo terminoHorario
    * @return o terminoHorario
    */
   public Date getTerminoHorario() {
      return terminoHorario;
   }


   /**
    * Atribui o novo valor de terminoHorario
    * @param terminoHorario terminoHorario que será atribuído
    */
   public void setTerminoHorario(Date terminoHorario) {
      this.terminoHorario = terminoHorario;
   }


   /**
    * Recupera o valor do atributo horarioIntermediario
    * @return o horarioIntermediario
    */
   public Date getHorarioIntermediario() {
      return horarioIntermediario;
   }


   /**
    * Atribui o novo valor de horarioIntermediario
    * @param horarioIntermediario horarioIntermediario que será atribuído
    */
   public void setHorarioIntermediario(Date horarioIntermediario) {
      this.horarioIntermediario = horarioIntermediario;
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

   /**
    * Recupera o valor do atributo inicioHorarioAbsoluto
    * @return o inicioHorarioAbsoluto
    */
   public Date getInicioHorarioAbsoluto() {
      return inicioHorarioAbsoluto;
   }

   /**
    * Atribui o novo valor de inicioHorarioAbsoluto
    * @param inicioHorarioAbsoluto inicioHorarioAbsoluto que será atribuído
    */
   public void setInicioHorarioAbsoluto(Date inicioHorarioAbsoluto) {
      this.inicioHorarioAbsoluto = inicioHorarioAbsoluto;
   }

   /**
    * Recupera o valor do atributo terminoHorarioAbsoluto
    * @return o terminoHorarioAbsoluto
    */
   public Date getTerminoHorarioAbsoluto() {
      return terminoHorarioAbsoluto;
   }

   /**
    * Atribui o novo valor de terminoHorarioAbsoluto
    * @param terminoHorarioAbsoluto terminoHorarioAbsoluto que será atribuído
    */
   public void setTerminoHorarioAbsoluto(Date terminoHorarioAbsoluto) {
      this.terminoHorarioAbsoluto = terminoHorarioAbsoluto;
   }
}
