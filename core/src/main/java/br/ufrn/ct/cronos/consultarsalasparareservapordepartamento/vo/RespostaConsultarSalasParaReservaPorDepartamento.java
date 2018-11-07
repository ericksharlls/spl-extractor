package br.ufrn.ct.cronos.consultarsalasparareservapordepartamento.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseListPaged;


public class RespostaConsultarSalasParaReservaPorDepartamento extends ResponseListPaged<DadosConsultarSalasParaReservaPorDepartamento> {

   private static final long serialVersionUID = 1L;

   public RespostaConsultarSalasParaReservaPorDepartamento() {
      super();
   }

   public RespostaConsultarSalasParaReservaPorDepartamento(List<DadosConsultarSalasParaReservaPorDepartamento> dados,
      Integer totalNumeroLinhas) {
      setDados(dados);
      setTotalNumeroLinhas(totalNumeroLinhas);
   }

}
