
package br.ufrn.ct.cronos.distribuirturmas.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaDistribuirTurmas extends ResponseList<DadosSala> {

   private static final long serialVersionUID = 1L;

   public RespostaDistribuirTurmas() {
      super();
   }

   public RespostaDistribuirTurmas(List<DadosSala> dados) {
      setDados(dados);
   }
}
