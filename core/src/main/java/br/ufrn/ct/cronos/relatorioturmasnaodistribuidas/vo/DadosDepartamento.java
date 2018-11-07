
package br.ufrn.ct.cronos.relatorioturmasnaodistribuidas.vo;

import java.io.Serializable;
import java.util.List;

public class DadosDepartamento implements Serializable {

   private static final long serialVersionUID = 1L;
   private String semestre = null;
   private String departamento = null;
   private String primeiraLinhaCabecalho = null;
   private String segundaLinhaCabecalho = null;
   private String terceiraLinhaCabecalho = null;
   private List<DadosTurma> turmas;

   public DadosDepartamento() {
      super();
   }

   /**
    * Recupera o valor do atributo semestre
    * @return o semestre
    */
   public String getSemestre() {
      return semestre;
   }

   /**
    * Atribui o novo valor de semestre
    * @param semestre semestre que será atribuído
    */
   public void setSemestre(String semestre) {
      this.semestre = semestre;
   }

   /**
    * Recupera o valor do atributo departamento
    * @return o departamento
    */
   public String getDepartamento() {
      return departamento;
   }

   /**
    * Atribui o novo valor de departamento
    * @param departamento departamento que será atribuído
    */
   public void setDepartamento(String departamento) {
      this.departamento = departamento;
   }

   /**
    * Recupera o valor do atributo primeiraLinhaCabecalho
    * @return o primeiraLinhaCabecalho
    */
   public String getPrimeiraLinhaCabecalho() {
      return primeiraLinhaCabecalho;
   }

   /**
    * Atribui o novo valor de primeiraLinhaCabecalho
    * @param primeiraLinhaCabecalho primeiraLinhaCabecalho que será atribuído
    */
   public void setPrimeiraLinhaCabecalho(String primeiraLinhaCabecalho) {
      this.primeiraLinhaCabecalho = primeiraLinhaCabecalho;
   }

   /**
    * Recupera o valor do atributo segundaLinhaCabecalho
    * @return o segundaLinhaCabecalho
    */
   public String getSegundaLinhaCabecalho() {
      return segundaLinhaCabecalho;
   }

   /**
    * Atribui o novo valor de segundaLinhaCabecalho
    * @param segundaLinhaCabecalho segundaLinhaCabecalho que será atribuído
    */
   public void setSegundaLinhaCabecalho(String segundaLinhaCabecalho) {
      this.segundaLinhaCabecalho = segundaLinhaCabecalho;
   }

   /**
    * Recupera o valor do atributo terceiraLinhaCabecalho
    * @return o terceiraLinhaCabecalho
    */
   public String getTerceiraLinhaCabecalho() {
      return terceiraLinhaCabecalho;
   }

   /**
    * Atribui o novo valor de terceiraLinhaCabecalho
    * @param terceiraLinhaCabecalho terceiraLinhaCabecalho que será atribuído
    */
   public void setTerceiraLinhaCabecalho(String terceiraLinhaCabecalho) {
      this.terceiraLinhaCabecalho = terceiraLinhaCabecalho;
   }

   /**
    * Recupera o valor do atributo turmas
    * @return o turmas
    */
   public List<DadosTurma> getTurmas() {
      return turmas;
   }

   /**
    * Atribui o novo valor de turmas
    * @param turmas turmas que será atribuído
    */
   public void setTurmas(List<DadosTurma> turmas) {
      this.turmas = turmas;
   }
}
