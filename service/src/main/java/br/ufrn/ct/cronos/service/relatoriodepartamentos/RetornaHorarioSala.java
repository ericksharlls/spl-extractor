package br.ufrn.ct.cronos.service.relatoriodepartamentos;

public class RetornaHorarioSala {
	
	public RetornaHorarioSala(){
		super();
	}
	
	public static Integer retornaValor(Integer idHorarioSala) {
		switch (idHorarioSala) {
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 3;
		case 4:
			return 4;
		case 5:
			return 5;
		case 6:
			return 6;
		case 7:
			return 1;
		case 8:
			return 2;
		case 9:
			return 3;
		case 10:
			return 4;
		case 11:
			return 5;
		case 12:
			return 6;
		case 13:
			return 1;
		case 14:
			return 2;
		case 15:
			return 3;
		case 16:
			return 4;
		}
		return null;
	}

}
