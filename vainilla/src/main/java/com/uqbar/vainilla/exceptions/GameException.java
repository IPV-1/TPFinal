package com.uqbar.vainilla.exceptions;

public class GameException extends RuntimeException {

	private static final long serialVersionUID = 4215920172473344618L;
	
	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public GameException(String description) {
		super(description);
	}
}