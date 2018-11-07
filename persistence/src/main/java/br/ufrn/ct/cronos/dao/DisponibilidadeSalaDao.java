package br.ufrn.ct.cronos.dao;

import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.entity.DisponibilidadeSala;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import dev.home.componente.dao.hibernate.Dao;

public interface DisponibilidadeSalaDao extends Dao<Long, DisponibilidadeSala> {

   public boolean verificarDisponibilidadeSala(Turma turma, Long idSala);

   // Depois verificar a possibilidade de exclusao desse metodo
   public boolean verificarTurmaTemSala(Long idTurma);

   public Sala getSalaPorTurma(Long idTurma);

   public List<Sala> getSalasPorTurma(Long idTurma);

   public Sala getSalaDisponivelPorTurma(Turma turma);

   public List<DisponibilidadeSala> getDisponibilidadesSalaPorTurma(Turma turma);

   public List<DisponibilidadeSala> getDisponibilidadesSalaPorTurmaHorariosEDiaDaSemana(Turma turma, List<Long> arrayHorarios,
      int diaDaSemana);

   public List<DisponibilidadeSala> getDisponibilidadesSalaPorTurmaESala(Turma turma, Sala sala);

   public List<String> getHorariosPorTurmaSalaEDiaDaSemana(Turma turma, Sala sala, int diaDaSemana, String turno);

   public List<DisponibilidadeSala> getDisponibilidadesSalaPorSalaTurnoEPeriodo(Long idSala, String turno, Long idPeriodo);

   public List<DisponibilidadeSala> getAgendamentosPorSalaTurnoEPeriodo(Long idSala, String turno, Long idPeriodo);

   public boolean verificarDisponibilidadeSalaParaAgendamentos(String horario, Long idSala, List<Date> datas, Long idPeriodo);

   public void removerDisponibilidadesSalaPorAgendamento(Long idAgendamento);

   public void removerAgendamentosAnteriores();

   public Integer contadorOcupacaoHorarioPorDiaDaSemana(Integer diaDaSemana, Long idHorarioSala, Long idPredio, Long idPeriodo);

   public Integer contadorOcupacaoHorarioPorDiaDaSemana(Integer diaDaSemana, Long idHorarioSala, Long idPredio, Long idPeriodo,
      List<Long> idsSalasForaDaEstatistica);

   public Sala getSalaPorAgendamento(Long idAgendamento);

   public List<Date> getDatasReservaPorAgendamento(Long idAgendamento);

   public List<DisponibilidadeSala> getHorariosPorDataReservaAgendamento(Long idAgendamento, Date data);

   // Método criado em 17/05/2016 objetivando
   public List<String> getHorariosPorTurmaESala(Turma turma, Sala sala, String turno, List<Long> arrayHorarios, List<String> stringsDias);

   public List<DisponibilidadeSala> getDisponibilidadesSalaPorIdSala(Long idSala);

}
