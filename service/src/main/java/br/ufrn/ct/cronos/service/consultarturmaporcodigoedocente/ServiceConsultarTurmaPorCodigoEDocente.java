package br.ufrn.ct.cronos.service.consultarturmaporcodigoedocente;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.consultarturmaporcodigoedocente.vo.ConsultarTurmaPorCodigoEDocente;
import br.ufrn.ct.cronos.consultarturmaporcodigoedocente.vo.DadosConsultarTurmaPorCodigoEDocente;
import br.ufrn.ct.cronos.consultarturmaporcodigoedocente.vo.RespostaConsultarTurmaPorCodigoEDocente;
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
import br.ufrn.ct.cronos.service.ManipuladorNomes;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceConsultarTurmaPorCodigoEDocente extends
         AbstractService<ConsultarTurmaPorCodigoEDocente, RespostaConsultarTurmaPorCodigoEDocente> {

   private TurmaDao turmaDao;
   private PerfilSalaTurmaDao perfilSalaTurmaDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private HorarioDao horarioDao;
   private TurmaDocenteDao turmaDocenteDao;
   private FuncionarioDao funcionarioDao;

   @Override
   public RespostaConsultarTurmaPorCodigoEDocente processa(ConsultarTurmaPorCodigoEDocente solicitacao) throws NegocioException {
      List<DadosConsultarTurmaPorCodigoEDocente> dadosTurmas = new ArrayList<DadosConsultarTurmaPorCodigoEDocente>(0);
      Integer totalNumeroLinhas = 0;
      
      if (solicitacao.getDocente().equals("")) {
    	  totalNumeroLinhas =
     	         this.turmaDao.contadorTurmasPorCodigoEDisciplina(solicitacao.getIdPeriodo(), solicitacao.getCodigo(), solicitacao.getNomeDisciplina());
          for (Turma t : this.turmaDao.getTurmasPorCodigoEDisciplina(solicitacao.getIdPeriodo(), solicitacao.getStartPage(),
     	         solicitacao.getMaxPage(), solicitacao.getCodigo(), solicitacao.getNomeDisciplina())) {
     	         DadosConsultarTurmaPorCodigoEDocente dados = new DadosConsultarTurmaPorCodigoEDocente();
     	         dados.setCodigoDisciplina(t.getCodigoDisciplina());
     	         // ############################################
     	         // Alterando a forma de obtencao dos docentes em 24/07/2017
     	         List<TurmaDocente> listaTurmaDocente = this.turmaDocenteDao.getTurmaDocentePorTurma(t.getId());
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

     	         dados.setHorario(t.getHorario());
     	         dados.setId(t.getId());
     	         dados.setNomeDisciplina(t.getNomeDisciplina());

     	         PerfilSalaTurma perfil = this.perfilSalaTurmaDao.get(t.getIdTipo());
     	         dados.setPerfil(perfil.getNome());

     	         // Obter as sala(s) da Turma
     	         Auxiliar auxiliar = new Auxiliar();
     	         List<Sala> salas = this.disponibilidadeSalaDao.getSalasPorTurma(t.getId());
     	         if (salas.size() > 0) {
     	            Sala[] arraySalas = (Sala[]) salas.toArray(new Sala[salas.size()]);
     	            dados.setSala("");
     	            for (int i = 0; i < arraySalas.length; i++) {
     	               dados.setSala(dados.getSala() + arraySalas[i].getNome());
     	               for (int h = 0; h < auxiliar.contadorDeGruposComSabado(t.getHorario()); h++) {
     	                  String grupo = auxiliar.retornaGrupoComSabado(t.getHorario(), h);
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
     	                     this.disponibilidadeSalaDao.getHorariosPorTurmaESala(t, arraySalas[i], turno, idsHorarios, stringsDias);
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
     	         dadosTurmas.add(dados);
     	      }
      } else {
    	  totalNumeroLinhas =
      	         this.turmaDao.contadorTurmasPorCodigoDocenteOuDisciplina(solicitacao.getIdPeriodo(), solicitacao.getCodigo(), solicitacao.getDocente(), solicitacao.getNomeDisciplina());
          for (Turma t : this.turmaDao.getTurmasPorCodigoDocenteOuDisciplina(solicitacao.getIdPeriodo(), solicitacao.getStartPage(),
        	         solicitacao.getMaxPage(), solicitacao.getCodigo(), solicitacao.getDocente(), solicitacao.getNomeDisciplina())) {
        	         DadosConsultarTurmaPorCodigoEDocente dados = new DadosConsultarTurmaPorCodigoEDocente();
        	         dados.setCodigoDisciplina(t.getCodigoDisciplina());
        	         // ############################################
        	         // Alterando a forma de obtencao dos docentes em 24/07/2017
        	         List<TurmaDocente> listaTurmaDocente = this.turmaDocenteDao.getTurmaDocentePorTurma(t.getId());
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

        	         dados.setHorario(t.getHorario());
        	         dados.setId(t.getId());
        	         dados.setNomeDisciplina(t.getNomeDisciplina());

        	         PerfilSalaTurma perfil = this.perfilSalaTurmaDao.get(t.getIdTipo());
        	         dados.setPerfil(perfil.getNome());

        	         // Obter as sala(s) da Turma
        	         Auxiliar auxiliar = new Auxiliar();
        	         List<Sala> salas = this.disponibilidadeSalaDao.getSalasPorTurma(t.getId());
        	         if (salas.size() > 0) {
        	            Sala[] arraySalas = (Sala[]) salas.toArray(new Sala[salas.size()]);
        	            dados.setSala("");
        	            for (int i = 0; i < arraySalas.length; i++) {
        	               dados.setSala(dados.getSala() + arraySalas[i].getNome());
        	               for (int h = 0; h < auxiliar.contadorDeGruposComSabado(t.getHorario()); h++) {
        	                  String grupo = auxiliar.retornaGrupoComSabado(t.getHorario(), h);
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
        	                     this.disponibilidadeSalaDao.getHorariosPorTurmaESala(t, arraySalas[i], turno, idsHorarios, stringsDias);
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
        	         dadosTurmas.add(dados);
        	      }
      }

      return new RespostaConsultarTurmaPorCodigoEDocente(dadosTurmas, totalNumeroLinhas);
   }

   @Override
   public void valida(ConsultarTurmaPorCodigoEDocente solicitacao) throws NegocioException {

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
