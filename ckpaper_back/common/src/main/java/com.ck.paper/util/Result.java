package com.ck.paper.util;

import java.io.Serializable;

public class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer code;

	private String message;
	
	private String consoleMsg;

	private T data;
	
	public Result() {
		super();
	}

	public Result(Integer code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public String getConsoleMsg() {
		return consoleMsg;
	}

	public void setConsoleMsg(String consoleMsg) {
		this.consoleMsg = consoleMsg;
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Result [code=" + code + ", message=" + message + ", consoleMsg=" + consoleMsg + ", data=" + data
				 + "]";
	}
}
