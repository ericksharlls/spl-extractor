
package br.ufrn.ct.cronos.relatoriodepartamentos.vo;

import java.io.Serializable;

public class DadosTurma implements Serializable {

   private static final long serialVersionUID = 1L;

   private String disciplina = null;
   private String horario = null;
   private String docente = null;
   private String local = null;
   private String numero = null;

   public DadosTurma() {
      super();
   }


   /**
    * Recupera o valor do atributo disciplina
    * @return o disciplina
    */
   public String getDisciplina() {
      return disciplina;
   }


   /**
    * Atribui o novo valor de disciplina
    * @param disciplina disciplina que será atribuído
    */
   public void setDisciplina(String disciplina) {
      this.disciplina = disciplina;
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
    * Recupera o valor do atributo docente
    * @return o docente
    */
   public String getDocente() {
      return docente;
   }


   /**
    * Atribui o novo valor de docente
    * @param docente docente que será atribuído
    */
   public void setDocente(String docente) {
      this.docente = docente;
   }


   /**
    * Recupera o valor do atributo local
    * @return o local
    */
   public String getLocal() {
      return local;
   }


   /**
    * Atribui o novo valor de local
    * @param local local que será atribuído
    */
   public void setLocal(String local) {
      this.local = local;
   }


   /**
    * Recupera o valor do atributo numero
    * @return o numero
    */
   public String getNumero() {
      return numero;
   }


   /**
    * Atribui o novo valor de numero
    * @param numero numero que será atribuído
    */
   public void setNumero(String numero) {
      this.numero = numero;
   }
}
