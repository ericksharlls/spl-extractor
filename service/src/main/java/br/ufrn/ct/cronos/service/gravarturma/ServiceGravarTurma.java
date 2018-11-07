
package br.ufrn.ct.cronos.service.gravarturma;

import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.gravarturma.vo.GravarTurma;
import br.ufrn.ct.cronos.gravarturma.vo.RespostaGravarTurma;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
 
public class ServiceGravarTurma extends AbstractService<GravarTurma, RespostaGravarTurma> {

   private TurmaDao turmaDao;

   public RespostaGravarTurma processa(GravarTurma solicitacao) throws NegocioException {
      Turma turma = this.turmaDao.get(solicitacao.getId());

      turma.setIdTipo(solicitacao.getIdTipo());
      turma.setDistribuir(solicitacao.getDistribuir());
      turma.setIdPredio(solicitacao.getIdPredio());

      this.turmaDao.merge(turma);

      return new RespostaGravarTurma();
   }

   public void valida(GravarTurma solicitacao) throws NegocioException {

   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

}
