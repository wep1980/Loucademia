package br.com.wepdev.loucademia.application.utils;


/*
 * Classe de metodos utilitarios que pode ser usado em qualquer PROJETO
 */
public class Validation {
	
	
	/**
	 * Metodo que recebe um objeto e garante que ele n�o esteja vazio (valida��o) de obrigatoriedade de valida��o
	 * static -> metodo que pode ser chamado diretamente em qualquer classe sem a necessidade da cria��o de um objeto
	 */
	public static void assertNotEmpty(Object obj) {
		if(obj instanceof String) { // Se o objeto for do tipo String
			String s = (String) obj; // Transformando o obj em String
		    if(StringUtils.isEmpty(s)) { // Verifica se esta vazio
		    	throw new ValidationException("O texto n�o pode ser nulo");
		    }
		}
		if(obj == null) {
			throw new ValidationException("Valor n�o pode ser nulo"); // Lan�a uma nova excess�o para fora(Para quem chamou) -- (O Try catch trata essa excess�o lan�ada)
		}
	}

}
