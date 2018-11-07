package br.ufrn.ct.cronos.service.removersala;

import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.removersala.vo.RemoverSala;
import br.ufrn.ct.cronos.removersala.vo.RespostaRemoverSala;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRemoverSala extends AbstractService<RemoverSala, RespostaRemoverSala> {

   private SalaDao salaDao;

   public RespostaRemoverSala processa(RemoverSala solicitacao) throws NegocioException {
      Sala sala = new Sala();
      sala.setId(solicitacao.getId());
      this.salaDao.delete(sala);
      return new RespostaRemoverSala();
   }

   public void valida(RemoverSala solicitacao) throws NegocioException {
      if (solicitacao.getId() == null) {
         throw new NegocioException(ErrorCode.ID_VAZIO);
      }
      if (this.salaDao.verificarSalaTemTurma(solicitacao.getId())) {
         throw new NegocioException(ErrorCode.SALA_POSSUI_TURMA);
      }
   }

   public void setSalaDao(SalaDao salaDao) {
      this.salaDao = salaDao;
   }

}
