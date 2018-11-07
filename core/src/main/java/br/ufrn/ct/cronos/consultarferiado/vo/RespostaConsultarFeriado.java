package br.ufrn.ct.cronos.consultarferiado.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseListPaged;


public class RespostaConsultarFeriado extends ResponseListPaged<DadosConsultarFeriado> {

   private static final long serialVersionUID = 1L;

   public RespostaConsultarFeriado() {
      super();
   }

   public RespostaConsultarFeriado(List<DadosConsultarFeriado> dados, Integer totalNumeroLinhas) {
      setDados(dados);
      setTotalNumeroLinhas(totalNumeroLinhas);
   }
}
