
package br.ufrn.ct.cronos.consultarturmaporcodigoedocente.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseListPaged;

public class RespostaConsultarTurmaPorCodigoEDocente extends ResponseListPaged<DadosConsultarTurmaPorCodigoEDocente> {

   private static final long serialVersionUID = 1L;

   public RespostaConsultarTurmaPorCodigoEDocente() {
      super();
   }

   public RespostaConsultarTurmaPorCodigoEDocente(List<DadosConsultarTurmaPorCodigoEDocente> dados, Integer totalNumeroLinhas) {
      setDados(dados);
      setTotalNumeroLinhas(totalNumeroLinhas);
   }

}
