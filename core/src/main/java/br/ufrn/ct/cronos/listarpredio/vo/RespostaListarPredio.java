
package br.ufrn.ct.cronos.listarpredio.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaListarPredio extends ResponseList<DadosListarPredio> {

   private static final long serialVersionUID = 1L;

   public RespostaListarPredio() {
      super();
   }

   public RespostaListarPredio(List<DadosListarPredio> dadosListarPredio) {
      setDados(dadosListarPredio);
   }

}
