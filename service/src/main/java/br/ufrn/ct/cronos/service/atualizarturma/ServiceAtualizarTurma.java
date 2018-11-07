
package br.ufrn.ct.cronos.service.atualizarturma;

import org.apache.commons.lang.StringUtils;
import br.ufrn.ct.cronos.atualizarturma.vo.AtualizarTurma;
import br.ufrn.ct.cronos.atualizarturma.vo.DadosDocente;
import br.ufrn.ct.cronos.atualizarturma.vo.RespostaAtualizarTurma;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.dao.TurmaDocenteDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.entity.TurmaDocente;
import br.ufrn.ct.cronos.error.ErrorCode;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceAtualizarTurma extends AbstractService<AtualizarTurma, RespostaAtualizarTurma> {

   private TurmaDao turmaDao;
   private TurmaDocenteDao turmaDocenteDao;

   public RespostaAtualizarTurma processa(AtualizarTurma solicitacao) throws NegocioException {
      Turma turma = this.turmaDao.get(solicitacao.getId());

      turma.setIdTipo(solicitacao.getIdTipo());
      turma.setIdPredio(solicitacao.getIdPredio());
      turma.setLocal("INDEFINIDO");
      turma.setDistribuir(solicitacao.getDistribuir());
      turma.setCapacidade(solicitacao.getCapacidade());
      turma.setCodigoDisciplina(solicitacao.getCodigoDisciplina());
      turma.setHorario(solicitacao.getHorario());
      turma.setNomeDisciplina(solicitacao.getNomeDisciplina());
      turma.setNumero(solicitacao.getTurma());

      this.turmaDao.merge(turma);

      this.turmaDocenteDao.removerRelacionamentoDocenteComTurma(turma.getId());
      if (!solicitacao.getDocentes().isEmpty()) {
         for (DadosDocente dadosDocente : solicitacao.getDocentes()) {
            TurmaDocente turmaDocente = new TurmaDocente();
            turmaDocente.setIdDocente(dadosDocente.getId());
            turmaDocente.setIdTurma(turma.getId());
            this.turmaDocenteDao.save(turmaDocente);
         }
      }
      return new RespostaAtualizarTurma();
   }

   public void valida(AtualizarTurma solicitacao) throws NegocioException {
      if (StringUtils.isBlank(solicitacao.getCodigoDisciplina())) {
         throw new NegocioException(ErrorCode.CODIGO_DISCIPLINA_VAZIO);
      }
      if (StringUtils.isBlank(solicitacao.getNomeDisciplina())) {
         throw new NegocioException(ErrorCode.NOME_DISCIPLINA_VAZIO);
      }
      /*
       * Por enquanto docente nao sera um campo obrigatorio para a atualizacao de Turma if (solicitacao.getDocentes().isEmpty()) { throw new
       * NegocioException(ErrorCode.DOCENTE_VAZIO); }
       */
      if (StringUtils.isBlank(solicitacao.getHorario())) {
         throw new NegocioException(ErrorCode.HORARIO_VAZIO);
      }
      if (StringUtils.isBlank(solicitacao.getTurma())) {
         throw new NegocioException(ErrorCode.NUMERO_VAZIO);
      }
      if (solicitacao.getCapacidade() == 0 || solicitacao.getCapacidade() == null) {
         throw new NegocioException(ErrorCode.CAPACIDADE_VAZIO);
      }
      Auxiliar auxiliar = new Auxiliar();
      if (!auxiliar.validarHorarioComSabado(solicitacao.getHorario())) {
         throw new NegocioException(ErrorCode.TURMA_COM_HORARIO_INVALIDO);
      }
      Turma turma = this.turmaDao.get(solicitacao.getId());
      if (this.turmaDao.turmaJaFoiDistribuida(solicitacao.getId()) && !turma.getHorario().equals(solicitacao.getHorario())) {
         throw new NegocioException(ErrorCode.HORARIO_NAO_PODE_SER_ALTERADO);
      }

   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setTurmaDocenteDao(TurmaDocenteDao turmaDocenteDao) {
      this.turmaDocenteDao = turmaDocenteDao;
   }

}
