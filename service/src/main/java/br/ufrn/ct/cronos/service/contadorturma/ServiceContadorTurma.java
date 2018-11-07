package br.ufrn.ct.cronos.service.contadorturma;

import br.ufrn.ct.cronos.contadorturma.vo.ContadorTurma;
import br.ufrn.ct.cronos.contadorturma.vo.DadosContadorTurma;
import br.ufrn.ct.cronos.contadorturma.vo.RespostaContadorTurma;
import br.ufrn.ct.cronos.dao.TurmaDao;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceContadorTurma extends AbstractService<ContadorTurma, RespostaContadorTurma> {

   private TurmaDao turmaDao;

   public RespostaContadorTurma processa(ContadorTurma solicitacao) throws NegocioException {
      Integer total = this.turmaDao.count();
      DadosContadorTurma dadosContadorTurma = new DadosContadorTurma(total);
      return new RespostaContadorTurma(dadosContadorTurma);
   }

   public void valida(ContadorTurma solicitacao) throws NegocioException {
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

}
