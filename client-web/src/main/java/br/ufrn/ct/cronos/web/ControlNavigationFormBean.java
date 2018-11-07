package br.ufrn.ct.cronos.web;

import dev.home.componente.web.infra.util.FacesUtil;

public class ControlNavigationFormBean {

   public static String pageConsultarProblema() {
      return FacesUtil.redirect("/admin/problema/listar");
   }

   public static String pageConsultarStatus() {
      return FacesUtil.redirect("/admin/status/listar");
   }

   public static String pageConsultarUsuario() {
      return FacesUtil.redirect("/admin/usuario/listar");
   }

   public static String pageConsultarFuncionario() {
      return FacesUtil.redirect("/admin/funcionario/listar");
   }

   public static String pageConsultarProduto() {
      return FacesUtil.redirect("/admin/produto/listar");
   }

   public static String pageConsultarSala() {
      return FacesUtil.redirect("/admin/sala/listar");
   }

   public static String pageConsultarPeriodo() {
      return FacesUtil.redirect("/admin/periodo/listar");
   }

   public static String pageConsultarPredio() {
      return FacesUtil.redirect("/admin/predio/listar");
   }

   public static String pageConsultarTurma() {
      return FacesUtil.redirect("/admin/turma/listar");
   }

   public static String pageConsultarFeriado() {
      return FacesUtil.redirect("/admin/feriado/listar");
   }

   public static String pagePermutarTurmas() {
      return FacesUtil.redirect("/admin/distribuicao/permutar");
   }

   public static String pageRelatorioSalas() {
      return FacesUtil.redirect("/admin/sala/relatorio");
   }

   public static String pageAlocarTurmas() {
      return FacesUtil.redirect("/admin/distribuicao/alocar");
   }

   public static String pageObterHorarioSala() {
      return FacesUtil.redirect("/admin/agendamento/salas");
   }

}
