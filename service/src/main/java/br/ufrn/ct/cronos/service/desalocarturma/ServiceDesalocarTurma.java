package br.ufrn.ct.cronos.service.desalocarturma;
 
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.desalocarturma.vo.DesalocarTurma;
import br.ufrn.ct.cronos.desalocarturma.vo.RespostaDesalocarTurma;
import br.ufrn.ct.cronos.entity.DisponibilidadeSala;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.error.ErrorCode;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceDesalocarTurma extends AbstractService<DesalocarTurma, RespostaDesalocarTurma> {
      
   private TurmaDao turmaDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
      
   @Override
   public RespostaDesalocarTurma processa(DesalocarTurma solicitacao) throws NegocioException {
      Turma turma = this.turmaDao.get(solicitacao.getIdTurma());

      for (DisponibilidadeSala d : this.disponibilidadeSalaDao.getDisponibilidadesSalaPorTurma(turma)) {
         this.disponibilidadeSalaDao.delete(d);
      }

      return new RespostaDesalocarTurma();
   }

   @Override
   public void valida(DesalocarTurma solicitacao) throws NegocioException {
      if ((solicitacao.getIdTurma() == null) || (solicitacao.getIdTurma().equals(new Long(0)))) {
         throw new NegocioException(ErrorCode.TURMA_VAZIA);
      }
      Turma turma = this.turmaDao.get(solicitacao.getIdTurma());
      if (!this.disponibilidadeSalaDao.verificarTurmaTemSala(turma.getId())) {
         throw new NegocioException(ErrorCode.TURMA_SEM_SALA);
      }
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }
      
}