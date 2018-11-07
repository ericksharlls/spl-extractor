package br.ufrn.ct.cronos.service.distribuirturmas;

import dev.home.componente.service.excecao.NegocioException;

public interface IServiceDistribuirTurmas {

   public void valida(Long idPeriodo, Long idPredio) throws NegocioException;

   public void processa(Long idPeriodo, Long idPredio) throws NegocioException;

}
