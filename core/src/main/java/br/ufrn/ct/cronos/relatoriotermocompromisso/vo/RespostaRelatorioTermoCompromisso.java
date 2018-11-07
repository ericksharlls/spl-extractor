package br.ufrn.ct.cronos.relatoriotermocompromisso.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;

public class RespostaRelatorioTermoCompromisso extends ResponseList<DadosTermoCompromisso> {

   private static final long serialVersionUID = 1L;

   public RespostaRelatorioTermoCompromisso() {
      super();
   }

   public RespostaRelatorioTermoCompromisso(List<DadosTermoCompromisso> dados) {
      setDados(dados);
   }

}
