
package br.ufrn.ct.cronos.service.alocarturma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.alocarturma.vo.AlocarTurma;
import br.ufrn.ct.cronos.alocarturma.vo.RespostaAlocarTurma;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.FeriadoDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.DisponibilidadeSala;
import br.ufrn.ct.cronos.entity.Periodo;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.service.ManipuladorDatas;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;
import dev.home.componente.utils.date.DateUtils;

public class ServiceAlocarTurma extends AbstractService<AlocarTurma, RespostaAlocarTurma> {

   private TurmaDao turmaDao;
   private SalaDao salaDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private HorarioDao horarioDao;
   private PeriodoDao periodoDao;
   private FeriadoDao feriadoDao;

   @Override
   public RespostaAlocarTurma processa(AlocarTurma solicitacao) throws NegocioException {
      Auxiliar auxiliar = new Auxiliar();
      Sala sala = this.salaDao.get(solicitacao.getIdSala());
      Turma turma = this.turmaDao.get(solicitacao.getIdTurma());
      Periodo periodo = this.periodoDao.get(turma.getIdPeriodo());

      if (solicitacao.getHorarioDesmembrado()) {
         String[] arrayDias = auxiliar.retornaArrayDias(solicitacao.getHorario());
         List<Long> idsHorarios = new ArrayList<Long>(0);
         String[] arrayHorarios = auxiliar.retornaArrayHorarios(solicitacao.getHorario());
         String turno = auxiliar.retornaTurno(solicitacao.getHorario());
         for (int k = 0; k < arrayHorarios.length; k++) {
            idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
         }
         for (DisponibilidadeSala d : this.disponibilidadeSalaDao
                  .getDisponibilidadesSalaPorTurmaHorariosEDiaDaSemana(turma, idsHorarios, Integer.parseInt(arrayDias[0]))) {
            this.disponibilidadeSalaDao.delete(d);
         }
      } else {
         for (DisponibilidadeSala d : this.disponibilidadeSalaDao.getDisponibilidadesSalaPorTurma(turma)) {
            this.disponibilidadeSalaDao.delete(d);
         }
      }

      for (int h = 0; h < auxiliar.contadorDeGruposComSabado(solicitacao.getHorario()); h++) {
         List<Long> idsHorarios = new ArrayList<Long>(0);
         String grupo = auxiliar.retornaGrupoComSabado(solicitacao.getHorario(), h);
         String turno = auxiliar.retornaTurno(grupo);
         String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

         for (int k = 0; k < arrayHorarios.length; k++) {
            idsHorarios.add(this.horarioDao.getByTurnoEHorario(turno, Integer.parseInt(arrayHorarios[k])).getId());
         }
         List<Date> feriados = this.feriadoDao.getDatasFeriadosByPeriodo(periodo.getId());
         List<Date> datasParaReserva = ManipuladorDatas.retornaDatasLetivasPorDias(DateUtils.datasEntre(periodo.getDataInicio(),
            periodo.getDataTermino()), feriados, grupo);
         Collections.sort(datasParaReserva, new Comparator<Date>() {

            public int compare(Date d1, Date d2) {
               return d1.compareTo(d2);
            }
         });

         for (Long idHorario : idsHorarios) {
            DisponibilidadeSala disponibilidadeSala = new DisponibilidadeSala();
            disponibilidadeSala.setIdHorarioSala(idHorario);
            disponibilidadeSala.setIdPeriodo(turma.getIdPeriodo());
            disponibilidadeSala.setIdSala(sala.getId());
            disponibilidadeSala.setIdTurma(turma.getId());

            for (Date data : datasParaReserva) {
               disponibilidadeSala.setDataReserva(data);
               this.disponibilidadeSalaDao.save(disponibilidadeSala);
            }
         }

      }

      turma.setDistribuir(false);
      this.turmaDao.merge(turma);

      return new RespostaAlocarTurma();
   }

   @Override
   public void valida(AlocarTurma solicitacao) throws NegocioException {
      if ((solicitacao.getIdTurma() == null) || (solicitacao.getIdTurma().equals(new Long(0)))) {
         throw new NegocioException(ErrorCode.TURMA_VAZIA);
      }
      if ((solicitacao.getIdSala() == null) || (solicitacao.getIdSala().equals(new Long(0)))) {
         throw new NegocioException(ErrorCode.SALA_VAZIA);
      }
      Sala s = this.salaDao.get(solicitacao.getIdSala());
      Turma t = this.turmaDao.get(solicitacao.getIdTurma());
      Auxiliar auxiliar = new Auxiliar();
      /*
       * if (!auxiliar.validarHorarioComSabado(t.getHorario())) { throw new NegocioException(ErrorCode.TURMA_COM_HORARIO_INVALIDO); }
       */

      if (!auxiliar.validarHorarioComSabado(solicitacao.getHorario())) {
         throw new NegocioException(ErrorCode.TURMA_COM_HORARIO_INVALIDO);
      }
      t.setHorario(solicitacao.getHorario());
      if (!this.disponibilidadeSalaDao.verificarDisponibilidadeSala(t, s.getId())) {
         throw new NegocioException(ErrorCode.NAO_PODE_REALIZAR_ALOCACAO);
      }

   }

   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }

   public void setSalaDao(SalaDao salaDao) {
      this.salaDao = salaDao;
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   /**
    * Atribui o novo valor de horarioDao
    * @param horarioDao horarioDao que será atribuído
    */
   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setFeriadoDao(FeriadoDao feriadoDao) {
      this.feriadoDao = feriadoDao;
   }

}
