package com.target.myretail.exception;

/**
 * Custom exception to track the data access layer exceptions
 * 
 * @author madhavi
 *
 */
public class MyRetailDaoException extends Exception {

	private static final long serialVersionUID = 1L;

	public MyRetailDaoException(String message) {
		super(message);
	}
}
