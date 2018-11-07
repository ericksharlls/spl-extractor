
package br.ufrn.ct.cronos.listarturmasdistribuidas.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaListarTurmasDistribuidas extends ResponseList<DadosListarTurmasDistribuidas> {

   private static final long serialVersionUID = 1L;

   public RespostaListarTurmasDistribuidas() {
      super();
   }

   public RespostaListarTurmasDistribuidas(List<DadosListarTurmasDistribuidas> dadosListarTurmaDistribuidas) {
      setDados(dadosListarTurmaDistribuidas);
   }

}