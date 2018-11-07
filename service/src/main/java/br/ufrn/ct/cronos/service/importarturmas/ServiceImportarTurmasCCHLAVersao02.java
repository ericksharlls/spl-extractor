package br.ufrn.ct.cronos.service.importarturmas;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import br.ufrn.ct.adaptadorturmas.AdaptadorAPIUFRN;
import br.ufrn.ct.adaptadorturmas.DocenteDTO;
import br.ufrn.ct.adaptadorturmas.RetornaTurmasSIGAA;
import br.ufrn.ct.adaptadorturmas.TurmaDTO;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.dao.TurmaDocenteDao;
import br.ufrn.ct.cronos.entity.Funcionario;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.entity.TurmaDocente;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceImportarTurmasCCHLAVersao02 implements IServiceImportarTurmas {

   private PeriodoDao periodoDao;
   private TurmaDao turmaDao;
   private TurmaDocenteDao turmaDocenteDao;
   private FuncionarioDao funcionarioDao;

   private RetornaTurmasSIGAA retornaTurmasSIGAA;

   @Override
   public void valida() throws NegocioException {

   }

   @Override
   public void processa(Integer idCentro, String clientId, String clientSecret) throws NegocioException {
      Periodo periodo = this.periodoDao.getPeriodoLetivoAtualOuProximo();
      this.retornaTurmasSIGAA = new AdaptadorAPIUFRN(idCentro, periodo.getAno(), periodo.getNumero(), clientId, clientSecret);
      List<br.ufrn.ct.adaptadorturmas.TurmaDTO> turmasSIGAA = retornaTurmasSIGAA.retornaTurmas();

      Iterator<br.ufrn.ct.adaptadorturmas.TurmaDTO> iterator = turmasSIGAA.iterator();

      /**
       * Eliminando as turmas AGRUPADORAS. Turmas AGRUPADORAS funcionam como turmas "mãe" de outras turmas. Apenas turmas "filhas" devem ser
       * consideradas, pois os alunos são matriculados nas "filhas", e fazem o uso da turma virtual apenas das "filhas" tambem.
       */
      iterator = turmasSIGAA.iterator();
      while (iterator.hasNext()) {
         if (iterator.next().getAgrupadora().equals(true)) {
            iterator.remove();
         }
      }

      // Obtendo a lista de turmas locais
      List<Turma> listaTurmasLocais = this.turmaDao.getTurmasPorPeriodo(periodo.getId());

      Map<Long, Long> mapaTurmasLocais = new HashMap<Long, Long>();
      // Montando mapa com chave sendo o ID da Turma Local no SIGAA, e como valor o proprio ID da turma
      for (Turma turmaLocal : listaTurmasLocais) {
         mapaTurmasLocais.put(turmaLocal.getIdTurmaSIGAA(), turmaLocal.getId());
      }
      for (br.ufrn.ct.adaptadorturmas.TurmaDTO turmaDTO : turmasSIGAA) {
         // Se a turma nao existir no mapaTurmasLocais, eh pq a turma eh nova, logo, sera salva. (ENTRARA no PROXIMO IF)
         // Caso contrario, sera atualizada (ENTRARA NO ELSE)
         if (!mapaTurmasLocais.containsKey(new Long(turmaDTO.getId()))) {
            Turma entidadeTurma = new Turma();
            entidadeTurma.setAlunosMatriculados(turmaDTO.getQtdMatriculados());
            entidadeTurma.setCapacidade(turmaDTO.getCapacidadeAluno());
            entidadeTurma.setCodigoDisciplina(turmaDTO.getCodigoComponente());
            entidadeTurma.setDistribuir(true);
            // ######## PARTE DOS DOCENTES ##########
            salvarDocentes(turmaDTO.getDocentesList());
            // ######## PARTE DOS DOCENTES ##########
            String horarioTemp = "";
            if (turmaDTO.getDescricaoHorario().contains("(")) {
               horarioTemp = turmaDTO.getDescricaoHorario().substring(0, turmaDTO.getDescricaoHorario().indexOf("("));
               horarioTemp = horarioTemp.trim();
            } else {
               horarioTemp = turmaDTO.getDescricaoHorario();
            }
            entidadeTurma.setHorario(horarioTemp);
            entidadeTurma.setIdDepartamento(Long.valueOf(turmaDTO.getIdUnidade().longValue()));
            entidadeTurma.setIdPeriodo(periodo.getId());
            entidadeTurma.setIdPredio(new Long(1));
            entidadeTurma.setIdTipo(new Long(1));
            entidadeTurma.setNomeDisciplina(turmaDTO.getNomeComponente());
            entidadeTurma.setNumero(turmaDTO.getCodigo());
            entidadeTurma.setIdTurmaSIGAA(new Long(turmaDTO.getId()));
            entidadeTurma.setLocal("INDEFINIDO");
            this.turmaDao.save(entidadeTurma);
         } else {
            Turma entidadeTurma = this.turmaDao.get(mapaTurmasLocais.get(new Long(turmaDTO.getId())));
            entidadeTurma.setAlunosMatriculados(turmaDTO.getQtdMatriculados());
            entidadeTurma.setCapacidade(turmaDTO.getCapacidadeAluno());
            entidadeTurma.setCodigoDisciplina(turmaDTO.getCodigoComponente());
            // ######## PARTE DOS DOCENTES ##########
            salvarDocentes(turmaDTO.getDocentesList());
            // ######## PARTE DOS DOCENTES ##########
            String horarioTemp = "";
            if (turmaDTO.getDescricaoHorario().contains("(")) {
               horarioTemp = turmaDTO.getDescricaoHorario().substring(0, turmaDTO.getDescricaoHorario().indexOf("("));
               horarioTemp = horarioTemp.trim();
            } else {
               horarioTemp = turmaDTO.getDescricaoHorario();
            }
            entidadeTurma.setHorario(horarioTemp);
            entidadeTurma.setNomeDisciplina(turmaDTO.getNomeComponente());
            entidadeTurma.setNumero(turmaDTO.getCodigo());
            this.turmaDao.merge(entidadeTurma);
         }
      }

      salvandoRelacionamentoTurmasComDocentes(turmasSIGAA);

      deletarTurmasQueNaoExistemMais(turmasSIGAA, listaTurmasLocais);

      System.out.println("!! ImportarTURMASCCHLA - VERSAO 02 - EXECUTADO COM SUCESSO !!");

   }

   /**
    * Salvando/Atualizando os dados do(s) docente(s) de uma turma especifica
    */
   private void salvarDocentes(List<DocenteDTO> docentesList) {
      for (DocenteDTO docenteDTO : docentesList) {
            Funcionario funcionario = this.funcionarioDao.getByIdSigaaFuncionario(docenteDTO.getIdServidor());
            if (funcionario == null) {
               Funcionario docente = new Funcionario();
               docente.setNome(docenteDTO.getNome());
               docente.setIdSigaaFuncionario(docenteDTO.getIdServidor());
               docente.setIdTipoFuncionario(new Long(1));
               this.funcionarioDao.save(docente);
            } else {
               funcionario.setNome(docenteDTO.getNome());
               funcionario.setIdSigaaFuncionario(docenteDTO.getIdServidor());
               funcionario.setIdTipoFuncionario(new Long(1));
               this.funcionarioDao.merge(funcionario);
            }
      }
   }

   /**
    * Removendo os relacionamentos antigos e salvando os novos entre turmas e seus respectivos docentes
    */
   private void salvandoRelacionamentoTurmasComDocentes(List<TurmaDTO> turmasSIGAA) {
      for (TurmaDTO turmaDTO : turmasSIGAA) {
         if (turmaDTO.getDocentesList().size() > 0) {
            Turma turma = this.turmaDao.getByIdTurmaSIGAA(new Long(turmaDTO.getId()));
            this.turmaDocenteDao.removerRelacionamentoDocenteComTurma(turma.getId());
            for (DocenteDTO docenteDTO : turmaDTO.getDocentesList()) {
                  TurmaDocente turmaDocente = new TurmaDocente();
                  Funcionario funcionario = this.funcionarioDao.getByIdSigaaFuncionario(docenteDTO.getIdServidor());
                  turmaDocente.setIdDocente(funcionario.getId());
                  turmaDocente.setIdTurma(turma.getId());
                  this.turmaDocenteDao.save(turmaDocente);
            }
         }
      }
   }

   /**
    * Removendo as turmas que existiam no SIGAA em importacao anterior, mas que na importacao atual nao existem mais
    */
   private void deletarTurmasQueNaoExistemMais(List<TurmaDTO> turmasSIGAA, List<Turma> listaTurmasLocais) {
      Map<Integer, Integer> mapaTurmasSIGAA = new HashMap<Integer, Integer>();
      for (br.ufrn.ct.adaptadorturmas.TurmaDTO turmaDTO : turmasSIGAA) {
         mapaTurmasSIGAA.put(turmaDTO.getId(), turmaDTO.getId());
      }
      for (Turma turmaLocal : listaTurmasLocais) {
         if (!turmaLocal.getIdTurmaSIGAA().equals(new Long(0)) && !mapaTurmasSIGAA.containsKey(new
                  Integer(turmaLocal.getIdTurmaSIGAA().intValue()))) {
            Turma entidadeTurma = this.turmaDao.get(turmaLocal.getId());
            System.out.println("### Turma excluida: " + entidadeTurma.getId());
         }
      }
      for (Turma turmaLocal : listaTurmasLocais) {
         if (!turmaLocal.getIdTurmaSIGAA().equals(new Long(0)) && !mapaTurmasSIGAA.containsKey(new
                  Integer(turmaLocal.getIdTurmaSIGAA().intValue()))) {
            Turma entidadeTurma = this.turmaDao.get(turmaLocal.getId());
            System.out.println("### Turma excluida (PASSO 2): " + entidadeTurma.getId());
            this.turmaDocenteDao.removerRelacionamentoDocenteComTurma(entidadeTurma.getId());
            this.turmaDao.delete(entidadeTurma.getId());
         }
      }
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setTurmaDocenteDao(TurmaDocenteDao turmaDocenteDao) {
      this.turmaDocenteDao = turmaDocenteDao;
   }

   public void setFuncionarioDao(FuncionarioDao funcionarioDao) {
      this.funcionarioDao = funcionarioDao;
   }

}
