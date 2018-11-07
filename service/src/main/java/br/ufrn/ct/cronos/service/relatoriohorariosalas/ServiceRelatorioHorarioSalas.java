package br.ufrn.ct.cronos.service.relatoriohorariosalas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.ufrn.ct.cronos.dao.ChaveDao;
import br.ufrn.ct.cronos.dao.HistoricoChaveDao;
import br.ufrn.ct.cronos.dao.HorarioDao;
import br.ufrn.ct.cronos.dao.ParametrosRelatoriosDao;
import br.ufrn.ct.cronos.dao.PeriodoDao;
import br.ufrn.ct.cronos.dao.SalaDao;
import br.ufrn.ct.cronos.entity.Chave;
import br.ufrn.ct.cronos.entity.HistoricoChave;
import br.ufrn.ct.cronos.entity.Horario;
import br.ufrn.ct.cronos.entity.Sala;
import br.ufrn.ct.cronos.error.ErrorCode;
import br.ufrn.ct.cronos.relatoriohorariosalas.vo.DadosHorario;
import br.ufrn.ct.cronos.relatoriohorariosalas.vo.DadosHorarioSalas;
import br.ufrn.ct.cronos.relatoriohorariosalas.vo.RelatorioHorarioSalas;
import br.ufrn.ct.cronos.relatoriohorariosalas.vo.RespostaRelatorioHorarioSalas;
import br.ufrn.ct.cronos.service.ParametrosRelatoriosEnum;
import dev.home.componente.service.AbstractService;
import dev.home.componente.service.excecao.NegocioException;

public class ServiceRelatorioHorarioSalas extends AbstractService<RelatorioHorarioSalas, RespostaRelatorioHorarioSalas> {
	
	private SalaDao salaDao;
	private ChaveDao chaveDao;
	private HistoricoChaveDao historicoChaveDao;
	private HorarioDao horarioDao;
   private ParametrosRelatoriosDao parametrosRelatoriosDao;
   private PeriodoDao periodoDao;

