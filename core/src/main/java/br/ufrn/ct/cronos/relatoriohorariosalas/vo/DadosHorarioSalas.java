
package br.ufrn.ct.cronos.relatoriohorariosalas.vo;

import java.io.Serializable;
import java.util.List;

public class DadosHorarioSalas implements Serializable {

   private static final long serialVersionUID = 1L;
   private String periodo = null;
   private String semestre = null;
   private String departamento = null;
   private String primeiraLinhaCabecalho = null;
   private String segundaLinhaCabecalho = null;
   private String terceiraLinhaCabecalho = null;
   private List<DadosHorario> dadosHorarios = null;

   public DadosHorarioSalas() {
      super();
   }

   /**
    * Recupera o valor do atributo periodo
    * @return o periodo
    */
   public String getPeriodo() {
      return periodo;
   }

   /**
    * Atribui o novo valor de periodo
    * @param periodo periodo que será atribuído
    */
   public void setPeriodo(String periodo) {
      this.periodo = periodo;
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
    * Recupera o valor do atributo dadosHorarios
    * @return o dadosHorarios
    */
   public List<DadosHorario> getDadosHorarios() {
      return dadosHorarios;
   }

   /**
    * Atribui o novo valor de dadosHorarios
    * @param dadosHorarios dadosHorarios que será atribuído
    */
   public void setDadosHorarios(List<DadosHorario> dadosHorarios) {
      this.dadosHorarios = dadosHorarios;
   }
}
