package br.com.wepdev.loucademia.application.utils;

import javax.ejb.ApplicationException;

@ApplicationException // Torna uma excessão da aplicação, e não encapsula ela dentro de uma excessão do EJB
public class ValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	

	public ValidationException() {
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
