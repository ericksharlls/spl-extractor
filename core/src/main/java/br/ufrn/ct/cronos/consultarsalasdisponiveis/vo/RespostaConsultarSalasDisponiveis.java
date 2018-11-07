
package br.ufrn.ct.cronos.consultarsalasdisponiveis.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseListPaged;

public class RespostaConsultarSalasDisponiveis extends ResponseListPaged<DadosConsultarSalasDisponiveis> {

   private static final long serialVersionUID = 1L;

   public RespostaConsultarSalasDisponiveis() {
      super();
   }

   public RespostaConsultarSalasDisponiveis(List<DadosConsultarSalasDisponiveis> dados, Integer totalNumeroLinhas) {
      setDados(dados);
      setTotalNumeroLinhas(totalNumeroLinhas);
   }

}
