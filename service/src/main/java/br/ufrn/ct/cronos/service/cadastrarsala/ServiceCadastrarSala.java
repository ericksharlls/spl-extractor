package br.ufrn.ct.cronos.service.cadastrarsala;
 
import org.apache.commons.lang.StringUtils;
import br.ufrn.ct.cronos.cadastrarsala.vo.CadastrarSala;
import br.ufrn.ct.cronos.cadastrarsala.vo.RespostaCadastrarSala;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.error.ErrorCode;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceCadastrarSala extends AbstractService<CadastrarSala, RespostaCadastrarSala> {
		
		private SalaDao salaDao;
		
		@Override
		public RespostaCadastrarSala processa(CadastrarSala solicitacao) throws NegocioException {
      Sala sala = new Sala();
			sala.setCapacidade(solicitacao.getCapacidade());
			sala.setDescricao(solicitacao.getDescricao());
			sala.setIdTipo(solicitacao.getIdTipoSala());
			sala.setNome(solicitacao.getNome());
			sala.setIdPredio(solicitacao.getIdPredio());
			sala.setTipoQuadro(solicitacao.getTipoQuadro());
      sala.setUtilizarNaDistribuicao(solicitacao.getUtilizarNaDistribuicao());
      sala.setUtilizarNoAgendamento(solicitacao.getUtilizarNoAgendamento());
			//sala.setDistribuir(true);
			
			this.salaDao.save(sala);
			return new RespostaCadastrarSala();
		}
 
		@Override
		public void valida(CadastrarSala solicitacao) throws NegocioException {
			if (StringUtils.isBlank(solicitacao.getNome())) {
				if (StringUtils.isBlank(solicitacao.getNome())) {
					throw new NegocioException(ErrorCode.NOME_VAZIO);
				}
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
