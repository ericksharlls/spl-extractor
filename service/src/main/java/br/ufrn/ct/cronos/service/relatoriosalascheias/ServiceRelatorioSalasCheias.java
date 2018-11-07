package br.ufrn.ct.cronos.service.relatoriosalascheias;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.ParametrosRelatoriosDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.relatoriosalascheias.vo.DadosRelatorioSalasCheias;
import br.ufrn.ct.cronos.relatoriosalascheias.vo.DadosTurma;
import br.ufrn.ct.cronos.relatoriosalascheias.vo.RelatorioSalasCheias;
import br.ufrn.ct.cronos.relatoriosalascheias.vo.RespostaRelatorioSalasCheias;
import br.ufrn.ct.cronos.service.ParametrosRelatoriosEnum;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRelatorioSalasCheias extends AbstractService<RelatorioSalasCheias, RespostaRelatorioSalasCheias> {

   private TurmaDao turmaDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private PeriodoDao periodoDao;
   private ParametrosRelatoriosDao parametrosRelatoriosDao;

   public RespostaRelatorioSalasCheias processa(RelatorioSalasCheias solicitacao) throws NegocioException {
      List<DadosRelatorioSalasCheias> salas = new ArrayList<DadosRelatorioSalasCheias>();
      List<DadosTurma> turmas = new ArrayList<DadosTurma>();
      DadosRelatorioSalasCheias dadosSala = new DadosRelatorioSalasCheias();

      dadosSala.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
      dadosSala.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
      dadosSala.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());
      dadosSala.setSemestre(this.periodoDao.get(solicitacao.getIdPeriodo()).getNome());

      for (Turma t : this.turmaDao.getTurmasComSalasCheais(solicitacao.getIdPredio(), solicitacao.getIdPeriodo())) {
         DadosTurma dadosTurma = new DadosTurma();
         Sala sala = this.disponibilidadeSalaDao.getSalaPorTurma(t.getId());

         dadosTurma.setCapacidadeSala(sala.getCapacidade());
         dadosTurma.setDisciplina(t.getCodigoDisciplina() + " - Turma " + t.getNumero());
         dadosTurma.setDocente(t.getDocente());
         dadosTurma.setHorario(t.getHorario());
         dadosTurma.setMatriculados(t.getAlunosMatriculados());
         dadosTurma.setSala(sala.getNome());

         turmas.add(dadosTurma);
      }

      dadosSala.setTurmas(turmas);
      salas.add(dadosSala);

      return new RespostaRelatorioSalasCheias(salas);
   }

   public void valida(RelatorioSalasCheias solicitacao) throws NegocioException {
      if (solicitacao.getIdPeriodo() == 0 || solicitacao.getIdPeriodo() == null) {
         throw new NegocioException(ErrorCode.ID_PERIODO);
      }
      if (solicitacao.getIdPredio() == 0 || solicitacao.getIdPredio() == null) {
         throw new NegocioException(ErrorCode.ID_PREDIO);
      }
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setParametrosRelatoriosDao(ParametrosRelatoriosDao parametrosRelatoriosDao) {
      this.parametrosRelatoriosDao = parametrosRelatoriosDao;
   }
}

