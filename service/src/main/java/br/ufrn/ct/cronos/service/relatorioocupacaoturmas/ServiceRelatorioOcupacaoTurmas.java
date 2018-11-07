package br.ufrn.ct.cronos.service.relatorioocupacaoturmas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.dao.ChaveDao;
import br.ufrn.ct.cronos.dao.DisponibilidadeSalaDao;
import br.ufrn.ct.cronos.dao.HistoricoChaveDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.ParametrosRelatoriosDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.TurmaDao;
import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.Chave;
import br.ufrn.ct.cronos.entity.HistoricoChave;
import br.ufrn.ct.cronos.entity.Horario;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.entity.Turma;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.relatorioocupacaoturmas.vo.DadosOcupacao;
import br.ufrn.ct.cronos.relatorioocupacaoturmas.vo.DadosOcupacaoTurmas;
import br.ufrn.ct.cronos.relatorioocupacaoturmas.vo.RelatorioOcupacaoTurmas;
import br.ufrn.ct.cronos.relatorioocupacaoturmas.vo.RespostaRelatorioOcupacaoTurmas;
import br.ufrn.ct.cronos.service.ParametrosRelatoriosEnum;
import br.ufrn.ct.cronos.service.relatoriohorariosalas.AuxiliarRelatorioHorarioSalas;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRelatorioOcupacaoTurmas extends AbstractService<RelatorioOcupacaoTurmas, RespostaRelatorioOcupacaoTurmas> {

   private HorarioDao horarioDao;
   private TurmaDao turmaDao;
   private ChaveDao chaveDao;
   private DisponibilidadeSalaDao disponibilidadeSalaDao;
   private HistoricoChaveDao historicoChaveDao;
   private ParametrosRelatoriosDao parametrosRelatoriosDao;
   private PeriodoDao periodoDao;
 
   @Override
   public RespostaRelatorioOcupacaoTurmas processa(RelatorioOcupacaoTurmas solicitacao) throws NegocioException {
      solicitacao.getDataInicial().setHours(0);
      solicitacao.getDataInicial().setMinutes(0);
      solicitacao.getDataInicial().setSeconds(0);
      solicitacao.getDataFinal().setHours(23);
      solicitacao.getDataFinal().setMinutes(59);
      solicitacao.getDataFinal().setSeconds(59);

      Auxiliar auxiliar = new Auxiliar();
      AuxiliarRelatorioHorarioSalas auxiliarRelatorioHorarioSalas = new AuxiliarRelatorioHorarioSalas();
 
      List<DadosOcupacaoTurmas> dadosOcupacaoTurmas = new ArrayList<DadosOcupacaoTurmas>(0);

      Turma turma = this.turmaDao.get(solicitacao.getIdTurma());
      Sala sala = this.disponibilidadeSalaDao.getSalaPorTurma(turma.getId());
      String chavesTemporarias = null;
      List<Chave> chavesUtilizadas = this.chaveDao.chavesUtilizadas(sala.getId());
      for (Chave chave : chavesUtilizadas) {
         chavesTemporarias = " " + chave.getCodigo();
      }
			
      DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
			
      for (int i = 0; i < auxiliar.contadorDeGruposComSabado(turma.getHorario()); i++) {
         String grupo = auxiliar.retornaGrupoComSabado(turma.getHorario(), i);
         String[] horarios = auxiliar.retornaArrayHorarios(grupo);
         String[] diasDaSemana = auxiliar.retornaArrayDias(grupo);
			
         DadosOcupacaoTurmas dadosOcupacaoTurma = new DadosOcupacaoTurmas();
         dadosOcupacaoTurma.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
                  .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
         dadosOcupacaoTurma.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
                  .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
         dadosOcupacaoTurma.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
                  .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());
         dadosOcupacaoTurma.setSemestre(this.periodoDao.getPeriodoPorData(new Date()).getNome());

         List<DadosOcupacao> dadosOcupacoes = new ArrayList<DadosOcupacao>(0);

         dadosOcupacaoTurma.setTurma(" " + turma.getCodigoDisciplina() + " - N\u00BA: " + turma.getNumero() + " - Hor\u00E1rio: "
            + turma.getHorario());
         dadosOcupacaoTurma.setChavesUtilizadas(chavesTemporarias);
         dadosOcupacaoTurma.setPeriodo(" " + formatadorData.format(solicitacao.getDataInicial()) + " a "
            + formatadorData.format(solicitacao.getDataFinal()));

         if (auxiliar.retornaTurno(grupo).equals("M")) {
            dadosOcupacaoTurma.setTurno("Manh\u00E3 - " + grupo);
         } else if (auxiliar.retornaTurno(grupo).equals("T")) {
            dadosOcupacaoTurma.setTurno("Tarde - " + grupo);
         } else if (auxiliar.retornaTurno(grupo).equals("N")) {
            dadosOcupacaoTurma.setTurno("Noite - " + grupo);
         }
         for (Chave chave : chavesUtilizadas) {

            for (Date dataTurma : auxiliarRelatorioHorarioSalas.retornaDatasDaTurmaPorHorario(solicitacao.getDataInicial(),
               solicitacao.getDataFinal(), diasDaSemana)) {
               DadosOcupacao dadosOcupacao = new DadosOcupacao();
               dadosOcupacao.setChave(chave.getCodigo());
               dadosOcupacao.setData(formatadorData.format(dataTurma) + "\n("
                  + auxiliarRelatorioHorarioSalas.retornaStringDiaDaSemana(dataTurma) + ")");
						
               for (HistoricoChave entrada : this.historicoChaveDao.getEntradasPorPeriodoEChave(solicitacao.getDataInicial(),
                  solicitacao.getDataFinal(), chave.getId())) {
                  HistoricoChave saida = this.historicoChaveDao.getUltimaSaidaPorEntrada(entrada);
                  if (auxiliarRelatorioHorarioSalas.isIntervaloMaiorQueDezMinutos(saida.getHoraRealizacao(), entrada.getHoraRealizacao())) {
                     for (int j = 0; j < horarios.length; j++) {
                        Horario horario =
                           this.horarioDao.getByTurnoEHorario(auxiliar.retornaTurno(grupo), Integer.valueOf(Integer.parseInt(horarios[j])));
                        dataTurma.setHours(horario.getHorarioIntermediario().getHours());
                        dataTurma.setMinutes(horario.getHorarioIntermediario().getMinutes());
                        dataTurma.setSeconds(horario.getHorarioIntermediario().getSeconds());

                        if (auxiliarRelatorioHorarioSalas.isDataIntermediaria(saida.getHoraRealizacao(), entrada.getHoraRealizacao(),
                           dataTurma)) {
                           if (Integer.parseInt(horarios[j]) == 1)
                              dadosOcupacao.setPrimeiroHorario("O");
                           else if (Integer.parseInt(horarios[j]) == 2)
                              dadosOcupacao.setSegundoHorario("O");
                           else if (Integer.parseInt(horarios[j]) == 3)
                              dadosOcupacao.setTerceiroHorario("O");
                           else if (Integer.parseInt(horarios[j]) == 4)
                              dadosOcupacao.setQuartoHorario("O");
                           else if (Integer.parseInt(horarios[j]) == 5)
                              dadosOcupacao.setQuintoHorario("O");
                           else if (Integer.parseInt(horarios[j]) == 6) {
                              dadosOcupacao.setSextoHorario("O");
									}
								}
							}
						}
					}
					
               dadosOcupacoes.add(dadosOcupacao);
				}

			}
         dadosOcupacaoTurma.setDadosOcupacoes(dadosOcupacoes);
         dadosOcupacaoTurmas.add(dadosOcupacaoTurma);
		}
		
      return new RespostaRelatorioOcupacaoTurmas(dadosOcupacaoTurmas);
   }

   public void valida(RelatorioOcupacaoTurmas solicitacao) throws NegocioException {
      Turma turma = this.turmaDao.get(solicitacao.getIdTurma());
      Auxiliar auxiliar = new Auxiliar();

      if (solicitacao.getDataInicial() == null) {
         throw new NegocioException(ErrorCode.DATA_INICIAL_VAZIO);
      }
      if (solicitacao.getDataFinal() == null) {
         throw new NegocioException(ErrorCode.DATA_FINAL_VAZIO);
      }
      if (solicitacao.getIdPeriodo() == 0 || solicitacao.getIdPeriodo() == null) {
         throw new NegocioException(ErrorCode.ID_PERIODO);
      }
      if (solicitacao.getIdPredio() == 0 || solicitacao.getIdPredio() == null) {
         throw new NegocioException(ErrorCode.ID_PREDIO);
      }
      if (solicitacao.getIdTurma() == null || solicitacao.getIdTurma() == 0) {
         throw new NegocioException(ErrorCode.TURMA_VAZIA);
      }
      if (solicitacao.getDataFinal().before(solicitacao.getDataInicial())) {
         throw new NegocioException(ErrorCode.DATA_FINAL_INVALIDA);
      }
      if (this.disponibilidadeSalaDao.getSalaPorTurma(turma.getId()) == null) {
         throw new NegocioException(ErrorCode.TURMA_SEM_SALA);
      }
      if (!auxiliar.validarHorarioComSabado(turma.getHorario())) {
         throw new NegocioException(ErrorCode.TURMA_COM_HORARIO_INVALIDO);
		}
   }
		
   public void setHorarioDao(HorarioDao horarioDao) {
      this.horarioDao = horarioDao;
   }
		
   public void setTurmaDao(TurmaDao turmaDao) {
      this.turmaDao = turmaDao;
   }
		
   public void setChaveDao(ChaveDao chaveDao) {
      this.chaveDao = chaveDao;
   }
	 
   public void setHistoricoChaveDao(HistoricoChaveDao historicoChaveDao) {
      this.historicoChaveDao = historicoChaveDao;
   }

   public void setDisponibilidadeSalaDao(DisponibilidadeSalaDao disponibilidadeSalaDao) {
      this.disponibilidadeSalaDao = disponibilidadeSalaDao;
   }

   public void setParametrosRelatoriosDao(ParametrosRelatoriosDao parametrosRelatoriosDao) {
      this.parametrosRelatoriosDao = parametrosRelatoriosDao;
   }
   
   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }
	
}
