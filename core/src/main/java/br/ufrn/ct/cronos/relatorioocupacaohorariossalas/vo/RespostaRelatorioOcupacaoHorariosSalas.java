package br.ufrn.ct.cronos.relatorioocupacaohorariossalas.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;


public class RespostaRelatorioOcupacaoHorariosSalas extends ResponseList<DadosPredio> {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   public RespostaRelatorioOcupacaoHorariosSalas(List<DadosPredio> dados) {
      setDados(dados);
   }

}
