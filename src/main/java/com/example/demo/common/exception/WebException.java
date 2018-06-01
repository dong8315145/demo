package com.example.demo.common.exception;

/**
 * Web Exception
 * 
 */
public class WebException extends Exception {

	private static final long serialVersionUID = 1L;
	private int errorCode;

	public WebException() {
	}

	public WebException(String msg) {
		super(msg);
	}

	public WebException(Throwable cause) {
		super(cause);
	}

	public WebException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public WebException(int code, String msg) {
		super(msg);
		this.errorCode = code;
	}

	public WebException(int code, String msg, Throwable cause) {
		super(code + ":" + msg, cause);
		this.errorCode = code;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
