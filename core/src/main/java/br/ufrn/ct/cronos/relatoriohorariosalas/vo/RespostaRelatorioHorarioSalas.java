
package br.ufrn.ct.cronos.relatoriohorariosalas.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaRelatorioHorarioSalas extends ResponseList<DadosHorarioSalas> {

   private static final long serialVersionUID = 1L;

   public RespostaRelatorioHorarioSalas(List<DadosHorarioSalas> dados) {
      setDados(dados);
   }

}
