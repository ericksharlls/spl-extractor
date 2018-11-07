package br.ufrn.ct.cronos.consultaragendamento.vo;

import java.io.Serializable;

public class DadosConsultarAgendamento implements Serializable {

   private static final long serialVersionUID = 1L;

   private Long idAgendamento;
   private String interessado, motivo, sala, data, telefone;

   public DadosConsultarAgendamento() {
      super();
   }

   /**
    * Recupera o valor do atributo idAgendamento
    * @return o idAgendamento
    */
   public Long getIdAgendamento() {
      return idAgendamento;
   }

   /**
    * Atribui o novo valor de idAgendamento
    * @param idAgendamento idAgendamento que será atribuído
    */
   public void setIdAgendamento(Long idAgendamento) {
      this.idAgendamento = idAgendamento;
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
    * Recupera o valor do atributo telefone
    * @return o telefone
    */
   public String getTelefone() {
      return telefone;
   }

   /**
    * Atribui o novo valor de telefone
    * @param telefone telefone que será atribuído
    */
   public void setTelefone(String telefone) {
      this.telefone = telefone;
   }

}
