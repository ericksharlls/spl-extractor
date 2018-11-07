
package br.ufrn.ct.cronos.relatorioturmasnaodistribuidas.vo;

import java.io.Serializable;

public class DadosTurma implements Serializable {

   private static final long serialVersionUID = 1L;
   private String disciplina = null;
   private String horario = null;
   private String docente = null;
   private Integer capacidade = null;
   private String numero = null;
   private Integer alunosMatriculados = null;

   public DadosTurma() {
      super();
   }

   public DadosTurma(String disciplina, String horario, String docente, Integer capacidade, String numero, Integer alunosMatriculados) {
      super();
      this.disciplina = disciplina;
      this.horario = horario;
      this.docente = docente;
      this.capacidade = capacidade;
      this.numero = numero;
      this.alunosMatriculados = alunosMatriculados;
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
    * Recupera o valor do atributo capacidade
    * @return o capacidade
    */
   public Integer getCapacidade() {
      return capacidade;
   }

   /**
    * Atribui o novo valor de capacidade
    * @param capacidade capacidade que será atribuído
    */
   public void setCapacidade(Integer capacidade) {
      this.capacidade = capacidade;
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

   /**
    * Recupera o valor do atributo alunosMatriculados
    * @return o alunosMatriculados
    */
   public Integer getAlunosMatriculados() {
      return alunosMatriculados;
   }

   /**
    * Atribui o novo valor de alunosMatriculados
    * @param alunosMatriculados alunosMatriculados que será atribuído
    */
   public void setAlunosMatriculados(Integer alunosMatriculados) {
      this.alunosMatriculados = alunosMatriculados;
   }

}
