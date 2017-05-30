package com.psquickit.common;

import java.io.Serializable;

public class HandledException extends Exception implements Serializable {

	private static final long serialVersionUID = -4089038983505329425L;
	
	private String code;
	
	public HandledException(String code, String message){
		super(message);
		this.setCode(code);
	}
	
	public HandledException(String code, String message, Throwable cause){
		super(message, cause);
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
