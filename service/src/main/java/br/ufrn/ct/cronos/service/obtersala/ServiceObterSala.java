package br.ufrn.ct.cronos.service.obtersala;
 
import br.ufrn.ct.cronos.dao.PerfilSalaTurmaDao;
import br.ufrn.ct.cronos.dao.PredioDao;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.entity.PerfilSalaTurma;
import br.ufrn.ct.cronos.entity.Predio;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.obtersala.vo.DadosObterSala;
import br.ufrn.ct.cronos.obtersala.vo.ObterSala;
import br.ufrn.ct.cronos.obtersala.vo.RespostaObterSala;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceObterSala extends AbstractService<ObterSala, RespostaObterSala> {
		
   private SalaDao salaDao;
   private PredioDao predioDao;
   private PerfilSalaTurmaDao perfilSalaTurmaDao;
		
   @Override
   public RespostaObterSala processa(ObterSala solicitacao) throws NegocioException {
      Sala sala = this.salaDao.get(solicitacao.getId());
      if (sala == null) {
         throw new NegocioException(ErrorCode.SALA_NAO_ENCONTRADO);
      }
      DadosObterSala dados = new DadosObterSala();
      dados.setId(sala.getId());
      dados.setNome(sala.getNome());
      dados.setCapacidade(sala.getCapacidade());
      dados.setDescricao(sala.getDescricao());
      dados.setTipoQuadro(sala.getTipoQuadro());
      dados.setUtilizarNaDistribuicao(sala.getUtilizarNaDistribuicao());
      dados.setUtilizarNoAgendamento(sala.getUtilizarNoAgendamento());
			
      Predio predio = this.predioDao.get(sala.getIdPredio());
      dados.setPredio(predio.getNome());
      dados.setIdPredio(predio.getId());
      PerfilSalaTurma tipo = this.perfilSalaTurmaDao.get(sala.getIdTipo());
      dados.setPerfil(tipo.getNome());
      dados.setIdTipoSala(tipo.getId());
			
      return new RespostaObterSala(dados);
   }
		
   @Override
   public void valida(ObterSala solicitacao) throws NegocioException {
      if (solicitacao.getId() == null) {
         throw new NegocioException(ErrorCode.ID_VAZIO);
		}
   }
		
   public void setSalaDao(SalaDao salaDao) {
      this.salaDao = salaDao;
   }
		
   public void setPredioDao(PredioDao predioDao) {
      this.predioDao = predioDao;
   }
		
   public void setPerfilSalaTurmaDao(PerfilSalaTurmaDao perfilSalaTurmaDao) {
      this.perfilSalaTurmaDao = perfilSalaTurmaDao;
   }

}
