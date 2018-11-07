
package br.ufrn.ct.cronos.dao.impl;

import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Auxiliar {

   private static final String REGEX_HORARIO = "[2-6]+[MTN][1-6]+";
   private static final String REGEX_HORARIO_SABADO = "[2-7]+[MTN][1-6]+";
   private static final String REGEX_HORARIO_DOMINGO = "[1-7]+[MTN][1-6]+";
   private static final String REGEX_HORARIO_VALIDACAO = "[\\s]*[2-6]+[MTN][1-6]+[\\s]+[2-6]+[MTN][1-6]+[\\s]*";
   private static final String REGEX_HORARIO_SABADO_VALIDACAO = "[\\s]*[2-7]+[MTN][1-6]+[\\s]*([\\s]+[2-7]+[MTN][1-6]+[\\s]*)*";
   private static final String REGEX_HORARIO_DOMINGO_VALIDACAO = "[\\s]*[1-7]+[MTN][1-6]+[\\s]*([\\s]+[1-7]+[MTN][1-6]+[\\s]*)*";

   public String[] retornaArrayDias(String horarioTurma) {
      horarioTurma = horarioTurma.trim();
      StringTokenizer tokenizer = new StringTokenizer(horarioTurma);
      String stringDias = tokenizer.nextToken("[MTN]");
      String[] arrayDias = new String[stringDias.length()];
      for (int i = 0; i < stringDias.length(); i++) {
         arrayDias[i] = stringDias.substring(i, i + 1);
      }
      return arrayDias;
   }

   public String retornaTurno(String horarioTurma) {
      horarioTurma = horarioTurma.trim();
      String turno = null;
      String[] temporario = horarioTurma.split("\\d");
      for (int i = 0; i < temporario.length; i++) {
         if (!temporario[i].equals("")) {
            turno = temporario[i];
         }
      }
      return turno;
   }

   public String[] retornaArrayHorarios(String horarioTurma) {
      horarioTurma = horarioTurma.trim();
      StringTokenizer tokenizer = new StringTokenizer(horarioTurma);
      tokenizer.nextToken("[MTN]");
      String stringHorarios = tokenizer.nextToken("[MTN]");
      String[] arrayHorarios = new String[stringHorarios.length()];
      for (int i = 0; i < stringHorarios.length(); i++) {
         arrayHorarios[i] = stringHorarios.substring(i, i + 1);
      }
      return arrayHorarios;
   }

   public boolean validarHorarioComSabado(String horarioTurma) {
      Pattern padrao = Pattern.compile(REGEX_HORARIO_SABADO_VALIDACAO);
      Matcher pesquisa = padrao.matcher(horarioTurma);
      if (pesquisa.matches()) {
         return true;
      }
      return false;
   }
   
   public boolean validarHorarioComDomingo(String horarioTurma) {
	      Pattern padrao = Pattern.compile(REGEX_HORARIO_DOMINGO_VALIDACAO);
	      Matcher pesquisa = padrao.matcher(horarioTurma);
	      if (pesquisa.matches()) {
	         return true;
	      }
	      return false;
	   }

   public int contadorDeGrupos(String horarioTurma) {
      Pattern p = Pattern.compile(REGEX_HORARIO);
      Matcher m = p.matcher(horarioTurma);
      int count = 0;
      while (m.find()) {
         count++;
      }
      return count;
   }

   public int contadorDeGruposComSabado(String horarioTurma) {
      Pattern p = Pattern.compile(REGEX_HORARIO_SABADO);
      Matcher m = p.matcher(horarioTurma);
      int count = 0;
      while (m.find()) {
         count++;
      }
      return count;
   }
   
   public int contadorDeGruposComDomingo(String horarioTurma) {
	      Pattern p = Pattern.compile(REGEX_HORARIO_DOMINGO);
	      Matcher m = p.matcher(horarioTurma);
	      int count = 0;
	      while (m.find()) {
	         count++;
	      }
	      return count;
	   }

   public String retornaGrupo(String horarioTurma, int indice) {
      Pattern p = Pattern.compile(REGEX_HORARIO);
      Matcher m = p.matcher(horarioTurma);
      int count = 0;
      while (m.find()) {
         if (count == indice) {
            return m.group();
         }
         count++;
      }
      return null;
   }

   public String retornaGrupoComSabado(String horarioTurma, int indice) {
      Pattern p = Pattern.compile(Auxiliar.REGEX_HORARIO_SABADO);
      Matcher m = p.matcher(horarioTurma);
      int count = 0;
      while (m.find()) {
         if (count == indice) {
            return m.group();
         }
         count++;
      }
      return null;
   }
   
   public String retornaGrupoComDomingo(String horarioTurma, int indice) {
	      Pattern p = Pattern.compile(Auxiliar.REGEX_HORARIO_DOMINGO);
	      Matcher m = p.matcher(horarioTurma);
	      int count = 0;
	      while (m.find()) {
	         if (count == indice) {
	            return m.group();
	         }
	         count++;
	      }
	      return null;
	   }

   public boolean validar(String horarioTurma) {
      Pattern padrao = Pattern.compile(REGEX_HORARIO_VALIDACAO);
      Matcher pesquisa = padrao.matcher(horarioTurma);
      if (pesquisa.matches()) {
         return true;
      }
      return false;
   }
}
