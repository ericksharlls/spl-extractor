package br.ufrn.ct.cronos.relatoriohistoricochave.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaRelatorioHistoricoChave extends ResponseList<DadosRelatorioUtilizacaoChaves> {

   private static final long serialVersionUID = 1L;

   public RespostaRelatorioHistoricoChave(List<DadosRelatorioUtilizacaoChaves> dados) {
      setDados(dados);
   }

}
