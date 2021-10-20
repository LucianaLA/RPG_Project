package com.qa.rpg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No character with that id found")
public class CharacterNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3888718293346211966L;

	public CharacterNotFoundException() {
		super();
	}

	public CharacterNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CharacterNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CharacterNotFoundException(String message) {
		super(message);
	}

	public CharacterNotFoundException(Throwable cause) {
		super(cause);
	}
	
	
}
