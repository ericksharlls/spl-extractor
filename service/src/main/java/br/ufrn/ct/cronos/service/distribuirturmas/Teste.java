package br.ufrn.ct.cronos.service.distribuirturmas;

import java.util.ArrayList;
import java.util.List;
import br.ufrn.ct.cronos.entity.Turma;

public class Teste {

   public static void main(String[] args) {
      Teste2 teste2 = new Teste2();
      Turma turma1 = new Turma();
      Turma turma2 = new Turma();
      Turma turma3 = new Turma();
      Turma turma4 = new Turma();

      turma1.setHorario("35T34");
      turma2.setHorario("35T12");
      turma3.setHorario("35N34");
      turma4.setHorario("35M56");

      List<Turma> turmasLegais = new ArrayList<Turma>();

      turmasLegais.add(turma1);
      turmasLegais.add(turma2);
      turmasLegais.add(turma3);
      turmasLegais.add(turma4);

      for (Turma t : turmasLegais) {
         List<Turma> consecutivos = new ArrayList<Turma>();
         System.out.println("Turma:" + t.getHorario() + " Indice:" + turmasLegais.indexOf(t));
         for (int i = 0; i < turmasLegais.size(); i++) {
            if (i != turmasLegais.size() - 1) {
               if (teste2.saoConsecutivos(t, (Turma) turmasLegais.get(i))) {
                  if (!consecutivos.contains(turmasLegais.get(i))) {
                     consecutivos.add(turmasLegais.get(i));
                  }
                  consecutivos.add(turmasLegais.get(i));
               }
            }
         }

         for (Turma turma : consecutivos) {
            System.out.println(turma.getHorario());
         }
      }
   }

}
