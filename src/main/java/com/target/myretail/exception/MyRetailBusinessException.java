package com.target.myretail.exception;

/**
 * Custom exception to track the business exceptions
 * 
 * @author madhavi
 *
 */
public class MyRetailBusinessException extends Exception {

	private static final long serialVersionUID = -3539896849210375663L;

	public MyRetailBusinessException(String message) {
		super(message);
	}

}
