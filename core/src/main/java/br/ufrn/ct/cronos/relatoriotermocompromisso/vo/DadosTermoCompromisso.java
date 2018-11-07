
package br.ufrn.ct.cronos.relatoriotermocompromisso.vo;

import java.io.Serializable;

public class DadosTermoCompromisso implements Serializable {

   private static final long serialVersionUID = 1L;
   private String interessado = null;
   private String sala = null;
   private String horario = null;
   private String data = null;
   private String dataItemF = null;
   private String motivo = null;
   private String semestre = null;
   private String primeiraLinhaCabecalho = null;
   private String segundaLinhaCabecalho = null;
   private String terceiraLinhaCabecalho = null;
   private String identificador = null;

   public DadosTermoCompromisso() {
      super();
   }

   /**
    * Recupera o valor do atributo interessado
    * @return o interessado
    */
   public String getInteressado() {
      return interessado;
   }

   /**
    * Atribui o novo valor de interessado
    * @param interessado interessado que será atribuído
    */
   public void setInteressado(String interessado) {
      this.interessado = interessado;
   }

   /**
    * Recupera o valor do atributo sala
    * @return o sala
    */
   public String getSala() {
      return sala;
   }

   /**
    * Atribui o novo valor de sala
    * @param sala sala que será atribuído
    */
   public void setSala(String sala) {
      this.sala = sala;
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
    * Recupera o valor do atributo dataItemF
    * @return o dataItemF
    */
   public String getDataItemF() {
      return dataItemF;
   }

   /**
    * Atribui o novo valor de dataItemF
    * @param dataItemF dataItemF que será atribuído
    */
   public void setDataItemF(String dataItemF) {
      this.dataItemF = dataItemF;
   }

   /**
    * Recupera o valor do atributo motivo
    * @return o motivo
    */
   public String getMotivo() {
      return motivo;
   }

   /**
    * Atribui o novo valor de motivo
    * @param motivo motivo que será atribuído
    */
   public void setMotivo(String motivo) {
      this.motivo = motivo;
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
    * Recupera o valor do atributo identificador
    * @return o identificador
    */
   public String getIdentificador() {
      return identificador;
   }

   /**
    * Atribui o novo valor de identificador
    * @param identificador identificador que será atribuído
    */
   public void setIdentificador(String identificador) {
      this.identificador = identificador;
   }

}
