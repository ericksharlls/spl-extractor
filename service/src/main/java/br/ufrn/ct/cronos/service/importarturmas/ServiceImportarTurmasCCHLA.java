package br.ufrn.ct.cronos.service.importarturmas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import br.ufrn.ct.adaptadorturmas.AdaptadorAPIUFRN;
import br.ufrn.ct.adaptadorturmas.DocenteDTO;
import br.ufrn.ct.adaptadorturmas.RetornaTurmasSIGAA;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.service.ManipuladorNomes;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceImportarTurmasCCHLA implements IServiceImportarTurmas {

   private PeriodoDao periodoDao;
   private TurmaDao turmaDao;
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

      /**
       * Restringindo a quantidade de docentes por Turma. Uma turma ficará com, no máximo, 2 docentes. E em caso de 2 docentes, estes terão
       * seus nomes abreviados.
       */
      for (br.ufrn.ct.adaptadorturmas.TurmaDTO turmaDTO : turmasSIGAA) {
         if (turmaDTO.getDocentesList().size() > 1) {
            if (turmaDTO.getDocentesList().size() == 2) {
               for (int i = 0; i < 2; i++) {
                  turmaDTO.getDocentesList().get(i).setNome(ManipuladorNomes.abreviaNome(turmaDTO.getDocentesList().get(i).getNome()));
               }
            } else {
               List<br.ufrn.ct.adaptadorturmas.DocenteDTO> listaDocentesAbreviada = new ArrayList<br.ufrn.ct.adaptadorturmas.DocenteDTO>(0);
               for (int i = 0; i < 2; i++) {
                  turmaDTO.getDocentesList().get(i).setNome(ManipuladorNomes.abreviaNome(turmaDTO.getDocentesList().get(i).getNome()));
                  listaDocentesAbreviada.add(turmaDTO.getDocentesList().get(i));
               }
               turmaDTO.setDocentesList(listaDocentesAbreviada);
            }
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
         // Se a turma nao existir no mapaTurmasLocais, eh pq a turma eh nova logo, sera salva. (ENTRARA no PROXIMO IF)
         // Caso contrario, sera atualizada (ENTRARA NO ELSE)
         if (!mapaTurmasLocais.containsKey(new Long(turmaDTO.getId()))) {
            Turma entidadeTurma = new Turma();
            entidadeTurma.setAlunosMatriculados(turmaDTO.getQtdMatriculados());
            entidadeTurma.setCapacidade(turmaDTO.getCapacidadeAluno());
            entidadeTurma.setCodigoDisciplina(turmaDTO.getCodigoComponente());
            entidadeTurma.setDistribuir(true);
            String docente = "";
            if (turmaDTO.getDocentesList().isEmpty()) {
               docente = "A DEFINIR DOCENTE";
            } else {
               for (DocenteDTO d : turmaDTO.getDocentesList()) {
                  docente += d.getNome() + " ";
               }
            }
            entidadeTurma.setDocente(docente);
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
            String docente = "";
            if (turmaDTO.getDocentesList().isEmpty()) {
               docente = "A DEFINIR DOCENTE";
            } else {
               DocenteDTO[] arrayDocentes =
                  (DocenteDTO[]) turmaDTO.getDocentesList().toArray(new DocenteDTO[turmaDTO.getDocentesList().size()]);
               for (int i = 0; i < arrayDocentes.length; i++) {
                  if (i > 0) {
                     docente += ", " + arrayDocentes[i].getNome();
                  } else {
                     docente += arrayDocentes[i].getNome();
                  }
               }
            }
            entidadeTurma.setDocente(docente);
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
      System.out.println("!! ImportarTURMASCCHLA EXECUTADO COM SUCESSO !!");

   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

}
