
package br.ufrn.ct.cronos.listarfuncionario.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaListarFuncionario extends ResponseList<DadosListarFuncionario> {

   private static final long serialVersionUID = 1L;

   public RespostaListarFuncionario() {
      super();
   }

   public RespostaListarFuncionario(List<DadosListarFuncionario> dadosListarFuncionario) {
      setDados(dadosListarFuncionario);
   }

}
