package br.ufrn.ct.cronos.service.importarturmas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import br.ufrn.ct.adaptadorturmas.AdaptadorAPIUFRN;
import br.ufrn.ct.adaptadorturmas.RetornaTurmasSIGAA;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.service.ManipuladorNomes;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceImportarTurmasEAJ implements IServiceImportarTurmas {

   private PeriodoDao periodoDao;
   private RetornaTurmasSIGAA retornaTurmasSIGAA;

   @Override
   public void valida() throws NegocioException {

   }

   @Override
   public void processa(Integer idCentro, String clientId, String clientSecret) throws NegocioException {
      Periodo periodo = this.periodoDao.getPeriodoLetivoAtualOuProximo();
      this.retornaTurmasSIGAA = new AdaptadorAPIUFRN(idCentro, periodo.getAno(), periodo.getNumero(), clientId, clientSecret);
      List<br.ufrn.ct.adaptadorturmas.TurmaDTO> turmas = retornaTurmasSIGAA.retornaTurmas();
      
      Iterator<br.ufrn.ct.adaptadorturmas.TurmaDTO> iterator = turmas.iterator();

      /**
       * Eliminando as turmas AGRUPADORAS. Turmas AGRUPADORAS funcionam como turmas "mãe" de outras turmas. Apenas turmas "filhas" devem ser
       * consideradas, pois os alunos são matriculados nas "filhas", e fazem o uso da turma virtual apenas das "filhas" tambem.
       */
      iterator = turmas.iterator();
      while (iterator.hasNext()) {
         if (iterator.next().getAgrupadora().equals(true)) {
            iterator.remove();
         }
      }

      /**
       * Restringindo a quantidade de docentes por Turma. Uma turma ficará com, no máximo, 2 docentes. E em caso de 2 docentes, estes terão
       * seus nomes abreviados.
       */
      for (br.ufrn.ct.adaptadorturmas.TurmaDTO turmaDTO : turmas) {
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

   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

}
