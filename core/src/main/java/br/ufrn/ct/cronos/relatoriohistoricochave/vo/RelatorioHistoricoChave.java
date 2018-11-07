package br.ufrn.ct.cronos.relatoriohistoricochave.vo;

import java.util.Date;
import dev.home.componente.service.entity.Request;

public class RelatorioHistoricoChave extends Request {

   private static final long serialVersionUID = 1L;

   private Date data;

   public RelatorioHistoricoChave() {
      super();
   }

   public Date getData() {
      return data;
   }

   public void setData(Date data) {
      this.data = data;
   }

}
