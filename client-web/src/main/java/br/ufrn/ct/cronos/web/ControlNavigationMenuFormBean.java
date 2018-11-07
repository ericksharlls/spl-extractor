
package br.ufrn.ct.cronos.web;

import dev.home.componente.web.infra.AbstractFormBean;
import dev.home.componente.web.infra.util.FacesUtil;

public class ControlNavigationMenuFormBean extends AbstractFormBean {

   private static final long serialVersionUID = 1L;
   private boolean menuSalaSelecionado;
   private boolean menuTurmaSelecionado;
   private boolean menuRelatoriosSelecionado;
   private boolean menuDistribuicaoSelecionado;
   private boolean menuAgendamentoSelecionado;

   // private boolean menuRelatorioUtilizacaoHorariosSalas;
   // private boolean menuRelatorioUtilizacaoHorariosPredio;

   public ControlNavigationMenuFormBean() {
      // this.menuRelatorioUtilizacaoHorariosSalas =
      // Boolean.parseBoolean(PropertiesLoader.propertiesLoader().getProperty("TemRelatorioUtilizacaoHorariosSalas"));
      // this.menuRelatorioUtilizacaoHorariosPredio =
      // Boolean.parseBoolean(PropertiesLoader.propertiesLoader().getProperty("TemRelatorioUtilizacaoHorariosPredio"));
      this.menuSalaSelecionado = true;
      this.menuTurmaSelecionado = true;
      this.menuRelatoriosSelecionado = true;
      this.menuDistribuicaoSelecionado = true;
      this.menuAgendamentoSelecionado = true;
   }

   public String cadastrarSala() {
      this.menuSalaSelecionado = true;
      this.menuTurmaSelecionado = false;
      this.menuRelatoriosSelecionado = false;
      this.menuDistribuicaoSelecionado = false;
      this.menuAgendamentoSelecionado = false;
      return FacesUtil.redirect("/admin/sala/novo");
   }

   public String cadastrarTurma() {
      this.menuTurmaSelecionado = true;
      this.menuSalaSelecionado = false;
      this.menuRelatoriosSelecionado = false;
      this.menuDistribuicaoSelecionado = false;
      this.menuAgendamentoSelecionado = false;
      return FacesUtil.redirect("/admin/turma/novo");
   }

   public String consultarSala() {
      this.menuSalaSelecionado = true;
      this.menuTurmaSelecionado = false;
      this.menuRelatoriosSelecionado = false;
      this.menuDistribuicaoSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/admin/sala/listar");
   }

   public String consultarTurma() {
      this.menuTurmaSelecionado = true;
      this.menuSalaSelecionado = false;
      this.menuRelatoriosSelecionado = false;
      this.menuDistribuicaoSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/admin/turma/listar");
   }

   public String pesquisarTurma() {
      this.menuTurmaSelecionado = true;
      this.menuSalaSelecionado = false;
      this.menuRelatoriosSelecionado = false;
      this.menuDistribuicaoSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/usuario/turma/pesquisar");
   }

   public String relatorioSala() {
      this.menuRelatoriosSelecionado = true;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;
      this.menuDistribuicaoSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/admin/relatorio/sala");
   }

   public String relatorioSalasCheias() {
      this.menuRelatoriosSelecionado = true;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;
      this.menuDistribuicaoSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/admin/relatorio/salasCheias");
   }

   public String relatorioTurmasNaoDistribuidas() {
      this.menuRelatoriosSelecionado = true;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;
      this.menuDistribuicaoSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/admin/relatorio/turmasNaoDistribuidas");
   }

   public String relatorioDepartamentos() {
      this.menuRelatoriosSelecionado = true;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;
      this.menuDistribuicaoSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/admin/relatorio/departamento");
   }

   public String relatorioOcupacaoSalas() {
      this.menuRelatoriosSelecionado = true;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;
      this.menuDistribuicaoSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/direcao/relatorio/ocupacaoSalas");
   }

   public String relatorioOcupacaoTurmas() {
      this.menuRelatoriosSelecionado = true;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;
      this.menuDistribuicaoSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/direcao/relatorio/ocupacaoTurmas");
   }

