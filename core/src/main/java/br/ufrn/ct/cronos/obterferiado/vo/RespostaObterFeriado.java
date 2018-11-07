package br.ufrn.ct.cronos.obterferiado.vo;

import dev.home.componente.service.entity.ResponseUnique;

public class RespostaObterFeriado extends ResponseUnique<DadosObterFeriado> {

   private static final long serialVersionUID = 1L;

   public RespostaObterFeriado() {
      super();
   }

   public RespostaObterFeriado(DadosObterFeriado objeto) {
      setObjeto(objeto);
   }
}
