package br.ufrn.ct.cronos.consultaragendamento.vo;

import java.util.Date;
import dev.home.componente.service.entity.RequestPaged;

public class ConsultarAgendamento extends RequestPaged {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   private String interessado, motivo;
   private Date dataInicial;
   private Date dataFinal;
   private Long idPeriodo, idPredio;

   public ConsultarAgendamento() {
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
    * Recupera o valor do atributo dataInicial
    * @return o dataInicial
    */
   public Date getDataInicial() {
      return dataInicial;
   }

   /**
    * Atribui o novo valor de dataInicial
    * @param dataInicial dataInicial que será atribuído
    */
   public void setDataInicial(Date dataInicial) {
      this.dataInicial = dataInicial;
   }

   /**
    * Recupera o valor do atributo dataFinal
    * @return o dataFinal
    */
   public Date getDataFinal() {
      return dataFinal;
   }

   /**
    * Atribui o novo valor de dataFinal
    * @param dataFinal dataFinal que será atribuído
    */
   public void setDataFinal(Date dataFinal) {
      this.dataFinal = dataFinal;
   }

   /**
    * Recupera o valor do atributo idPeriodo
    * @return o idPeriodo
    */
   public Long getIdPeriodo() {
      return idPeriodo;
   }

   /**
    * Atribui o novo valor de idPeriodo
    * @param idPeriodo idPeriodo que será atribuído
    */
   public void setIdPeriodo(Long idPeriodo) {
      this.idPeriodo = idPeriodo;
   }

   /**
    * Recupera o valor do atributo idPredio
    * @return o idPredio
    */
   public Long getIdPredio() {
      return idPredio;
   }

   /**
    * Atribui o novo valor de idPredio
    * @param idPredio idPredio que será atribuído
    */
   public void setIdPredio(Long idPredio) {
      this.idPredio = idPredio;
   }

}
