package br.ufrn.ct.cronos.service.listarturmasdistribuidas;
 
import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.listarturmasdistribuidas.vo.DadosListarTurmasDistribuidas;
import br.ufrn.ct.cronos.listarturmasdistribuidas.vo.ListarTurmasDistribuidas;
import br.ufrn.ct.cronos.listarturmasdistribuidas.vo.RespostaListarTurmasDistribuidas;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceListarTurmasDistribuidas extends AbstractService<ListarTurmasDistribuidas, RespostaListarTurmasDistribuidas> {
		
   private TurmaDao turmaDao;
 
   @Override
   public RespostaListarTurmasDistribuidas processa(ListarTurmasDistribuidas solicitacao) throws NegocioException {
      List<Turma> turmas = this.turmaDao.getTurmasDistribuidasPorPeriodoEPredio(solicitacao.getIdPeriodo(), solicitacao.getIdPredio());
      List<DadosListarTurmasDistribuidas> dadosListarTurmaDistribuidas = new ArrayList<DadosListarTurmasDistribuidas>();
 
      for (Turma t : turmas) {
         DadosListarTurmasDistribuidas dados = new DadosListarTurmasDistribuidas();
         dados.setId(t.getId());
         dados.setNome(t.getCodigoDisciplina() + " - Turma " + t.getNumero());
         dadosListarTurmaDistribuidas.add(dados);
		}
      return new RespostaListarTurmasDistribuidas(dadosListarTurmaDistribuidas);
   }

   @Override
   public void valida(ListarTurmasDistribuidas solicitacao) throws NegocioException {
			
   }
 
   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }
		
}
