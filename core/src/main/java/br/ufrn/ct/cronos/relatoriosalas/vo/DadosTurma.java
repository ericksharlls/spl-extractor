package br.ufrn.ct.cronos.relatoriosalas.vo;

import java.io.Serializable;

public class DadosTurma implements Serializable {

   private static final long serialVersionUID = 1L;
   private String horario = null;
   private String horarioSegunda = null;
   private String horarioTerca = null;
   private String horarioQuarta = null;
   private String horarioQuinta = null;
   private String horarioSexta = null;
   private String horarioSabado = null;

   public DadosTurma() {
      super();
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
    * Recupera o valor do atributo horarioSegunda
    * @return o horarioSegunda
    */
   public String getHorarioSegunda() {
      return horarioSegunda;
   }

   /**
    * Atribui o novo valor de horarioSegunda
    * @param horarioSegunda horarioSegunda que será atribuído
    */
   public void setHorarioSegunda(String horarioSegunda) {
      this.horarioSegunda = horarioSegunda;
   }

   /**
    * Recupera o valor do atributo horarioTerca
    * @return o horarioTerca
    */
   public String getHorarioTerca() {
      return horarioTerca;
   }

   /**
    * Atribui o novo valor de horarioTerca
    * @param horarioTerca horarioTerca que será atribuído
    */
   public void setHorarioTerca(String horarioTerca) {
      this.horarioTerca = horarioTerca;
   }

   /**
    * Recupera o valor do atributo horarioQuarta
    * @return o horarioQuarta
    */
   public String getHorarioQuarta() {
      return horarioQuarta;
   }

   /**
    * Atribui o novo valor de horarioQuarta
    * @param horarioQuarta horarioQuarta que será atribuído
    */
   public void setHorarioQuarta(String horarioQuarta) {
      this.horarioQuarta = horarioQuarta;
   }

   /**
    * Recupera o valor do atributo horarioQuinta
    * @return o horarioQuinta
    */
   public String getHorarioQuinta() {
      return horarioQuinta;
   }

   /**
    * Atribui o novo valor de horarioQuinta
    * @param horarioQuinta horarioQuinta que será atribuído
    */
   public void setHorarioQuinta(String horarioQuinta) {
      this.horarioQuinta = horarioQuinta;
   }

   /**
    * Recupera o valor do atributo horarioSexta
    * @return o horarioSexta
    */
   public String getHorarioSexta() {
      return horarioSexta;
   }

   /**
    * Atribui o novo valor de horarioSexta
    * @param horarioSexta horarioSexta que será atribuído
    */
   public void setHorarioSexta(String horarioSexta) {
      this.horarioSexta = horarioSexta;
   }

   /**
    * Recupera o valor do atributo horarioSabado
    * @return o horarioSabado
    */
   public String getHorarioSabado() {
      return horarioSabado;
   }

   /**
    * Atribui o novo valor de horarioSabado
    * @param horarioSabado horarioSabado que será atribuído
    */
   public void setHorarioSabado(String horarioSabado) {
      this.horarioSabado = horarioSabado;
   }

}
