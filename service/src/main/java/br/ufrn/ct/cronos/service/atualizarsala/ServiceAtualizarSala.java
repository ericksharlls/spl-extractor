package br.ufrn.ct.cronos.service.atualizarsala;

import br.ufrn.ct.cronos.atualizarsala.vo.AtualizarSala;
import br.ufrn.ct.cronos.atualizarsala.vo.RespostaAtualizarSala;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.entity.Sala;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
import org.apache.commons.lang.StringUtils;
import br.ufrn.ct.cronos.error.ErrorCode;

public class ServiceAtualizarSala extends AbstractService<AtualizarSala, RespostaAtualizarSala>{
      
      private SalaDao salaDao;
      
      @Override
      public RespostaAtualizarSala processa(AtualizarSala solicitacao) throws NegocioException {
         Sala sala = this.salaDao.get(solicitacao.getId());
         
         sala.setNome(solicitacao.getNome());
         sala.setCapacidade(solicitacao.getCapacidade());
         sala.setDescricao(solicitacao.getDescricao());
         sala.setUtilizarNaDistribuicao(solicitacao.getUtilizarNaDistribuicao());
         sala.setUtilizarNoAgendamento(solicitacao.getUtilizarNoAgendamento());
         
         sala.setTipoQuadro(solicitacao.getTipoQuadro());
         sala.setIdTipo(solicitacao.getIdTipoSala());
         sala.setIdPredio(solicitacao.getIdPredio());
         
         this.salaDao.merge(sala);
         return new RespostaAtualizarSala();
      }
      
      @Override
      public void valida(AtualizarSala solicitacao) throws NegocioException {
         if (StringUtils.isBlank(solicitacao.getNome())) {
            throw new NegocioException(ErrorCode.NOME_VAZIO);
         }
         if (StringUtils.isBlank(solicitacao.getDescricao())) {
            throw new NegocioException(ErrorCode.DESCRICAO_VAZIO);
         }
         if (solicitacao.getCapacidade() == 0) {
            throw new NegocioException(ErrorCode.CAPACIDADE_VAZIO);
         }
         if (StringUtils.isBlank(solicitacao.getTipoQuadro())) {
            throw new NegocioException(ErrorCode.TIPO_QUADRO_VAZIO);
         }
         if (solicitacao.getIdTipoSala() == 0 || solicitacao.getIdTipoSala() == null) {
            throw new NegocioException(ErrorCode.ID_TIPO_SALA);
         }
         if (solicitacao.getIdPredio() == 0 || solicitacao.getIdPredio() == null) {
            throw new NegocioException(ErrorCode.ID_PREDIO);
         }
      }
      
      public void setSalaDao(SalaDao salaDao){
         this.salaDao = salaDao;
      }

}
