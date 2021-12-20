package com.bridgelabz.lmscandidate.exception;


/**
 * purpose to handle custom exception
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 *
 */
@SuppressWarnings("serial")
public class HireCandidateException extends RuntimeException {

	public HireCandidateException(String message) {
		super (message);
	}
	
}
