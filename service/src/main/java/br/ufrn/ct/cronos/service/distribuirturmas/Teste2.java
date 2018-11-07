package br.ufrn.ct.cronos.service.distribuirturmas;

import br.ufrn.ct.cronos.distribuirturmas.vo.Auxiliar;
import br.ufrn.ct.cronos.entity.Turma;

public class Teste2 {

   public boolean saoConsecutivos(Turma turma1, Turma turma2) {
      Auxiliar auxiliar = new Auxiliar();
      int contadorDeT = auxiliar.contadorDeGrupos(turma2.getHorario());

      if (contadorDeT == auxiliar.contadorDeGrupos(turma1.getHorario())) {
         for (int h = 0; h < auxiliar.contadorDeGrupos(turma1.getHorario()); h++) {
            String grupo = auxiliar.retornaGrupo(turma1.getHorario(), h);
            String[] arrayDias = auxiliar.retornaArrayDias(grupo);
            String turno = auxiliar.retornaTurno(grupo);
            String[] arrayHorarios = auxiliar.retornaArrayHorarios(grupo);

            String grupoParametro = auxiliar.retornaGrupo(turma2.getHorario(), h);
            String[] arrayDiasParametro = auxiliar.retornaArrayDias(grupoParametro);
            String turnoParametro = auxiliar.retornaTurno(grupoParametro);
            String[] arrayHorariosParametro = auxiliar.retornaArrayHorarios(grupoParametro);

            if (diasIguais(arrayDias, arrayDiasParametro)) {
               if (turno.equals(turnoParametro)) {
                  if (!horarioConsecutivosTurnosIguais(arrayHorarios, arrayHorariosParametro)) {
                     return false;
                  }
               } else {
                  if ((turno.equals("M")) && (turnoParametro.equals("N"))) {
                     return false;
                  }
                  if (!horarioConsecutivosTurnosDiferentes(arrayHorarios, arrayHorariosParametro)) {
                     return false;
                  }
               }
            }
            else {
               return false;
            }
         }
      }
      else {
         return false;
      }

      return true;
   }

   public boolean diasIguais(String[] array1, String[] array2) {
      if (array1.length == array2.length) {
         for (int i = 0; i < array1.length; i++) {
            if (!array1[i].equals(array2[i]))
               return false;
         }
      }
      else {
         return false;
      }
      return true;
   }

   public boolean horarioConsecutivosTurnosIguais(String[] array1, String[] array2) {
      for (int i = 0; i < array1.length; i++) {
         if ((i == array1.length - 1) && (Integer.parseInt(array1[i]) == Integer.parseInt(array2[0]) - 1)) {
            return true;
         }
      }

      return false;
   }

   public boolean horarioConsecutivosTurnosDiferentes(String[] array1, String[] array2) {
      for (int i = 0; i < array1.length; i++) {
         if ((i == array1.length - 1) && (Integer.parseInt(array1[i]) == 6) && (Integer.parseInt(array2[0]) == 1)) {
            return true;
         }
      }

      return false;
   }

}
