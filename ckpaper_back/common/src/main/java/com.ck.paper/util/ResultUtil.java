package com.ck.paper.util;


import com.ck.paper.emum.ResultEnum;

public class ResultUtil {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Result success(Object object) {
		Result result = new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
		result.setData(object);
		return result;
	}

	@SuppressWarnings({ "rawtypes" })
	public static Result success() {
		return success("");
	}

	@SuppressWarnings("rawtypes")
	public static Result error(ResultEnum resultEnum) {
		Result result = new Result<>(resultEnum.getCode(), resultEnum.getMessage());
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Result error(String msg) {
		Result result = new Result<>();
		result.setCode(ResultEnum.UNKOWN.getCode());
		result.setMessage(msg);
		return result;
	}


	@SuppressWarnings("rawtypes")
	public static Result error(Integer code, String message) {
		Result result = new Result<>(code, message);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Result error(Integer code, String message,Object object) {
		Result result = new Result<>(code, message);
		result.setData(object);
		return result;
	}

	public static Result<?> success(Integer code, String message) {
		Result result = new Result<>(code, message);
		return result;
	}
}
