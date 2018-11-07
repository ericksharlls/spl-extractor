package br.ufrn.ct.cronos.service.removerturma;
 
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.entity.DisponibilidadeSala;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.removerturma.vo.RemoverTurma;
import br.ufrn.ct.cronos.removerturma.vo.RespostaRemoverTurma;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
 
public class ServiceRemoverTurma extends AbstractService<RemoverTurma, RespostaRemoverTurma>  {
		
   private TurmaDao turmaDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
		
   @Override
   public RespostaRemoverTurma processa(RemoverTurma solicitacao) throws NegocioException {
      Turma turma = this.turmaDao.get(solicitacao.getId());
			
      if (this.disponibilidadeSalaDao.getDisponibilidadesSalaPorTurma(turma).size() > 0) {
         for (DisponibilidadeSala ds : this.disponibilidadeSalaDao.getDisponibilidadesSalaPorTurma(turma)) {
            this.disponibilidadeSalaDao.delete(ds);
			}
      }

      this.turmaDao.delete(turma);
      return new RespostaRemoverTurma();
   }
		
   @Override
   public void valida(RemoverTurma solicitacao) throws NegocioException {
      if (solicitacao.getId() == null) {
         throw new NegocioException(ErrorCode.ID_VAZIO);
		}
      if (this.turmaDao.turmaJaFoiDistribuida(solicitacao.getId())) {
         throw new NegocioException(ErrorCode.TURMA_JA_FOI_ALOCADA);
      }
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

}
