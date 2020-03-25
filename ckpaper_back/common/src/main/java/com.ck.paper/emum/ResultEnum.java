package com.ck.paper.emum;

public enum ResultEnum {
	/**
	 * 异常编码分类目录
	 * 
	 * 0~10:系统异常
	 * 
	 * 
	 * 1000~1999:净值
	 */
	UNKOWN(-1,"未知异常")
	,SUCCESS(0,"")
	,INSERTNOTICESUCCESS(0,"新增启事成功")
	,DELETEBYPASSWORDSUCESS(200,"删除启事成功")
	;

	private Integer code;
	private String message;
	
	
	private ResultEnum(Integer code, String message) {
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
	
	
	
	
}
