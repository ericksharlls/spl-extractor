package br.ufrn.ct.cronos.relatoriosalas.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaRelatorioSalas extends ResponseList<DadosSala> {

   private static final long serialVersionUID = 1L;

   public RespostaRelatorioSalas(List<DadosSala> dados) {
      setDados(dados);
   }

}
