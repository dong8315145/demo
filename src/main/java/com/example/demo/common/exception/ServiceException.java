package com.example.demo.common.exception;

/**
 * Service层抛出
 * 
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	private int errorCode;

	public ServiceException() {
	}

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ServiceException(int code, String msg) {
		super(msg);
		this.errorCode = code;
	}

	public ServiceException(int code, String msg, Throwable cause) {
		super(code + ":" + msg, cause);
		this.errorCode = code;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
