package br.com.usjt.partiu.web.util;

public class StringUtils {

	public static String converteInicialMaiuscula(String string) {
		String inicial = string.substring(0, 1);
		String restanteString = string.substring(1);
		return inicial.toUpperCase().concat(restanteString.toLowerCase());
	}
	
	public static String converteCamelCase(String string) {
		String[] vetor = string.split(" ");
		String retorno = "";
		for (int i = 0; i < vetor.length; i++) {
			if (vetor[i].length() > 2) {
				retorno += StringUtils.converteInicialMaiuscula(vetor[i]).concat(" ");				
			} else {
				retorno += vetor[i].toLowerCase().concat(" ");
			}
		}
		return retorno.trim();
	}
}
