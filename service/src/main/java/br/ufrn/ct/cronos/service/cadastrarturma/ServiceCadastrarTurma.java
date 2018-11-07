package br.ufrn.ct.cronos.service.cadastrarturma;

import org.apache.commons.lang.StringUtils;
import br.ufrn.ct.cronos.cadastrarturma.vo.CadastrarTurma;
import br.ufrn.ct.cronos.cadastrarturma.vo.RespostaCadastrarTurma;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.dao.TurmaDocenteDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.entity.TurmaDocente;
import br.ufrn.ct.cronos.error.ErrorCode;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceCadastrarTurma extends AbstractService<CadastrarTurma, RespostaCadastrarTurma> {
	
   private TurmaDao turmaDao;
   private TurmaDocenteDao turmaDocenteDao;
 
   @Override
   public RespostaCadastrarTurma processa(CadastrarTurma solicitacao) throws NegocioException {
      Turma turma = new Turma();
			
      turma.setCapacidade(solicitacao.getCapacidade());
      turma.setCodigoDisciplina(solicitacao.getCodigoDisciplina());
      turma.setDistribuir(solicitacao.getDistribuir());
      turma.setHorario(solicitacao.getHorario());
      turma.setIdTipo(solicitacao.getIdTipo());
      turma.setNomeDisciplina(solicitacao.getNomeDisciplina());
      turma.setNumero(solicitacao.getNumero());
      turma.setIdPeriodo(solicitacao.getIdPeriodo());
      turma.setIdPredio(solicitacao.getIdPredio());
      turma.setIdDepartamento(solicitacao.getIdDepartamento());

      turma.setIdTurmaSIGAA(new Long(0));
      turma.setAlunosMatriculados(new Integer(0));
      turma.setLocal("INDEFINIDO");

      this.turmaDao.save(turma);

      // ##############

      if (solicitacao.getIdDocente() != 0) {
         TurmaDocente turmaDocente = new TurmaDocente();
         turmaDocente.setIdDocente(solicitacao.getIdDocente());
         turmaDocente.setIdTurma(turma.getId());
         this.turmaDocenteDao.save(turmaDocente);
      }
      // ########
			
      return new RespostaCadastrarTurma();
   }
 
   @Override
   public void valida(CadastrarTurma solicitacao) throws NegocioException {
      if (StringUtils.isBlank(solicitacao.getCodigoDisciplina())) {
         throw new NegocioException(ErrorCode.CODIGO_DISCIPLINA_VAZIO);
      }
      if (StringUtils.isBlank(solicitacao.getNomeDisciplina())) {
         throw new NegocioException(ErrorCode.NOME_DISCIPLINA_VAZIO);
      }
      if (StringUtils.isBlank(solicitacao.getHorario())) {
         throw new NegocioException(ErrorCode.HORARIO_VAZIO);
      }
      if (StringUtils.isBlank(solicitacao.getNumero())) {
         throw new NegocioException(ErrorCode.NUMERO_VAZIO);
      }
      if (solicitacao.getCapacidade() == 0 || solicitacao.getCapacidade() == null) {
         throw new NegocioException(ErrorCode.CAPACIDADE_VAZIO);
      }
      /*
       * Por enquanto docente nao sera um campo obrigatorio para o cadastro de Turma if (solicitacao.getIdDocente() == 0 ||
       * solicitacao.getIdDocente() == null) { throw new NegocioException(ErrorCode.DOCENTE_VAZIO); }
       */
      if (solicitacao.getIdDepartamento() == 0 || solicitacao.getIdDepartamento() == null) {
         throw new NegocioException(ErrorCode.DEPARTAMENTO_VAZIO);
      }
      if (solicitacao.getIdTipo() == 0 || solicitacao.getIdTipo() == null) {
         throw new NegocioException(ErrorCode.ID_PERFIL_TURMA);
      }
      if (solicitacao.getIdPredio() == 0 || solicitacao.getIdPredio() == null) {
         throw new NegocioException(ErrorCode.ID_PREDIO);
      }
      if (solicitacao.getIdPeriodo() == 0 || solicitacao.getIdPeriodo() == null) {
         throw new NegocioException(ErrorCode.ID_PERIODO);
      }
      Auxiliar auxiliar = new Auxiliar();
      if (!auxiliar.validarHorarioComSabado(solicitacao.getHorario())) {
         throw new NegocioException(ErrorCode.HORARIO_VAZIO_OU_INVALIDO);
      }
   }
		
   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setTurmaDocenteDao(TurmaDocenteDao turmaDocenteDao) {
      this.turmaDocenteDao = turmaDocenteDao;
   }

}
