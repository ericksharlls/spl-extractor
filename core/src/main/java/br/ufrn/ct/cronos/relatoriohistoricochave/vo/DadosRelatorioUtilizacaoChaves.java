package br.ufrn.ct.cronos.relatoriohistoricochave.vo;

import java.io.Serializable;
import java.util.List;

public class DadosRelatorioUtilizacaoChaves implements Serializable {

   private static final long serialVersionUID = 1L;
   private String data = null;
   private String semestre = null;
   private String primeiraLinhaCabecalho = null;
   private String segundaLinhaCabecalho = null;
   private String terceiraLinhaCabecalho = null;
   private List<DadosHistoricoChave> historicosDasChaves;

   public DadosRelatorioUtilizacaoChaves() {
      super();
   }

   /**
    * Recupera o valor do atributo data
    * @return o data
    */
   public String getData() {
      return data;
   }

   /**
    * Atribui o novo valor de data
    * @param data data que será atribuído
    */
   public void setData(String data) {
      this.data = data;
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
    * Recupera o valor do atributo historicosDasChaves
    * @return o historicosDasChaves
    */
   public List<DadosHistoricoChave> getHistoricosDasChaves() {
      return historicosDasChaves;
   }

   /**
    * Atribui o novo valor de historicosDasChaves
    * @param historicosDasChaves historicosDasChaves que será atribuído
    */
   public void setHistoricosDasChaves(List<DadosHistoricoChave> historicosDasChaves) {
      this.historicosDasChaves = historicosDasChaves;
   }
}
