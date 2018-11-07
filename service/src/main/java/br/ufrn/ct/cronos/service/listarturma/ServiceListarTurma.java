package br.ufrn.ct.cronos.service.listarturma;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.listarturma.vo.DadosListarTurma;
import br.ufrn.ct.cronos.listarturma.vo.ListarTurma;
import br.ufrn.ct.cronos.listarturma.vo.RespostaListarTurma;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceListarTurma extends AbstractService<ListarTurma, RespostaListarTurma> {

   private TurmaDao turmaDao;

   public RespostaListarTurma processa(ListarTurma solicitacao) throws NegocioException {
      List<Turma> turmas =
         this.turmaDao.getTurmasPorPeriodoPredioEDepartamentoOrderByNomeDisciplina(solicitacao.getIdPeriodo(), solicitacao.getIdPredio(),
            solicitacao.getIdDepartamento());
      List<DadosListarTurma> dadosListarTurma = new ArrayList<DadosListarTurma>();

      for (Turma t : turmas) {
         DadosListarTurma dados = new DadosListarTurma();
         dados.setId(t.getId());
         dados.setNome(t.getCodigoDisciplina() + " - Turma " + t.getNumero());
         dadosListarTurma.add(dados);
      }

      return new RespostaListarTurma(dadosListarTurma);
   }

   public void valida(ListarTurma arg0)
      throws NegocioException
   {
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }
}
