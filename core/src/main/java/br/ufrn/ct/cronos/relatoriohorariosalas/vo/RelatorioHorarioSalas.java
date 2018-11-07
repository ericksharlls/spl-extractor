
package br.ufrn.ct.cronos.relatoriohorariosalas.vo;

import java.util.Date;
import dev.home.componente.service.entity.Request;

public class RelatorioHorarioSalas extends Request {

   private static final long serialVersionUID = 1L;
   private Date dataInicial;
   private Date dataFinal;
   private Long idPredio;

   public RelatorioHorarioSalas() {
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
