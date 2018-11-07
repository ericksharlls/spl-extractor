
package br.ufrn.ct.cronos.consultarperiodo.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseListPaged;

public class RespostaConsultarPeriodo extends ResponseListPaged<DadosConsultarPeriodo> {

   private static final long serialVersionUID = 1L;

   public RespostaConsultarPeriodo() {
      super();
   }

   public RespostaConsultarPeriodo(List<DadosConsultarPeriodo> dados, Integer totalNumeroLinhas) {
      setDados(dados);
      setTotalNumeroLinhas(totalNumeroLinhas);
   }
}
