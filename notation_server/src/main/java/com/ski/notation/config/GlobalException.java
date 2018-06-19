package com.ski.notation.config;

public class GlobalException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3819980925419133920L;
	private int errorCode;
	public GlobalException(){
		super();
	}
	public GlobalException(String message){
		super(message);
	}
	public GlobalException(String message ,int errorCode){
		super(message);
		this.setErrorCode(errorCode);
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}
