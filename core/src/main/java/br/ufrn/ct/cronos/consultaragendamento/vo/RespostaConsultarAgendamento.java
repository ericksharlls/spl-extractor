package br.ufrn.ct.cronos.consultaragendamento.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseListPaged;


public class RespostaConsultarAgendamento extends ResponseListPaged<DadosConsultarAgendamento> {

   private static final long serialVersionUID = 1L;

   public RespostaConsultarAgendamento() {
      super();
   }

   public RespostaConsultarAgendamento(List<DadosConsultarAgendamento> dados, Integer totalNumeroLinhas) {
      setDados(dados);
      setTotalNumeroLinhas(totalNumeroLinhas);
   }

}
