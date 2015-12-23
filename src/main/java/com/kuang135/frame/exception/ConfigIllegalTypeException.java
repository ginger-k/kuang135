package com.kuang135.frame.exception;

public class ConfigIllegalTypeException extends Exception {

	private static final long serialVersionUID = 4589643046378494460L;

	public ConfigIllegalTypeException() {
		super();
	}

	public ConfigIllegalTypeException(String message) {
		super(message);
	}
	
	public ConfigIllegalTypeException(Throwable cause) {
		super(cause);
	}
	
	public ConfigIllegalTypeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ConfigIllegalTypeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}


}
