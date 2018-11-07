package br.ufrn.ct.cronos.service.relatorioturmasnaodistribuidas;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.DepartamentoDao;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.ParametrosRelatoriosDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.dao.TurmaDocenteDao;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.entity.TurmaDocente;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.relatorioturmasnaodistribuidas.vo.DadosDepartamento;
import br.ufrn.ct.cronos.relatorioturmasnaodistribuidas.vo.DadosTurma;
import br.ufrn.ct.cronos.relatorioturmasnaodistribuidas.vo.RelatorioTurmasNaoDistribuidas;
import br.ufrn.ct.cronos.relatorioturmasnaodistribuidas.vo.RespostaRelatorioTurmasNaoDistribuidas;
import br.ufrn.ct.cronos.service.ManipuladorNomes;
import br.ufrn.ct.cronos.service.ParametrosRelatoriosEnum;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRelatorioTurmasNaoDistribuidas extends
         AbstractService<RelatorioTurmasNaoDistribuidas, RespostaRelatorioTurmasNaoDistribuidas> {

   private TurmaDao turmaDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private PeriodoDao periodoDao;
   private DepartamentoDao departamentoDao;
   private ParametrosRelatoriosDao parametrosRelatoriosDao;
   private FuncionarioDao funcionarioDao;
   private TurmaDocenteDao turmaDocenteDao;

   public RespostaRelatorioTurmasNaoDistribuidas processa(RelatorioTurmasNaoDistribuidas solicitacao) throws NegocioException {
      List<DadosDepartamento> dadosDepartamentos = new ArrayList<DadosDepartamento>();
      List<DadosTurma> dadosTurmas = new ArrayList<DadosTurma>();

      DadosDepartamento departamento = new DadosDepartamento();

      departamento.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
      departamento.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
      departamento.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());
      departamento.setSemestre(this.periodoDao.get(solicitacao.getIdPeriodo()).getNome());
      departamento.setDepartamento(this.departamentoDao.get(solicitacao.getIdDepartamento()).getNome().toUpperCase());

      for (Turma t : this.turmaDao.getTurmasNaoDistribuidas(solicitacao.getIdPredio(), solicitacao.getIdPeriodo(),
         solicitacao.getIdDepartamento())) {
         DadosTurma turma = new DadosTurma();

         turma.setDisciplina(t.getCodigoDisciplina() + " - " + t.getNomeDisciplina());
         turma.setDocente(obterDocente(t.getId()));
         turma.setHorario(t.getHorario());
         turma.setNumero(t.getNumero());
         turma.setCapacidade(t.getCapacidade());
         turma.setAlunosMatriculados(t.getAlunosMatriculados());

         dadosTurmas.add(turma);
      }
      departamento.setTurmas(dadosTurmas);
      dadosDepartamentos.add(departamento);

      return new RespostaRelatorioTurmasNaoDistribuidas(dadosDepartamentos);
   }

   public void valida(RelatorioTurmasNaoDistribuidas solicitacao) throws NegocioException {
      if (solicitacao.getIdPeriodo() == 0 || solicitacao.getIdPeriodo() == null) {
         throw new NegocioException(ErrorCode.ID_PERIODO);
      }
      if (solicitacao.getIdPredio() == 0 || solicitacao.getIdPredio() == null) {
         throw new NegocioException(ErrorCode.ID_PREDIO);
      }
      if (solicitacao.getIdDepartamento() == 0 || solicitacao.getIdDepartamento() == null) {
         throw new NegocioException(ErrorCode.DEPARTAMENTO_VAZIO);
      }
      if (this.disponibilidadeSalaDao.findAll().size() < 1) {
         throw new NegocioException(ErrorCode.DISTRIBUICAO_NAO_REALIZADA);
      }
   }

   private String obterDocente(Long idTurma) {
      List<TurmaDocente> listaTurmaDocente = this.turmaDocenteDao.getTurmaDocentePorTurma(idTurma);
      String docente = "";
      if (listaTurmaDocente.isEmpty()) {
         docente = "A DEFINIR DOCENTE";
      } else if (listaTurmaDocente.size() == 1) {
         for (TurmaDocente td : listaTurmaDocente) {
            docente += this.funcionarioDao.get(td.getIdDocente()).getNome() + " ";
         }
      } else {
         int contador = listaTurmaDocente.size();
         for (TurmaDocente td : listaTurmaDocente) {
            contador--;
            if (contador > 0) {
               docente += ManipuladorNomes.abreviaNome(this.funcionarioDao.get(td.getIdDocente()).getNome()) + ", ";
            } else {
               docente += ManipuladorNomes.abreviaNome(this.funcionarioDao.get(td.getIdDocente()).getNome());
            }
         }
      }
      return docente;
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

   public void setDepartamentoDao(DepartamentoDao departamentoDao) {
      this.departamentoDao = departamentoDao;
   }

   public void setParametrosRelatoriosDao(ParametrosRelatoriosDao parametrosRelatoriosDao) {
      this.parametrosRelatoriosDao = parametrosRelatoriosDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

   public void setTurmaDocenteDao(TurmaDocenteDao turmaDocenteDao) {
      this.turmaDocenteDao = turmaDocenteDao;
   }

}
