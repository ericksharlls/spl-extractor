package br.ufrn.ct.cronos.relatoriohistoricochaveporsala.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaRelatorioHistoricoChavePorSala extends ResponseList<DadosHistoricoChavePorSala> {

   private static final long serialVersionUID = 1L;

   public RespostaRelatorioHistoricoChavePorSala(List<DadosHistoricoChavePorSala> dados) {
      setDados(dados);
   }

}
