package br.com.wepdev.loucademia.application.utils;


/*
 * Classe de metodos utilitarios que pode ser usado em qualquer PROJETO
 */
public class Validation {
	
	
	/**
	 * Metodo que recebe um objeto e garante que ele não esteja vazio (validação) de obrigatoriedade de validação
	 * static -> metodo que pode ser chamado diretamente em qualquer classe sem a necessidade da criação de um objeto
	 */
	public static void assertNotEmpty(Object obj) {
		if(obj instanceof String) { // Se o objeto for do tipo String
			String s = (String) obj; // Transformando o obj em String
		    if(StringUtils.isEmpty(s)) { // Verifica se esta vazio
		    	throw new ValidationException("O texto não pode ser nulo");
		    }
		}
		if(obj == null) {
			throw new ValidationException("Valor não pode ser nulo"); // Lança uma nova excessão para fora(Para quem chamou) -- (O Try catch trata essa excessão lançada)
		}
	}

}
