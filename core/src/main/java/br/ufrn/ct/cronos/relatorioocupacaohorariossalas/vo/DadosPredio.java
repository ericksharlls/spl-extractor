package br.ufrn.ct.cronos.relatorioocupacaohorariossalas.vo;

import java.io.Serializable;
import java.util.List;

public class DadosPredio implements Serializable {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   private String turno = null;
   private String primeiraLinhaCabecalho = null;
   private String segundaLinhaCabecalho = null;
   private String terceiraLinhaCabecalho = null;
   private String semestre = null;
   private String predio = null;
   private String mensagemRodape = null;

   private List<DadosOcupacaoHorario> dadosOcupacaoHorario = null;

   public DadosPredio() {
      super();
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
    * Recupera o valor do atributo predio
    * @return o predio
    */
   public String getPredio() {
      return predio;
   }

   /**
    * Atribui o novo valor de predio
    * @param predio predio que será atribuído
    */
   public void setPredio(String predio) {
      this.predio = predio;
   }

   /**
    * Recupera o valor do atributo mensagemRodape
    * @return o mensagemRodape
    */
   public String getMensagemRodape() {
      return mensagemRodape;
   }

   /**
    * Atribui o novo valor de mensagemRodape
    * @param mensagemRodape mensagemRodape que será atribuído
    */
   public void setMensagemRodape(String mensagemRodape) {
      this.mensagemRodape = mensagemRodape;
   }

   /**
    * Recupera o valor do atributo dadosOcupacaoHorario
    * @return o dadosOcupacaoHorario
    */
   public List<DadosOcupacaoHorario> getDadosOcupacaoHorario() {
      return dadosOcupacaoHorario;
   }

   /**
    * Atribui o novo valor de dadosOcupacaoHorario
    * @param dadosOcupacaoHorario dadosOcupacaoHorario que será atribuído
    */
   public void setDadosOcupacaoHorario(List<DadosOcupacaoHorario> dadosOcupacaoHorario) {
      this.dadosOcupacaoHorario = dadosOcupacaoHorario;
   }
}
