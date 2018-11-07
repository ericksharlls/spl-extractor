package br.ufrn.ct.cronos.service.relatorioocupacaohorariossalas;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.ParametrosRelatoriosDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.PredioDao;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.entity.Horario;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.relatorioocupacaohorariossalas.vo.DadosOcupacaoHorario;
import br.ufrn.ct.cronos.relatorioocupacaohorariossalas.vo.DadosPredio;
import br.ufrn.ct.cronos.relatorioocupacaohorariossalas.vo.RelatorioOcupacaoHorariosSalas;
import br.ufrn.ct.cronos.relatorioocupacaohorariossalas.vo.RespostaRelatorioOcupacaoHorariosSalas;
import br.ufrn.ct.cronos.service.ParametrosRelatoriosEnum;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRelatorioOcupacaoHorariosSalas extends
         AbstractService<RelatorioOcupacaoHorariosSalas, RespostaRelatorioOcupacaoHorariosSalas> {

   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private HorarioDao horarioDao;
   private SalaDao salaDao;
   private PeriodoDao periodoDao;
   private PredioDao predioDao;
   private ParametrosRelatoriosDao parametrosRelatoriosDao;

   @Override
   public RespostaRelatorioOcupacaoHorariosSalas processa(RelatorioOcupacaoHorariosSalas solicitacao) throws NegocioException {
      List<DadosPredio> listaDadosPredio = new ArrayList<DadosPredio>(0);

      List<Long> idsSalasForaDaEstatisticaManha = new ArrayList<Long>(0);
      idsSalasForaDaEstatisticaManha.add(new Long(57)); // Id da sala 4G1
      idsSalasForaDaEstatisticaManha.add(new Long(85)); // Id da sala 4H1

      List<Long> idsSalasForaDaEstatisticaTarde = new ArrayList<Long>(0);
      idsSalasForaDaEstatisticaTarde.add(new Long(86)); // Id da sala 4D2
      idsSalasForaDaEstatisticaTarde.add(new Long(57)); // Id da sala 4G1
      idsSalasForaDaEstatisticaTarde.add(new Long(85)); // Id da sala 4H1

      List<Long> idsSalasForaDaEstatisticaNoite = new ArrayList<Long>(0);
      idsSalasForaDaEstatisticaNoite.add(new Long(86)); // Id da sala 4D2
      idsSalasForaDaEstatisticaNoite.add(new Long(57)); // Id da sala 4G1
      idsSalasForaDaEstatisticaNoite.add(new Long(85)); // Id da sala 4H1

      Integer contadorDeSalasManha = this.salaDao.countByPredio(solicitacao.getIdPredio(), idsSalasForaDaEstatisticaManha);
      Integer contadorDeSalasTarde = this.salaDao.countByPredio(solicitacao.getIdPredio(), idsSalasForaDaEstatisticaTarde);
      Integer contadorDeSalasNoite = this.salaDao.countByPredio(solicitacao.getIdPredio(), idsSalasForaDaEstatisticaNoite);

      for (int i = 0; i < 3; i++) {
         switch (i) {
            case 0:
               DadosPredio dadosPredio1 = new DadosPredio();
               dadosPredio1.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
                        .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
               dadosPredio1.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
                        .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
               dadosPredio1.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
                        .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());
               dadosPredio1.setTurno("MANH\u00C3");
               dadosPredio1.setSemestre(this.periodoDao.get(solicitacao.getIdPeriodo()).getNome());
               dadosPredio1.setPredio(this.predioDao.get(solicitacao.getIdPredio()).getNome());
               dadosPredio1.setDadosOcupacaoHorario(getDadosManha(solicitacao.getIdPredio(), solicitacao.getIdPeriodo(),
                  contadorDeSalasManha, idsSalasForaDaEstatisticaManha));
               dadosPredio1
                        .setMensagemRodape("Para o turno da MANH\u00C3 do SETOR DE AULAS IV, est\u00E3o sendo desconsideradas as salas 4G1 e 4H1.");
               listaDadosPredio.add(dadosPredio1);
               break;
            case 1:
               DadosPredio dadosPredio2 = new DadosPredio();
               dadosPredio2.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
                        .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
               dadosPredio2.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
                        .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
               dadosPredio2.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
                        .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());
               dadosPredio2.setTurno("TARDE");
               dadosPredio2.setSemestre(this.periodoDao.get(solicitacao.getIdPeriodo()).getNome());
               dadosPredio2.setPredio(this.predioDao.get(solicitacao.getIdPredio()).getNome());
               dadosPredio2.setDadosOcupacaoHorario(getDadosTarde(solicitacao.getIdPredio(), solicitacao.getIdPeriodo(),
                  contadorDeSalasTarde, idsSalasForaDaEstatisticaTarde));
               dadosPredio2
                        .setMensagemRodape("Para o turno da TARDE do SETOR DE AULAS IV, est\u00E3o sendo desconsideradas as salas 4D2, 4G1 e 4H1.");
               listaDadosPredio.add(dadosPredio2);
               break;
            case 2:
               DadosPredio dadosPredio3 = new DadosPredio();
               dadosPredio3.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
                        .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
               dadosPredio3.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
                        .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
               dadosPredio3.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
                        .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());
               dadosPredio3.setTurno("NOITE");
               dadosPredio3.setSemestre(this.periodoDao.get(solicitacao.getIdPeriodo()).getNome());
               dadosPredio3.setPredio(this.predioDao.get(solicitacao.getIdPredio()).getNome());
               dadosPredio3.setDadosOcupacaoHorario(getDadosNoite(solicitacao.getIdPredio(), solicitacao.getIdPeriodo(),
                  contadorDeSalasNoite, idsSalasForaDaEstatisticaNoite));
               dadosPredio3
                        .setMensagemRodape("Para o turno da NOITE do SETOR DE AULAS IV, est\u00E3o sendo desconsideradas as salas 4D2, 4G1 e 4H1.");
               listaDadosPredio.add(dadosPredio3);
         }
      }

      return new RespostaRelatorioOcupacaoHorariosSalas(listaDadosPredio);
   }

   @Override
   public void valida(RelatorioOcupacaoHorariosSalas solicitacao) throws NegocioException {
      if (solicitacao.getIdPredio() == 0 || solicitacao.getIdPredio() == null) {
         throw new NegocioException(ErrorCode.ID_PREDIO);
      }
      if (solicitacao.getIdPeriodo() == 0 || solicitacao.getIdPeriodo() == null) {
         throw new NegocioException(ErrorCode.ID_PERIODO);
      }
   }

   public List<DadosOcupacaoHorario> getDadosManha(Long idPredio, Long idPeriodo, Integer contadorDeSalas,
      List<Long> idsSalasForaDaEstatistica) {
      DateFormat formatter = new SimpleDateFormat("HH:mm");
      NumberFormat formatador = NumberFormat.getPercentInstance();
      List<DadosOcupacaoHorario> retorno = new ArrayList<DadosOcupacaoHorario>(0);
      for (Horario horario : this.horarioDao.getByTurno("M")) {
         DadosOcupacaoHorario dadosOcupacaoHorario = new DadosOcupacaoHorario();
         Integer contador = new Integer(0);
         
         dadosOcupacaoHorario.setHorario(formatter.format(horario.getInicioHorarioAbsoluto()) + " \u00E0s "
            + formatter.format(horario.getTerminoHorarioAbsoluto()));

               for (int dia = 2; dia < 8; dia++) {
                  switch (dia) {
                     case 2:
                        contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioSegunda(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                        break;
                     case 3:
                        contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioTerca(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                        break;
                     case 4:
                        contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioQuarta(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                        break;
                     case 5:
                        contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioQuinta(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                        break;
                     case 6:
                        contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioSexta(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                        break;
                     case 7:
                        contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioSabado(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                        break;
                  }
               }
         retorno.add(dadosOcupacaoHorario);

         }

      return retorno;
   }

   public List<DadosOcupacaoHorario> getDadosTarde(Long idPredio, Long idPeriodo, Integer contadorDeSalas,
      List<Long> idsSalasForaDaEstatistica) {
      DateFormat formatter = new SimpleDateFormat("HH:mm");
      NumberFormat formatador = NumberFormat.getPercentInstance();
      List<DadosOcupacaoHorario> retorno = new ArrayList<DadosOcupacaoHorario>(0);
      for (Horario horario : this.horarioDao.getByTurno("T")) {
         DadosOcupacaoHorario dadosOcupacaoHorario = new DadosOcupacaoHorario();
         Integer contador = new Integer(0);

         dadosOcupacaoHorario.setHorario(formatter.format(horario.getInicioHorarioAbsoluto()) + " \u00E0s "
            + formatter.format(horario.getTerminoHorarioAbsoluto()));

         for (int dia = 2; dia < 8; dia++) {
            switch (dia) {
               case 2:
                  contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioSegunda(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                  break;
               case 3:
                  contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioTerca(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                  break;
               case 4:
                  contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioQuarta(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                  break;
               case 5:
                  contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioQuinta(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                  break;
               case 6:
                  contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioSexta(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                  break;
               case 7:
                  contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioSabado(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                  break;
            }

         }
         retorno.add(dadosOcupacaoHorario);

      }

      return retorno;
   }

   public List<DadosOcupacaoHorario> getDadosNoite(Long idPredio, Long idPeriodo, Integer contadorDeSalas,
      List<Long> idsSalasForaDaEstatistica) {
      DateFormat formatter = new SimpleDateFormat("HH:mm");
      NumberFormat formatador = NumberFormat.getPercentInstance();
      List<DadosOcupacaoHorario> retorno = new ArrayList<DadosOcupacaoHorario>(0);
      for (Horario horario : this.horarioDao.getByTurno("N")) {
         DadosOcupacaoHorario dadosOcupacaoHorario = new DadosOcupacaoHorario();
         Integer contador = new Integer(0);

         dadosOcupacaoHorario.setHorario(formatter.format(horario.getInicioHorarioAbsoluto()) + " \u00E0s "
            + formatter.format(horario.getTerminoHorarioAbsoluto()));

         for (int dia = 2; dia < 8; dia++) {
            switch (dia) {
               case 2:
                  contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioSegunda(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                  break;
               case 3:
                  contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioTerca(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                  break;
               case 4:
                  contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioQuarta(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                  break;
               case 5:
                  contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioQuinta(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                  break;
               case 6:
                  contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioSexta(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                  break;
               case 7:
                  contador =
                     this.disponibilidadeSalaDao.contadorOcupacaoHorarioPorDiaDaSemana(dia, horario.getId(), idPredio, idPeriodo,
                        idsSalasForaDaEstatistica);
                  dadosOcupacaoHorario.setHorarioSabado(Integer.toString(contador) + "/" + contadorDeSalas + "\n"
                     + formatador.format(new Double(contador) / new Double(contadorDeSalas)));
                  break;
            }

         }
         retorno.add(dadosOcupacaoHorario);

      }

      return retorno;
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }

   public void setSalaDao(SalaDao salaDao) {
      this.salaDao = salaDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

   public void setPredioDao(PredioDao predioDao) {
      this.predioDao = predioDao;
   }

   public void setParametrosRelatoriosDao(ParametrosRelatoriosDao parametrosRelatoriosDao) {
      this.parametrosRelatoriosDao = parametrosRelatoriosDao;
   }

}
