package br.ufrn.ct.cronos.service.importarturmas;

import dev.home.componente.service.excecao.NegocioException;


public interface IServiceImportarTurmas {

   public void valida() throws NegocioException;

   public void processa(Integer idCentro, String clientId, String clientSecret) throws NegocioException;

}
