package br.ufrn.ct.cronos.service.relatoriodepartamentos;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.DepartamentoDao;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.ParametrosRelatoriosDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.dao.TurmaDocenteDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.EntidadeRelatorioTurmaDepartamento;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.entity.TurmaDocente;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.relatoriodepartamentos.vo.DadosDepartamento;
import br.ufrn.ct.cronos.relatoriodepartamentos.vo.DadosTurma;
import br.ufrn.ct.cronos.relatoriodepartamentos.vo.RelatorioDepartamentos;
import br.ufrn.ct.cronos.relatoriodepartamentos.vo.RespostaRelatorioDepartamentos;
import br.ufrn.ct.cronos.service.ManipuladorNomes;
import br.ufrn.ct.cronos.service.ParametrosRelatoriosEnum;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRelatorioDepartamentosOtimizado extends AbstractService<RelatorioDepartamentos, RespostaRelatorioDepartamentos> {

   private TurmaDao turmaDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private PeriodoDao periodoDao;
   private DepartamentoDao departamentoDao;
   private ParametrosRelatoriosDao parametrosRelatoriosDao;
   private HorarioDao horarioDao;
   private TurmaDocenteDao turmaDocenteDao;
   private FuncionarioDao funcionarioDao;

   public RespostaRelatorioDepartamentos processa(RelatorioDepartamentos solicitacao) throws NegocioException {
      List<DadosDepartamento> dadosDepartamentos = new ArrayList<DadosDepartamento>(0);
      List<DadosTurma> dadosTurmas = new ArrayList<DadosTurma>(0);
      Auxiliar auxiliar = new Auxiliar();

      DadosDepartamento departamento = new DadosDepartamento();

      departamento.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
      departamento.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
      departamento.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());
      departamento.setDepartamento(this.departamentoDao.get(solicitacao.getIdDepartamento()).getNome().toUpperCase());
      departamento.setSemestre(this.periodoDao.get(solicitacao.getIdPeriodo()).getNome());

      List<EntidadeRelatorioTurmaDepartamento> turmasLegais = this.turmaDao.getRelatorioAlocacaoTurmasPorDepartamento(solicitacao.getIdPredio(), solicitacao.getIdPeriodo(), solicitacao.getIdDepartamento());
	  String dia = "";
	  String turno = "";
	  String horario = "";
	  Long idTurmaAnteriorLaco = new Long(0);
      for (EntidadeRelatorioTurmaDepartamento entidade : turmasLegais) {
    	  Long idTurmaAtualLaco = entidade.getIdTurma();
    	  if (!idTurmaAnteriorLaco.equals(idTurmaAtualLaco)) {
    		  dia += entidade.getDia();
    		  turno += entidade.getTurno();
    		  horario += RetornaHorarioSala.retornaValor(entidade.getIdHorarioSala().intValue());
    	  }
    	  idTurmaAnteriorLaco = entidade.getIdTurma();
    	  
    	  
    	  System.out.println("### " + entidade.getDia() + " - "  + entidade.getTurno() + " - "  + entidade.getIdHorarioSala() + " - "  + entidade.getIdSala() + " - " + entidade.getIdTurma());
      }
      
      

      return new RespostaRelatorioDepartamentos(dadosDepartamentos);
   }

   public void valida(RelatorioDepartamentos solicitacao) throws NegocioException {
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

   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

   public void setTurmaDocenteDao(TurmaDocenteDao turmaDocenteDao) {
      this.turmaDocenteDao = turmaDocenteDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

}
