package br.ufrn.ct.cronos.service.distribuirturmas;


public class AbreviarNomesCompleto {

   public static void main(String args[]) {
      String nomeInteiro = "Erick Sharlls Ramos de Pontes da Silva";
      nomeInteiro = nomeInteiro.replace(' ', ';');
      String nomePedacos[] = nomeInteiro.split(";");
      int k;
      String saida = "";
      for (k = 0; k < nomePedacos.length; k++) {
         if (k == 0) {
            saida = saida + nomePedacos[k];
         } else if (k == (nomePedacos.length - 1)) {
            saida = saida + " " + nomePedacos[k];
         } else {
            if (nomePedacos[k].length() < 3) {
               saida = saida + " " + nomePedacos[k];
            } else {
               saida = saida + " " + nomePedacos[k].charAt(0) + ".";
            }

         }
      }
      System.out.println(saida);
   }

}
