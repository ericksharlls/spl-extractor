
package br.ufrn.ct.cronos.service.obterturma;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.atualizarturma.vo.DadosDocente;
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
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.obterturma.vo.DadosObterTurma;
import br.ufrn.ct.cronos.obterturma.vo.ObterTurma;
import br.ufrn.ct.cronos.obterturma.vo.RespostaObterTurma;
import br.ufrn.ct.cronos.service.ManipuladorNomes;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceObterTurma extends AbstractService<ObterTurma, RespostaObterTurma> {

   private TurmaDao turmaDao;
   private PerfilSalaTurmaDao perfilSalaTurmaDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private HorarioDao horarioDao;
   private TurmaDocenteDao turmaDocenteDao;
   private FuncionarioDao funcionarioDao;

   @Override
   public RespostaObterTurma processa(ObterTurma solicitacao) throws NegocioException {
      Auxiliar auxiliar = new Auxiliar();
      Turma turma = this.turmaDao.get(solicitacao.getId());
      if (turma == null)
         throw new NegocioException(ErrorCode.TURMA_NAO_ENCONTRADO);

      DadosObterTurma dados = new DadosObterTurma();
      List<Sala> salas = this.disponibilidadeSalaDao.getSalasPorTurma(turma.getId());
      PerfilSalaTurma tipo = this.perfilSalaTurmaDao.get(turma.getIdTipo());

      dados.setId(turma.getId());
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
      List<DadosDocente> listaDadosDocentes = new ArrayList<DadosDocente>(0);
      for (TurmaDocente turmaDocente : listaTurmaDocente) {
         DadosDocente dadosDocente = new DadosDocente();
         dadosDocente.setId(turmaDocente.getIdDocente());
         dadosDocente.setNome(this.funcionarioDao.get(turmaDocente.getIdDocente()).getNome());
         listaDadosDocentes.add(dadosDocente);
      }
      dados.setDocentes(listaDadosDocentes);

      dados.setIdTipoSala(turma.getIdTipo());
      dados.setIdPredio(turma.getIdPredio());
      dados.setNomeDisciplina(turma.getNomeDisciplina());
      dados.setTurma(turma.getNumero());
      dados.setCapacidade(turma.getCapacidade());
      dados.setHorario(turma.getHorario());
      dados.setDistribuir(turma.getDistribuir());
      dados.setPerfil(tipo.getNome());

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
      return new RespostaObterTurma(dados);
   }

   @Override
   public void valida(ObterTurma solicitacao) throws NegocioException {
      if (solicitacao.getId() == null) {
         throw new NegocioException(ErrorCode.ID_VAZIO);
      }
   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setPerfilSalaTurmaDao(PerfilSalaTurmaDao perfilSalaTurmaDao) {
      this.perfilSalaTurmaDao = perfilSalaTurmaDao;
   }

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
