package br.ufrn.ct.cronos.consultarsalasparamarcacoes.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseListPaged;

public class RespostaConsultarSalasParaMarcacoes extends ResponseListPaged<DadosConsultarSalasParaMarcacoes> {

   private static final long serialVersionUID = 1L;

   public RespostaConsultarSalasParaMarcacoes() {
      super();
   }

   public RespostaConsultarSalasParaMarcacoes(List<DadosConsultarSalasParaMarcacoes> dados, Integer totalNumeroLinhas) {
      setDados(dados);
      setTotalNumeroLinhas(totalNumeroLinhas);
   }

}
