package br.com.wepdev.loucademia.application.utils;

/*
 * Classe de metodos utilitarios que pode ser usado em qualquer PROJETO
 */
public class StringUtils {
	

	/**
	 * static -> metodo que pode ser chamado diretamente em qualquer classe sem a necessidade da criação de um objeto
	 * Metodo que verifica se a String esta vazia
	 * trim() -> Retira os espaços vazios
	 */
	public static boolean isEmpty(String s) {
		if(s == null) {
			return true;
		}
		return s.trim().length() == 0;
	}
	
	/**
	 * Metodo que coloca zeros a esquerda
	 * @param valor
	 * @param finalTamanho
	 * @return
	 */
	public static String colocaZerosAEsquerda(int valor, int finalTamanho) {
		return String.format("%0" + finalTamanho + "d", valor);
	}
	
	// Testando os metodos acima
	/*public static void main(String[] args) {
		
		String str = null;
		String strr = "abc";
		
		boolean b = StringUtils.isEmpty(str);
		System.out.println(b);
		
		//*****************************************************************
		
		int v = 12345;
		System.out.println(StringUtils.colocaZerosAEsquerda(v, 8));
		
		
	}*/
}
