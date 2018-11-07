package br.ufrn.ct.cronos.listarperiodo.vo;

import java.util.List;
import dev.home.componente.service.entity.ResponseList;


public class RespostaListarPeriodo extends ResponseList<DadosListarPeriodo> {

   /** @TODO Comentar atributo */
   private static final long serialVersionUID = 1L;

   public RespostaListarPeriodo() {
      super();
   }

   public RespostaListarPeriodo(List<DadosListarPeriodo> dadosListarPeriodo) {
      setDados(dadosListarPeriodo);
   }

}
