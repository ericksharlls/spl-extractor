package br.ufrn.ct.cronos.service.distribuirturmas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import br.ufrn.ct.adaptadorturmas.AdaptadorAPIUFRN;
import br.ufrn.ct.adaptadorturmas.RetornaTurmasSIGAA;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.service.ManipuladorNomes;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceImportarTurmas {

   private PeriodoDao periodoDao;

   public void valida() throws NegocioException {

   }

   public void processa(Integer idCentro) throws NegocioException {
      // Mapa dos Departamentos
      Map<String, Integer> departamentos = new HashMap<String, Integer>();

      departamentos.put("54", 1); // Departamento de Arquitetura
      departamentos.put("5205", 2); // Departamento de Engenharia Biomédica
      departamentos.put("52", 3); // Departamento de Engenharia Civil
      departamentos.put("56", 4); // Departamento de Engenharia de Computação e Automação
      departamentos.put("5204", 5); // Departamento de Engenharia de Comunicações
      departamentos.put("1589", 6); // Departamento de Engenharia de Materiais
      departamentos.put("5075", 7); // Departamento de Engenharia de Petróleo
      departamentos.put("112", 8); // Departamento de Engenharia Elétrica
      departamentos.put("50", 9); // Departamento de Engenharia Mecânica
      departamentos.put("115", 10); // Departamento de Engenharia Produção
      departamentos.put("57", 11); // Departamento de Engenharia Química
      departamentos.put("424", 12); // Departamento de Engenharia Têxtil
      departamentos.put("5632", 13); // Coordenação do Curso de Engenharia Mecatrônica
      // departamentos.put("", 14); // DIVERSOS

      RetornaTurmasSIGAA retornaTurmasSIGAA = new AdaptadorAPIUFRN(idCentro, 2016, 2, "turmas-previsao-app-id", "tuguhaDRej2REpR");
      List<br.ufrn.ct.adaptadorturmas.TurmaDTO> turmas = retornaTurmasSIGAA.retornaTurmas();

      Iterator<br.ufrn.ct.adaptadorturmas.TurmaDTO> iterator = turmas.iterator();

      // ----------------
      System.out.println("## QTD de TURMAS (01): " + turmas.size());
      Map<String, String> departamentosCCHLA = new HashMap<String, String>();


      for (br.ufrn.ct.adaptadorturmas.TurmaDTO turmaDTO : turmas) {
         if (!departamentosCCHLA.containsKey(turmaDTO.getIdUnidade())) {
            departamentosCCHLA.put(String.valueOf(turmaDTO.getIdUnidade()), turmaDTO.getUnidade());
         }
      }
      for (Map.Entry<String, String> pair : departamentosCCHLA.entrySet()) {
         System.out.println("INSERT INTO departamento (id_departamento, nome_departamento, descricao_departamento) VALUES ("
            + pair.getKey() + ", " + "'" + pair.getValue() + "'" + ", " + "'" + pair.getValue() + "'" + ");");
      }
      // ----------------

      /**
       * Restrigindo as turmas só para os departamentos incluidos na variavel departamentos do tipo Map<String, Integer> (Linha 30) Esse
       * procedimento é importante para eliminar, por exemplo, os departamentos q sao Pos-Graduacoes.
       */
      while (iterator.hasNext()) {
         if (!departamentos.containsKey(String.valueOf(iterator.next().getIdUnidade()))) {
            iterator.remove();
         }
      }

      /**
       * Eliminando as turmas que NAO sao de graduacao. Para o CT, apenas as turmas de graduacao sao consideradas
       */
      iterator = turmas.iterator();
      while (iterator.hasNext()) {
         if (!iterator.next().getNivel().equals("G")) {
            iterator.remove();
         }
      }

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
               System.out.println(" Turma GRANDE: " + turmaDTO.getId() + " - Cod: " + turmaDTO.getCodigoComponente());
               List<br.ufrn.ct.adaptadorturmas.DocenteDTO> listaDocentesAbreviada = new ArrayList<br.ufrn.ct.adaptadorturmas.DocenteDTO>(0);
               for (int i = 0; i < 2; i++) {
                  turmaDTO.getDocentesList().get(i).setNome(ManipuladorNomes.abreviaNome(turmaDTO.getDocentesList().get(i).getNome()));
                  listaDocentesAbreviada.add(turmaDTO.getDocentesList().get(i));
               }
               turmaDTO.setDocentesList(listaDocentesAbreviada);
            }
         }
      }

      System.out.println("## QTD de TURMAS (02): " + turmas.size());
      for (br.ufrn.ct.adaptadorturmas.TurmaDTO turmaDTO : turmas) {
         List<br.ufrn.ct.adaptadorturmas.DocenteDTO> docentes = turmaDTO.getDocentesList();
         for (br.ufrn.ct.adaptadorturmas.DocenteDTO docenteDTO : docentes) {
            System.out.println("Turma: " + turmaDTO.getId() + " - Docente: " + docenteDTO.getNome());
         }
      }

   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

}
