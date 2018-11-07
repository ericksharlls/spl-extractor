
package br.ufrn.ct.cronos.relatorioocupacaoturmas.vo;

import java.util.Date;
import dev.home.componente.service.entity.Request;

public class RelatorioOcupacaoTurmas extends Request {

   private static final long serialVersionUID = 1L;
   private Date dataInicial;
   private Date dataFinal;
   private Long idTurma, idPeriodo, idPredio;

   public RelatorioOcupacaoTurmas() {
      super();
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
    * Recupera o valor do atributo idTurma
    * @return o idTurma
    */
   public Long getIdTurma() {
      return idTurma;
   }

   /**
    * Atribui o novo valor de idTurma
    * @param idTurma idTurma que será atribuído
    */
   public void setIdTurma(Long idTurma) {
      this.idTurma = idTurma;
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
