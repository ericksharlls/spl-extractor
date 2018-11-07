
package br.ufrn.ct.cronos.listarsalaporpredio.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaListarSalaPorPredio extends ResponseList<DadosListarSalaPorPredio> {

   private static final long serialVersionUID = 1L;

   public RespostaListarSalaPorPredio() {
      super();
   }

   public RespostaListarSalaPorPredio(List<DadosListarSalaPorPredio> dadosListarTurma) {
      setDados(dadosListarTurma);
   }

}
