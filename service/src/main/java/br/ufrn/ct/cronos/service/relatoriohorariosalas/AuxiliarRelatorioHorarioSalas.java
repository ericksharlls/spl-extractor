/*     */ package br.ufrn.ct.cronos.service.relatoriohorariosalas;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ 
/*     */ public class AuxiliarRelatorioHorarioSalas
/*     */ {
/*     */   public boolean saoDoMesmoDia(Date horaRealizacaoEntrada, Date horaRealizacaoSaida)
/*     */   {
/*  14 */     if ((horaRealizacaoEntrada.getDate() == horaRealizacaoSaida.getDate()) && (horaRealizacaoEntrada.getMonth() == horaRealizacaoSaida.getMonth())) {
/*  15 */       return true;
/*     */     }
/*  17 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean compararApenasHoras(Date horaUm, Date horaDois) {
/*  21 */     if (horaUm.getHours() > horaDois.getHours())
/*  22 */       return true;
/*  23 */     if (horaUm.getHours() < horaDois.getHours())
/*  24 */       return false;
/*  25 */     if (horaUm.getMinutes() > horaDois.getMinutes()) {
/*  26 */       return true;
/*     */     }
/*  28 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isDataIntermediaria(Date dataInicial, Date dataFinal, Date dataIntermediaria) {
/*  32 */     if ((dataIntermediaria.after(dataInicial)) && (dataIntermediaria.before(dataFinal))) {
/*  33 */       return true;
/*     */     }
/*  35 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean isIntervaloMaiorQueDezMinutos(Date horaInicial, Date horaFinal) {
/*  39 */     if (horaFinal.getHours() == horaInicial.getHours()) {
/*  40 */       if (horaFinal.getMinutes() - horaInicial.getMinutes() > 10)
/*  41 */         return true;
/*     */     } else {
/*  43 */       if (horaFinal.getHours() - horaInicial.getHours() > 1) {
/*  44 */         return true;
/*     */       }
/*     */ 
/*  47 */       int temp = 60 - horaInicial.getMinutes();
/*  48 */       temp += horaFinal.getMinutes();
/*  49 */       if (temp > 10) {
/*  50 */         return true;
/*     */       }
/*     */     }
/*  53 */     return false;
/*     */   }
/*     */ 
/*     */   public List<Date> retornaDatasDaTurmaPorHorario(Date dataInicio, Date dataFim, String[] diasDaSemana) {
/*  57 */     List retorno = new ArrayList(0);
/*  58 */     if (dataInicio.getMonth() == dataFim.getMonth()) {
/*  59 */       int dataInicial = dataInicio.getDate();
/*  60 */       int dataFinal = dataFim.getDate();
/*  61 */       int mes = dataInicio.getMonth();
/*  62 */       int ano = dataInicio.getYear();
/*  63 */       for (int j = dataInicial; j <= dataFinal; j++) {
/*  64 */         Date date = new Date();
/*  65 */         date.setDate(j);
/*  66 */         date.setMonth(mes);
/*  67 */         date.setYear(ano);
/*  68 */         for (int i = 0; i < diasDaSemana.length; i++) {
/*  69 */           if (date.getDay() + 1 == Integer.parseInt(diasDaSemana[i]))
/*  70 */             retorno.add(date);
/*     */         }
/*     */       }
/*     */     }
/*  74 */     else if (dataInicio.getYear() == dataFim.getYear()) {
/*  75 */       int dataInicial = dataInicio.getDate();
/*  76 */       int dataFinal = dataFim.getDate();
/*  77 */       int mesInicial = dataInicio.getMonth();
/*  78 */       int mesFinal = dataFim.getMonth();
/*  79 */       int ano = dataInicio.getYear();
/*  80 */       for (int k = mesInicial; k <= mesFinal; k++) {
/*  81 */         if (k == mesInicial) {
/*  82 */           for (int j = dataInicial; j <= retornaQuantidadeDiasPorMes(k); j++) {
/*  83 */             Date date = new Date();
/*  84 */             date.setDate(j);
/*  85 */             date.setMonth(k);
/*  86 */             date.setYear(ano);
/*  87 */             for (int i = 0; i < diasDaSemana.length; i++) {
/*  88 */               if (date.getDay() + 1 == Integer.parseInt(diasDaSemana[i]))
/*  89 */                 retorno.add(date);
/*     */             }
/*     */           }
/*     */         }
/*  93 */         else if (k == mesFinal) {
/*  94 */           for (int j = 1; j <= dataFinal; j++) {
/*  95 */             Date date = new Date();
/*  96 */             date.setDate(j);
/*  97 */             date.setMonth(k);
/*  98 */             date.setYear(ano);
/*  99 */             for (int i = 0; i < diasDaSemana.length; i++) {
/* 100 */               if (date.getDay() + 1 == Integer.parseInt(diasDaSemana[i]))
/* 101 */                 retorno.add(date);
/*     */             }
/*     */           }
/*     */         }
/*     */         else {
/* 106 */           for (int j = 1; j <= retornaQuantidadeDiasPorMes(k); j++) {
/* 107 */             Date date = new Date();
/* 108 */             date.setDate(j);
/* 109 */             date.setMonth(k);
/* 110 */             date.setYear(ano);
/* 111 */             for (int i = 0; i < diasDaSemana.length; i++)
/* 112 */               if (date.getDay() + 1 == Integer.parseInt(diasDaSemana[i]))
/* 113 */                 retorno.add(date);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 120 */       int dataInicial = dataInicio.getDate();
/* 121 */       int dataFinal = dataFim.getDate();
/* 122 */       int mesInicial = dataInicio.getMonth();
/* 123 */       int mesFinal = dataFim.getMonth();
/* 124 */       int anoInicial = dataInicio.getYear();
/* 125 */       int anoFinal = dataFim.getYear();
/* 126 */       for (int k = anoInicial; k <= anoFinal; k++) {
/* 127 */         if (k == anoInicial) {
/* 128 */           for (int j = mesInicial; j <= 11; j++) {
/* 129 */             if (k == mesInicial) {
/* 130 */               for (int l = dataInicial; l <= retornaQuantidadeDiasPorMes(k); l++) {
/* 131 */                 Date date = new Date();
/* 132 */                 date.setDate(l);
/* 133 */                 date.setMonth(j);
/* 134 */                 date.setYear(k);
/* 135 */                 for (int i = 0; i < diasDaSemana.length; i++) {
/* 136 */                   if (date.getDay() + 1 == Integer.parseInt(diasDaSemana[i]))
/* 137 */                     retorno.add(date);
/*     */                 }
/*     */               }
/*     */             }
/*     */             else {
/* 142 */               for (int z = 1; z <= retornaQuantidadeDiasPorMes(j); z++) {
/* 143 */                 Date date = new Date();
/* 144 */                 date.setDate(z);
/* 145 */                 date.setMonth(j);
/* 146 */                 date.setYear(k);
/* 147 */                 for (int i = 0; i < diasDaSemana.length; i++) {
/* 148 */                   if (date.getDay() + 1 == Integer.parseInt(diasDaSemana[i]))
/* 149 */                     retorno.add(date);
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/* 155 */         else if (k == anoFinal) {
/* 156 */           for (int i = 0; i <= mesFinal; i++) {
/* 157 */             if (i == mesFinal) {
/* 158 */               for (int j = 1; j <= dataFinal; j++) {
/* 159 */                 Date date = new Date();
/* 160 */                 date.setDate(j);
/* 161 */                 date.setMonth(i);
/* 162 */                 date.setYear(k);
/* 163 */                 for (int z = 0; z < diasDaSemana.length; z++) {
/* 164 */                   if (date.getDay() + 1 == Integer.parseInt(diasDaSemana[z]))
/* 165 */                     retorno.add(date);
/*     */                 }
/*     */               }
/*     */             }
/*     */             else {
/* 170 */               for (int z = 1; z <= retornaQuantidadeDiasPorMes(i); z++) {
/* 171 */                 Date date = new Date();
/* 172 */                 date.setDate(z);
/* 173 */                 date.setMonth(i);
/* 174 */                 date.setYear(k);
/* 175 */                 for (int l = 0; l < diasDaSemana.length; l++) {
/* 176 */                   if (date.getDay() + 1 == Integer.parseInt(diasDaSemana[l])) {
/* 177 */                     retorno.add(date);
/*     */                   }
/*     */                 }
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 187 */     return retorno;
/*     */   }
/*     */ 
/*     */   public String retornaStringDiaDaSemana(Date data) {
/* 191 */     String retorno = null;
/* 192 */     switch (data.getDay()) {
/*     */     case 1:
/* 194 */       retorno = "Segunda-Feira";
/* 195 */       break;
/*     */     case 2:
/* 197 */       retorno = "Terça-Feira";
/* 198 */       break;
/*     */     case 3:
/* 200 */       retorno = "Quarta-Feira";
/* 201 */       break;
/*     */     case 4:
/* 203 */       retorno = "Quinta-Feira";
/* 204 */       break;
/*     */     case 5:
/* 206 */       retorno = "Sexta-Feira";
/* 207 */       break;
/*     */     case 6:
/* 209 */       retorno = "Sábado";
/*     */     }
/*     */ 
/* 212 */     return retorno;
/*     */   }
/*     */ 
/*     */   public int retornaQuantidadeDiasPorMes(int mes) {
/* 216 */     int retorno = 0;
/* 217 */     switch (mes) {
/*     */     case 0:
/* 219 */       retorno = 31;
/* 220 */       break;
/*     */     case 1:
/* 222 */       retorno = 28;
/* 223 */       break;
/*     */     case 2:
/* 225 */       retorno = 31;
/* 226 */       break;
/*     */     case 3:
/* 228 */       retorno = 30;
/* 229 */       break;
/*     */     case 4:
/* 231 */       retorno = 31;
/* 232 */       break;
/*     */     case 5:
/* 234 */       retorno = 30;
/* 235 */       break;
/*     */     case 6:
/* 237 */       retorno = 31;
/* 238 */       break;
/*     */     case 7:
/* 240 */       retorno = 31;
/* 241 */       break;
/*     */     case 8:
/* 243 */       retorno = 30;
/* 244 */       break;
/*     */     case 9:
/* 246 */       retorno = 31;
/* 247 */       break;
/*     */     case 10:
/* 249 */       retorno = 30;
/* 250 */       break;
/*     */     case 11:
/* 252 */       retorno = 31;
/*     */     }
/*     */ 
/* 255 */     return retorno;
/*     */   }
/*     */ }

/* Location:           C:\Users\Erick\Documents\componentesCronosAtualizados\cronos-service-0.0.1.0-SNAPSHOT\
 * Qualified Name:     br.ufrn.ct.cronos.service.relatoriohorariosalas.AuxiliarRelatorioHorarioSalas
 * JD-Core Version:    0.6.2
 */