   public String distribuirTurmas() {
      this.menuDistribuicaoSelecionado = true;
      this.menuRelatoriosSelecionado = false;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/admin/distribuicao/distribuicao");
   }

   public String permutarTurmas() {
      this.menuDistribuicaoSelecionado = true;
      this.menuRelatoriosSelecionado = false;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/admin/distribuicao/permutar");
   }

   public String alocarTurmas() {
      this.menuDistribuicaoSelecionado = true;
      this.menuRelatoriosSelecionado = false;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/admin/distribuicao/alocar");
   }

   public String cadastrarAgendamento() {
      this.menuAgendamentoSelecionado = true;
      this.menuDistribuicaoSelecionado = false;
      this.menuRelatoriosSelecionado = false;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;

      return FacesUtil.redirect("/admin/agendamento/consultar");
   }

   public String cadastrarAgendamentoParaDepartamento() {
      this.menuAgendamentoSelecionado = true;
      this.menuDistribuicaoSelecionado = false;
      this.menuRelatoriosSelecionado = false;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;

      return FacesUtil.redirect("/departamento/agendamento/consultar");
   }

   public String visualizarAgendamentosParaUsuario() {
      this.menuAgendamentoSelecionado = true;
      this.menuDistribuicaoSelecionado = false;
      this.menuRelatoriosSelecionado = false;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;

      return FacesUtil.redirect("/usuario/agendamento/visualizar");
   }

   public String visualizarAgendamentosParaAdministrador() {
      this.menuAgendamentoSelecionado = true;
      this.menuDistribuicaoSelecionado = false;
      this.menuRelatoriosSelecionado = false;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;

      return FacesUtil.redirect("/admin/agendamento/visualizar");
   }

   public String pesquisarAgendamentos() {
      this.menuAgendamentoSelecionado = true;
      this.menuDistribuicaoSelecionado = false;
      this.menuRelatoriosSelecionado = false;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;

      return FacesUtil.redirect("/usuario/agendamento/pesquisar");
   }

   public String registrarEntradaSaida() {
      this.menuSalaSelecionado = true;
      this.menuTurmaSelecionado = false;
      this.menuRelatoriosSelecionado = false;
      this.menuDistribuicaoSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/supervisao/sala/registrarEntradaSaida");
   }

   public String desalocarTurma() {
      this.menuDistribuicaoSelecionado = true;
      this.menuRelatoriosSelecionado = false;
      this.menuTurmaSelecionado = false;
      this.menuSalaSelecionado = false;
      this.menuAgendamentoSelecionado = false;

      return FacesUtil.redirect("/admin/distribuicao/desalocar");
   }

   public boolean isMenuSalaSelecionado() {
      return this.menuSalaSelecionado;
   }

   public void setMenuSalaSelecionado(boolean menuSalaSelecionado) {
      this.menuSalaSelecionado = menuSalaSelecionado;
   }

   public boolean isMenuTurmaSelecionado() {
      return this.menuTurmaSelecionado;
   }

   public void setMenuTurmaSelecionado(boolean menuTurmaSelecionado) {
      this.menuTurmaSelecionado = menuTurmaSelecionado;
   }

   public boolean isMenuRelatoriosSelecionado() {
      return this.menuRelatoriosSelecionado;
   }

   public void setMenuRelatoriosSelecionado(boolean menuRelatoriosSelecionado) {
      this.menuRelatoriosSelecionado = menuRelatoriosSelecionado;
   }

   public boolean isMenuDistribuicaoSelecionado() {
      return this.menuDistribuicaoSelecionado;
   }

   public void setMenuDistribuicaoSelecionado(boolean menuDistribuicaoSelecionado) {
      this.menuDistribuicaoSelecionado = menuDistribuicaoSelecionado;
   }

   public boolean isMenuAgendamentoSelecionado() {
      return this.menuAgendamentoSelecionado;
   }

   public void setMenuAgendamentoSelecionado(boolean menuAgendamentoSelecionado) {
      this.menuAgendamentoSelecionado = menuAgendamentoSelecionado;
   }

}
