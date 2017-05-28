package com.psquickit.common;

import java.io.Serializable;

import com.google.common.annotations.VisibleForTesting;

public class DuplicateUserException extends Exception implements Serializable {

	private static final long serialVersionUID = 8721510979267423761L;
	private String code;
	/**
	 * Default constructor, meant for use in testing only.
	 */
	@VisibleForTesting
	public DuplicateUserException() {
		super("Recipe not found");
	}

	/**
	 * Constructor for DuplicateUserException
	 * 
	 * @param code
	 *            Represents the Code
	 * @param message
	 *            Represents the Message
	 * @param cause
	 *            Represents the Cause
	 */
	public DuplicateUserException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor for DuplicateUserException
	 * 
	 * @param message
	 *            Represents the Message
	 */
	public DuplicateUserException(String message) {
		super(message);
	}
	
	/**
	 * Constructor for DuplicateUserException
	 * 
	 * @param code
	 *            Represents the Code
	 * @param message
	 *            Represents the Message
	 */
	public DuplicateUserException(String code, String message) {
		super(message);
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
