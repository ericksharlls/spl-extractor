package br.ufrn.ct.cronos.obterfuncionario.vo;

import dev.home.componente.service.entity.ResponseUnique;

public class RespostaObterFuncionario extends ResponseUnique<DadosObterFuncionario> {

   private static final long serialVersionUID = 1L;

   public RespostaObterFuncionario() {
   }

   public RespostaObterFuncionario(DadosObterFuncionario objeto) {
      setObjeto(objeto);
   }

}
