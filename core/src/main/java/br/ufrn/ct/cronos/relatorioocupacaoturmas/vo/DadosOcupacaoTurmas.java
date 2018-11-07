
package br.ufrn.ct.cronos.relatorioocupacaoturmas.vo;

import java.io.Serializable;
import java.util.List;

public class DadosOcupacaoTurmas implements Serializable {

   private static final long serialVersionUID = 1L;
   private String turma = null;
   private String chavesUtilizadas = null;
   private String periodo = null;
   private String semestre = null;
   private String turno = null;
   private String primeiraLinhaCabecalho = null;
   private String segundaLinhaCabecalho = null;
   private String terceiraLinhaCabecalho = null;
   private List<DadosOcupacao> dadosOcupacoes;

   public DadosOcupacaoTurmas() {
      super();
   }

   /**
    * Recupera o valor do atributo turma
    * @return o turma
    */
   public String getTurma() {
      return turma;
   }

   /**
    * Atribui o novo valor de turma
    * @param turma turma que será atribuído
    */
   public void setTurma(String turma) {
      this.turma = turma;
   }

   /**
    * Recupera o valor do atributo chavesUtilizadas
    * @return o chavesUtilizadas
    */
   public String getChavesUtilizadas() {
      return chavesUtilizadas;
   }

   /**
    * Atribui o novo valor de chavesUtilizadas
    * @param chavesUtilizadas chavesUtilizadas que será atribuído
    */
   public void setChavesUtilizadas(String chavesUtilizadas) {
      this.chavesUtilizadas = chavesUtilizadas;
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
    * Recupera o valor do atributo dadosOcupacoes
    * @return o dadosOcupacoes
    */
   public List<DadosOcupacao> getDadosOcupacoes() {
      return dadosOcupacoes;
   }

   /**
    * Atribui o novo valor de dadosOcupacoes
    * @param dadosOcupacoes dadosOcupacoes que será atribuído
    */
   public void setDadosOcupacoes(List<DadosOcupacao> dadosOcupacoes) {
      this.dadosOcupacoes = dadosOcupacoes;
   }
}