	@Override
	public RespostaRelatorioHorarioSalas processa(RelatorioHorarioSalas solicitacao) throws NegocioException {
		solicitacao.getDataInicial().setHours(0);
		solicitacao.getDataInicial().setMinutes(0);
		solicitacao.getDataInicial().setSeconds(0);
		solicitacao.getDataFinal().setHours(23);
		solicitacao.getDataFinal().setMinutes(59);
		solicitacao.getDataFinal().setSeconds(59);

		DadosHorarioSalas dadosHorarioSala = new DadosHorarioSalas();
      dadosHorarioSala.setSemestre(this.periodoDao.getPeriodoPorData(new Date()).getNome());
      dadosHorarioSala.setPrimeiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.PRIMEIRA_LINHA_CABECALHO.name()).getTexto());
      dadosHorarioSala.setSegundaLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.SEGUNDA_LINHA_CABECALHO.name()).getTexto());
      dadosHorarioSala.setTerceiraLinhaCabecalho(this.parametrosRelatoriosDao
               .getByIdentificadorParametro(ParametrosRelatoriosEnum.TERCEIRA_LINHA_CABECALHO.name()).getTexto());

		List<DadosHorarioSalas> dadosHorariosSalas = new ArrayList<DadosHorarioSalas>(0);
		List<DadosHorario> dadosHorarios = new ArrayList<DadosHorario>(0);
		AuxiliarRelatorioHorarioSalas auxiliarRelatorioHorarioSalas = new AuxiliarRelatorioHorarioSalas();

      for (Sala sala : this.salaDao.getAllByPredio(solicitacao.getIdPredio())) {
			DadosHorario dadosHorario = new DadosHorario();
			dadosHorario.setSala(sala.getNome());
			for (Chave chave : this.chaveDao.getBySala(sala.getId())){
				
				for (HistoricoChave entrada : this.historicoChaveDao.getEntradasPorPeriodoEChave(solicitacao.getDataInicial(), solicitacao.getDataFinal(), chave.getId())) {
					HistoricoChave saida = this.historicoChaveDao.getUltimaSaidaPorEntrada(entrada);
					Horario horarioSaida = this.horarioDao.getByHora(saida.getHoraRealizacao());
					Horario horarioEntrada = this.horarioDao.getByHora(entrada.getHoraRealizacao());
					int contador;
          
					if (auxiliarRelatorioHorarioSalas.saoDoMesmoDia(entrada.getHoraRealizacao(), saida.getHoraRealizacao())) {
						if (auxiliarRelatorioHorarioSalas.isIntervaloMaiorQueDezMinutos(saida.getHoraRealizacao(), entrada.getHoraRealizacao())) {
							contador = 0;
							for (Horario horario : this.horarioDao.getIntervaloHorarios(horarioSaida, horarioEntrada)) {
								try {
									if (contador == 0) {
										if (auxiliarRelatorioHorarioSalas.compararApenasHoras(horario.getHorarioIntermediario(), saida.getHoraRealizacao()))
											if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("M"))) {
												Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioManha().intValue() + 1);
												dadosHorario.setPrimeiroHorarioManha(cont);
											} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("M"))) {
												Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioManha().intValue() + 1);
												dadosHorario.setSegundoHorarioManha(cont);
											} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("M"))) {
												Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioManha().intValue() + 1);
												dadosHorario.setTerceiroHorarioManha(cont);
											} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("M"))) {
												Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioManha().intValue() + 1);
												dadosHorario.setQuartoHorarioManha(cont);
											} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("M"))) {
												Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioManha().intValue() + 1);
												dadosHorario.setQuintoHorarioManha(cont);
											} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("M"))) {
												Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioManha().intValue() + 1);
												dadosHorario.setSextoHorarioManha(cont);
											} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("T"))) {
												Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioTarde().intValue() + 1);
												dadosHorario.setPrimeiroHorarioTarde(cont);
											} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("T"))) {
												Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioTarde().intValue() + 1);
												dadosHorario.setSegundoHorarioTarde(cont);
											} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("T"))) {
												Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioTarde().intValue() + 1);
												dadosHorario.setTerceiroHorarioTarde(cont);
											} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("T"))) {
												Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioTarde().intValue() + 1);
												dadosHorario.setQuartoHorarioTarde(cont);
											} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("T"))) {
												Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioTarde().intValue() + 1);
												dadosHorario.setQuintoHorarioTarde(cont);
											} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("T"))) {
												Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioTarde().intValue() + 1);
												dadosHorario.setSextoHorarioTarde(cont);
											} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("N"))) {
												Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioNoite().intValue() + 1);
												dadosHorario.setPrimeiroHorarioNoite(cont);
											} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("N"))) {
												Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioNoite().intValue() + 1);
												dadosHorario.setSegundoHorarioNoite(cont);
											} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("N"))) {
												Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioNoite().intValue() + 1);
												dadosHorario.setTerceiroHorarioNoite(cont);
											} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("N"))) {
												Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioNoite().intValue() + 1);
												dadosHorario.setQuartoHorarioNoite(cont);
											}
									}
									else if (contador == this.horarioDao.getIntervaloHorarios(horarioSaida, horarioEntrada).size() - 1) {
										if (auxiliarRelatorioHorarioSalas.compararApenasHoras(entrada.getHoraRealizacao(), horario.getHorarioIntermediario())) {
											if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("M"))) {
												Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioManha().intValue() + 1);
												dadosHorario.setPrimeiroHorarioManha(cont);
											} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("M"))) {
												Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioManha().intValue() + 1);
												dadosHorario.setSegundoHorarioManha(cont);
											} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("M"))) {
												Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioManha().intValue() + 1);
												dadosHorario.setTerceiroHorarioManha(cont);
											} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("M"))) {
												Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioManha().intValue() + 1);
												dadosHorario.setQuartoHorarioManha(cont);
											} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("M"))) {
												Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioManha().intValue() + 1);
												dadosHorario.setQuintoHorarioManha(cont);
											} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("M"))) {
												Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioManha().intValue() + 1);
												dadosHorario.setSextoHorarioManha(cont);
											} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("T"))) {
												Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioTarde().intValue() + 1);
												dadosHorario.setPrimeiroHorarioTarde(cont);
											} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("T"))) {
												Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioTarde().intValue() + 1);
												dadosHorario.setSegundoHorarioTarde(cont);
											} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("T"))) {
												Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioTarde().intValue() + 1);
												dadosHorario.setTerceiroHorarioTarde(cont);
											} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("T"))) {
												Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioTarde().intValue() + 1);
												dadosHorario.setQuartoHorarioTarde(cont);
											} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("T"))) {
												Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioTarde().intValue() + 1);
												dadosHorario.setQuintoHorarioTarde(cont);
											} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("T"))) {
												Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioTarde().intValue() + 1);
												dadosHorario.setSextoHorarioTarde(cont);
											} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("N"))) {
												Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioNoite().intValue() + 1);
												dadosHorario.setPrimeiroHorarioNoite(cont);
											} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("N"))) {
												Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioNoite().intValue() + 1);
												dadosHorario.setSegundoHorarioNoite(cont);
											} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("N"))) {
												Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioNoite().intValue() + 1);
												dadosHorario.setTerceiroHorarioNoite(cont);
											} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("N"))) {
												Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioNoite().intValue() + 1);
												dadosHorario.setQuartoHorarioNoite(cont);
											}
										}
									}
									else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioManha().intValue() + 1);
										dadosHorario.setPrimeiroHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioManha().intValue() + 1);
										dadosHorario.setSegundoHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioManha().intValue() + 1);
										dadosHorario.setTerceiroHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioManha().intValue() + 1);
										dadosHorario.setQuartoHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioManha().intValue() + 1);
										dadosHorario.setQuintoHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioManha().intValue() + 1);
										dadosHorario.setSextoHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioTarde().intValue() + 1);
										dadosHorario.setPrimeiroHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioTarde().intValue() + 1);
										dadosHorario.setSegundoHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioTarde().intValue() + 1);
										dadosHorario.setTerceiroHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioTarde().intValue() + 1);
										dadosHorario.setQuartoHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioTarde().intValue() + 1);
										dadosHorario.setQuintoHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioTarde().intValue() + 1);
										dadosHorario.setSextoHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("N"))) {
										Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioNoite().intValue() + 1);
										dadosHorario.setPrimeiroHorarioNoite(cont);
									} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("N"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioNoite().intValue() + 1);
										dadosHorario.setSegundoHorarioNoite(cont);
									} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("N"))) {
										Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioNoite().intValue() + 1);
										dadosHorario.setTerceiroHorarioNoite(cont);
									} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("N"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioNoite().intValue() + 1);
										dadosHorario.setQuartoHorarioNoite(cont);
									}
								}
								catch (Exception e) {
									e.printStackTrace();
								}
								contador++;
							}
						}
					} else {
						contador = 0;
						for (Horario horario : this.horarioDao.getHorariosPosteriores(horarioSaida)) {
							if (contador == 0) {
								if (auxiliarRelatorioHorarioSalas.compararApenasHoras(horario.getHorarioIntermediario(), saida.getHoraRealizacao())) {
									if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioManha().intValue() + 1);
										dadosHorario.setPrimeiroHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioManha().intValue() + 1);
										dadosHorario.setSegundoHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioManha().intValue() + 1);
										dadosHorario.setTerceiroHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioManha().intValue() + 1);
										dadosHorario.setQuartoHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioManha().intValue() + 1);
										dadosHorario.setQuintoHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioManha().intValue() + 1);
										dadosHorario.setSextoHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioTarde().intValue() + 1);
										dadosHorario.setPrimeiroHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioTarde().intValue() + 1);
										dadosHorario.setSegundoHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioTarde().intValue() + 1);
										dadosHorario.setTerceiroHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioTarde().intValue() + 1);
										dadosHorario.setQuartoHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioTarde().intValue() + 1);
										dadosHorario.setQuintoHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioTarde().intValue() + 1);
										dadosHorario.setSextoHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("N"))) {
										Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioNoite().intValue() + 1);
										dadosHorario.setPrimeiroHorarioNoite(cont);
									} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("N"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioNoite().intValue() + 1);
										dadosHorario.setSegundoHorarioNoite(cont);
									} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("N"))) {
										Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioNoite().intValue() + 1);
										dadosHorario.setTerceiroHorarioNoite(cont);
									} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("N"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioNoite().intValue() + 1);
										dadosHorario.setQuartoHorarioNoite(cont);
									}
								}
							}
							else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("M"))) {
								Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioManha().intValue() + 1);
								dadosHorario.setPrimeiroHorarioManha(cont);
							} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("M"))) {
								Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioManha().intValue() + 1);
								dadosHorario.setSegundoHorarioManha(cont);
							} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("M"))) {
								Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioManha().intValue() + 1);
								dadosHorario.setTerceiroHorarioManha(cont);
							} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("M"))) {
								Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioManha().intValue() + 1);
								dadosHorario.setQuartoHorarioManha(cont);
							} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("M"))) {
								Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioManha().intValue() + 1);
								dadosHorario.setQuintoHorarioManha(cont);
							} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("M"))) {
								Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioManha().intValue() + 1);
								dadosHorario.setSextoHorarioManha(cont);
							} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("T"))) {
								Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioTarde().intValue() + 1);
								dadosHorario.setPrimeiroHorarioTarde(cont);
							} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("T"))) {
								Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioTarde().intValue() + 1);
								dadosHorario.setSegundoHorarioTarde(cont);
							} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("T"))) {
								Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioTarde().intValue() + 1);
								dadosHorario.setTerceiroHorarioTarde(cont);
							} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("T"))) {
								Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioTarde().intValue() + 1);
								dadosHorario.setQuartoHorarioTarde(cont);
							} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("T"))) {
								Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioTarde().intValue() + 1);
								dadosHorario.setQuintoHorarioTarde(cont);
							} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("T"))) {
								Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioTarde().intValue() + 1);
								dadosHorario.setSextoHorarioTarde(cont);
							} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("N"))) {
								Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioNoite().intValue() + 1);
								dadosHorario.setPrimeiroHorarioNoite(cont);
							} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("N"))) {
								Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioNoite().intValue() + 1);
								dadosHorario.setSegundoHorarioNoite(cont);
							} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("N"))) {
								Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioNoite().intValue() + 1);
								dadosHorario.setTerceiroHorarioNoite(cont);
							} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("N"))) {
								Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioNoite().intValue() + 1);
								dadosHorario.setQuartoHorarioNoite(cont);
							}

							contador++;
						}
						contador = 0;
						for (Horario horario : this.horarioDao.getHorariosAnteriores(horarioEntrada)) {
							if (contador == this.horarioDao.getHorariosAnteriores(horarioEntrada).size() - 1) {
								if (auxiliarRelatorioHorarioSalas.compararApenasHoras(entrada.getHoraRealizacao(), horario.getHorarioIntermediario())) {
									if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioManha().intValue() + 1);
										dadosHorario.setPrimeiroHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioManha().intValue() + 1);
										dadosHorario.setSegundoHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioManha().intValue() + 1);
										dadosHorario.setTerceiroHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioManha().intValue() + 1);
										dadosHorario.setQuartoHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioManha().intValue() + 1);
										dadosHorario.setQuintoHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("M"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioManha().intValue() + 1);
										dadosHorario.setSextoHorarioManha(cont);
									} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioTarde().intValue() + 1);
										dadosHorario.setPrimeiroHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioTarde().intValue() + 1);
										dadosHorario.setSegundoHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioTarde().intValue() + 1);
										dadosHorario.setTerceiroHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioTarde().intValue() + 1);
										dadosHorario.setQuartoHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioTarde().intValue() + 1);
										dadosHorario.setQuintoHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("T"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioTarde().intValue() + 1);
										dadosHorario.setSextoHorarioTarde(cont);
									} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("N"))) {
										Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioNoite().intValue() + 1);
										dadosHorario.setPrimeiroHorarioNoite(cont);
									} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("N"))) {
										Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioNoite().intValue() + 1);
										dadosHorario.setSegundoHorarioNoite(cont);
									} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("N"))) {
										Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioNoite().intValue() + 1);
										dadosHorario.setTerceiroHorarioNoite(cont);
									} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("N"))) {
										Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioNoite().intValue() + 1);
										dadosHorario.setQuartoHorarioNoite(cont);
									}
								}
							}
							else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("M"))) {
								Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioManha().intValue() + 1);
								dadosHorario.setPrimeiroHorarioManha(cont);
							} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("M"))) {
								Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioManha().intValue() + 1);
								dadosHorario.setSegundoHorarioManha(cont);
							} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("M"))) {
								Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioManha().intValue() + 1);
								dadosHorario.setTerceiroHorarioManha(cont);
							} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("M"))) {
								Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioManha().intValue() + 1);
								dadosHorario.setQuartoHorarioManha(cont);
							} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("M"))) {
								Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioManha().intValue() + 1);
								dadosHorario.setQuintoHorarioManha(cont);
							} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("M"))) {
								Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioManha().intValue() + 1);
								dadosHorario.setSextoHorarioManha(cont);
							} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("T"))) {
								Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioTarde().intValue() + 1);
								dadosHorario.setPrimeiroHorarioTarde(cont);
							} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("T"))) {
								Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioTarde().intValue() + 1);
								dadosHorario.setSegundoHorarioTarde(cont);
							} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("T"))) {
								Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioTarde().intValue() + 1);
								dadosHorario.setTerceiroHorarioTarde(cont);
							} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("T"))) {
								Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioTarde().intValue() + 1);
								dadosHorario.setQuartoHorarioTarde(cont);
							} else if ((horario.getHorario().intValue() == 5) && (horario.getTurno().equals("T"))) {
								Integer cont = Integer.valueOf(dadosHorario.getQuintoHorarioTarde().intValue() + 1);
								dadosHorario.setQuintoHorarioTarde(cont);
							} else if ((horario.getHorario().intValue() == 6) && (horario.getTurno().equals("T"))) {
								Integer cont = Integer.valueOf(dadosHorario.getSextoHorarioTarde().intValue() + 1);
								dadosHorario.setSextoHorarioTarde(cont);
							} else if ((horario.getHorario().intValue() == 1) && (horario.getTurno().equals("N"))) {
								Integer cont = Integer.valueOf(dadosHorario.getPrimeiroHorarioNoite().intValue() + 1);
								dadosHorario.setPrimeiroHorarioNoite(cont);
							} else if ((horario.getHorario().intValue() == 2) && (horario.getTurno().equals("N"))) {
								Integer cont = Integer.valueOf(dadosHorario.getSegundoHorarioNoite().intValue() + 1);
								dadosHorario.setSegundoHorarioNoite(cont);
							} else if ((horario.getHorario().intValue() == 3) && (horario.getTurno().equals("N"))) {
								Integer cont = Integer.valueOf(dadosHorario.getTerceiroHorarioNoite().intValue() + 1);
								dadosHorario.setTerceiroHorarioNoite(cont);
							} else if ((horario.getHorario().intValue() == 4) && (horario.getTurno().equals("N"))) {
								Integer cont = Integer.valueOf(dadosHorario.getQuartoHorarioNoite().intValue() + 1);
								dadosHorario.setQuartoHorarioNoite(cont);
							}
							
							contador++;
						}
					}
				}
			}
			dadosHorarios.add(dadosHorario);
		}
		DateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy");
		dadosHorarioSala.setPeriodo(" " + formatadorData.format(solicitacao.getDataInicial()) + " a " + formatadorData.format(solicitacao.getDataFinal()));
		dadosHorarioSala.setDadosHorarios(dadosHorarios);
		dadosHorariosSalas.add(dadosHorarioSala);
		
		return new RespostaRelatorioHorarioSalas(dadosHorariosSalas);
	}
	
	public void valida(RelatorioHorarioSalas solicitacao) throws NegocioException {
		if (solicitacao.getDataInicial() == null) {
			throw new NegocioException(ErrorCode.DATA_INICIAL_VAZIO);
		}
		if (solicitacao.getDataFinal() == null) {
			throw new NegocioException(ErrorCode.DATA_FINAL_VAZIO);
		}
      if (solicitacao.getIdPredio() == 0 || solicitacao.getIdPredio() == null) {
         throw new NegocioException(ErrorCode.ID_PREDIO);
      }
		if (solicitacao.getDataFinal().before(solicitacao.getDataInicial())) {
			throw new NegocioException(ErrorCode.DATA_FINAL_INVALIDA);
		}
	}

	public void setSalaDao(SalaDao salaDao){
		this.salaDao = salaDao;
	}
	
	public void setChaveDao(ChaveDao chaveDao) {
		this.chaveDao = chaveDao;
	}
	
	public void setHistoricoChaveDao(HistoricoChaveDao historicoChaveDao) {
		this.historicoChaveDao = historicoChaveDao;
	}
	
	public void setHorarioDao(HorarioDao horarioDao) {
		this.horarioDao = horarioDao;
	}
	
   public void setParametrosRelatoriosDao(ParametrosRelatoriosDao parametrosRelatoriosDao) {
      this.parametrosRelatoriosDao = parametrosRelatoriosDao;
   }

   public void setPeriodoDao(PeriodoDao periodoDao) {
      this.periodoDao = periodoDao;
   }

}