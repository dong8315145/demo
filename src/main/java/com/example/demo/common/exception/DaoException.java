package com.example.demo.common.exception;

/**
 * dal层抛出
 * 
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;
	private int errorCode;

	public DaoException() {
	}

	public DaoException(String msg) {
		super(msg);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}

	public DaoException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DaoException(int code, String msg) {
		super(msg);
		this.errorCode = code;
	}

	public DaoException(int code, String msg, Throwable cause) {
		super(code + ":" + msg, cause);
		this.errorCode = code;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
