
package br.ufrn.ct.cronos.service.permutarturma;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.FuncionarioDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.PerfilSalaTurmaDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.dao.TurmaDocenteDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.PerfilSalaTurma;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.entity.TurmaDocente;
import br.ufrn.ct.cronos.permutarturma.vo.DadosObterTurmaCompleta;
import br.ufrn.ct.cronos.permutarturma.vo.ObterTurmaCompleta;
import br.ufrn.ct.cronos.permutarturma.vo.RespostaObterTurmaCompleta;
import br.ufrn.ct.cronos.service.ManipuladorNomes;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceObterTurmaCompleta extends AbstractService<ObterTurmaCompleta, RespostaObterTurmaCompleta> {

   private TurmaDao turmaDao;
   private PerfilSalaTurmaDao perfilSalaTurmaDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private HorarioDao horarioDao;
   private TurmaDocenteDao turmaDocenteDao;
   private FuncionarioDao funcionarioDao;

   public RespostaObterTurmaCompleta processa(ObterTurmaCompleta solicitacao) throws NegocioException {
      DadosObterTurmaCompleta dados = new DadosObterTurmaCompleta();
      if (solicitacao.getId() != null && solicitacao.getId() != 0) {
         Turma turma = this.turmaDao.get(solicitacao.getId());
         PerfilSalaTurma tipo = this.perfilSalaTurmaDao.get(turma.getIdTipo());

         dados.setCodigoDisciplina(turma.getCodigoDisciplina());
         // Linha abaixo comentada em 07/07/2017. Docente passou a ficar em uma tabela separada
         // dados.setDocente(turma.getDocente());
         List<TurmaDocente> listaTurmaDocente = this.turmaDocenteDao.getTurmaDocentePorTurma(turma.getId());
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
         dados.setDocente(docente);

         dados.setHorario(turma.getHorario());
         dados.setId(turma.getId());
         dados.setNomeDisciplina(turma.getNomeDisciplina());
         dados.setNumero(turma.getNumero());
         dados.setAlunosMatriculados(turma.getAlunosMatriculados());
         dados.setPerfilTurma(tipo.getNome());
         // Obter a(s) sala(s) da Turma
         Auxiliar auxiliar = new Auxiliar();
         List<Sala> salas = this.disponibilidadeSalaDao.getSalasPorTurma(turma.getId());
         dados.setCapacidadesDasSalas("");
         for (Sala sala : salas) {
            dados.setCapacidadesDasSalas(dados.getCapacidadesDasSalas() + sala.getCapacidade() + " (" + sala.getNome() + ") ");
         }
         if (salas.size() > 0) {
            Sala[] arraySalas = (Sala[]) salas.toArray(new Sala[salas.size()]);
            dados.setSala("");
            for (int i = 0; i < arraySalas.length; i++) {
               dados.setSala(dados.getSala() + arraySalas[i].getNome());
               for (int h = 0; h < auxiliar.contadorDeGruposComSabado(turma.getHorario()); h++) {
                  String grupo = auxiliar.retornaGrupoComSabado(turma.getHorario(), h);
                  String turno = auxiliar.retornaTurno(grupo);
                  String[] horariosDoGrupo = auxiliar.retornaArrayHorarios(grupo);
                  String[] diasDoGrupo = auxiliar.retornaArrayDias(grupo);
                  List<String> stringsDias = new ArrayList<String>(0);
                  for (int z = 0; z < diasDoGrupo.length; z++) {
                     stringsDias.add(diasDoGrupo[z]);
                  }
                  List<Long> idsHorarios = new ArrayList<Long>(0);
                  for (int z = 0; z < horariosDoGrupo.length; z++) {
                     idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(horariosDoGrupo[z])).getId());
                  }
                  List<String> horarios =
                     this.disponibilidadeSalaDao.getHorariosPorTurmaESala(turma, arraySalas[i], turno, idsHorarios, stringsDias);
                  String hs = "";
                  String dias = "";
                  for (int r = 0; r < horarios.size(); r++) {
                     if (!auxiliar.jaExisteHorario(hs, auxiliar.retornaHorario(horarios.get(r)))) {
                        hs += auxiliar.retornaHorario(horarios.get(r));
                     }
                     if (r == horarios.size() - 1) {
                        String dia = auxiliar.retornaDia(horarios.get(r));
                        if (!auxiliar.jaExisteDia(dias, dia)) {
                           dias += dia;
                        }
                        dados.setSala(dados.getSala() + " (" + dias + auxiliar.retornaTurno(horarios.get(r)) + hs + ") ");
                     } else {
                        String dia = auxiliar.retornaDia(horarios.get(r));
                        if (!auxiliar.jaExisteDia(dias, dia)) {
                           dias += dia;
                        }
                     }
                  }
               }
            }
         } else {
            dados.setSala("INDEFINIDO");
         }
      }

      return new RespostaObterTurmaCompleta(dados);
   }

   public void valida(ObterTurmaCompleta solicitacao) throws NegocioException {

   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setPerfilSalaTurmaDao(PerfilSalaTurmaDao perfilSalaTurmaDao) {
      this.perfilSalaTurmaDao = perfilSalaTurmaDao;
   }

   /**
    * Atribui o novo valor de disponibilidadeSalaDao
    * @param disponibilidadeSalaDao disponibilidadeSalaDao que será atribuído
    */
   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
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
