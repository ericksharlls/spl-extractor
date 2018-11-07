
package br.ufrn.ct.cronos.listarfuncionariodocente.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaListarFuncionarioDocente extends ResponseList<DadosListarFuncionarioDocente> {

   private static final long serialVersionUID = 1L;

   public RespostaListarFuncionarioDocente() {
      super();
   }

   public RespostaListarFuncionarioDocente(List<DadosListarFuncionarioDocente> dadosListarFuncionarioDocente) {
      setDados(dadosListarFuncionarioDocente);
   }

}
