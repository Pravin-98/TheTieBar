package com.thetiebar.exception;

public class ProductServiceException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	public ProductServiceException(String msg,String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
