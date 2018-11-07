package br.ufrn.ct.cronos.relatoriohistoricochaveporsala.vo;

import java.util.Date;
import dev.home.componente.service.entity.Request;

public class RelatorioHistoricoChavePorSala extends Request {

   private static final long serialVersionUID = 1L;

   private Date data;
   private Long idSala;

   public RelatorioHistoricoChavePorSala() {
      super();
   }

   /**
    * Recupera o valor do atributo data
    * @return o data
    */
   public Date getData() {
      return data;
   }

   /**
    * Atribui o novo valor de data
    * @param data data que será atribuído
    */
   public void setData(Date data) {
      this.data = data;
   }

   /**
    * Recupera o valor do atributo idSala
    * @return o idSala
    */
   public Long getIdSala() {
      return idSala;
   }

   /**
    * Atribui o novo valor de idSala
    * @param idSala idSala que será atribuído
    */
   public void setIdSala(Long idSala) {
      this.idSala = idSala;
   }

}
