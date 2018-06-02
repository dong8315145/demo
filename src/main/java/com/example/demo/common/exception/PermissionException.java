package com.example.demo.common.exception;

/**
 * dal层抛出
 * 
 */
public class PermissionException extends Exception {

	private static final long serialVersionUID = 1L;
	private int errorCode;

	public PermissionException() {
	}

	public PermissionException(String msg) {
		super(msg);
	}

	public PermissionException(Throwable cause) {
		super(cause);
	}

	public PermissionException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public PermissionException(int code, String msg) {
		super(msg);
		this.errorCode = code;
	}

	public PermissionException(int code, String msg, Throwable cause) {
		super(code + ":" + msg, cause);
		this.errorCode = code;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
