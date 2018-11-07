package br.ufrn.ct.cronos.listartipofuncionario.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaListarTipoFuncionario extends ResponseList<DadosListarTipoFuncionario> {

   private static final long serialVersionUID = 1L;

   public RespostaListarTipoFuncionario() {
      super();
   }

   public RespostaListarTipoFuncionario(List<DadosListarTipoFuncionario> dadosListarTipoFuncionario) {
      setDados(dadosListarTipoFuncionario);
   }

}
