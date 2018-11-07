package br.ufrn.ct.cronos.service.registraroperacaochave;

import org.apache.commons.lang.StringUtils;
import br.ufrn.ct.cronos.entity.Chave;
import br.ufrn.ct.cronos.entity.HistoricoChave;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.registraroperacaochave.vo.RegistrarOperacaoChave;
import dev.home.componente.service.excecao.NegocioException;


public class ServiceRegistrarOperacaoChaveSemResponsavel extends ServiceRegistrarOperacaoChave {

   @Override
   protected Long obterIdResponsavel(HistoricoChave historicoChave, RegistrarOperacaoChave solicitacao) {
      System.out.println("### ServiceRegistrarOperacaoChaveSemResponsavel EXECUTADO COM SUCESSO ###");
      return null;
   }

   @Override
   public void valida(RegistrarOperacaoChave solicitacao) throws NegocioException {
      if (StringUtils.isBlank(solicitacao.getCodigoChave())) {
         throw new NegocioException(ErrorCode.CODIGO_CHAVE_VAZIO);
      }
      if (solicitacao.getIdOperacao() == 0 || solicitacao.getIdOperacao() == null) {
         throw new NegocioException(ErrorCode.ID_OPERACAO_VAZIO);
      }

      Chave chave = this.chaveDao.getByCodigo(solicitacao.getCodigoChave());
      if (chave == null) {
         throw new NegocioException(ErrorCode.CHAVE_NAO_ENCONTRADA);
      }

      /*
       * if (solicitacao.isTemResponsavel()) { if (this.funcionarioDao.get(solicitacao.getIdentificadorResponsavel()) == null) { throw new
       * NegocioException(ErrorCode.FUNCIONARIO_NAO_ENCONTRADO); } }
       */

      if (!this.historicoChaveDao.existeOcorrenciaChave(chave.getId())) {
         if (solicitacao.getIdOperacao().equals(new Long(1))) {
            throw new NegocioException(ErrorCode.OPERACAO_INVALIDA);
         }
      }

      HistoricoChave historicoChave = this.historicoChaveDao.getUltimaOcorrenciaChave(chave.getId());
      if (historicoChave != null) {
         if (solicitacao.getIdOperacao().equals(historicoChave.getIdOperacao())) {
            if (solicitacao.getIdOperacao().equals(new Long(1))) {
               throw new NegocioException(ErrorCode.ENTRADA_JA_REGISTRADA);
            } else {
               throw new NegocioException(ErrorCode.SAIDA_JA_REGISTRADA);
            }
         }
      }

   }

}
