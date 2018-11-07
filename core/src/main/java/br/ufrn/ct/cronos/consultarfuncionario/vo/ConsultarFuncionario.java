package br.ufrn.ct.cronos.consultarfuncionario.vo;

import dev.home.componente.service.entity.RequestPaged;

public class ConsultarFuncionario extends RequestPaged {

  private static final long serialVersionUID = 1L;

   private Long idTipoFuncionario;

   public ConsultarFuncionario() {
      super();
   }

   /**
    * Recupera o valor do atributo idTipoFuncionario
    * @return o idTipoFuncionario
    */
   public Long getIdTipoFuncionario() {
      return idTipoFuncionario;
   }

   /**
    * Atribui o novo valor de idTipoFuncionario
    * @param idTipoFuncionario idTipoFuncionario que será atribuído
    */
   public void setIdTipoFuncionario(Long idTipoFuncionario) {
      this.idTipoFuncionario = idTipoFuncionario;
   }

}