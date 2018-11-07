package br.ufrn.ct.cronos.service;


public class ManipuladorNomes {

   public static String abreviaNome(String nomeInteiro) {
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
      return saida;
   }

}
