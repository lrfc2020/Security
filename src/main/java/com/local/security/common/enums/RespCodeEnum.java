package com.local.security.common.enums;

public enum RespCodeEnum {

	SUCCESS(0, "请求成功"),
	FAILED(-1, "请求失败"),
	ERROR_BAD_REQUEST(400, "错误请求"),
	ERROR_UNAUTHORIZED(401, "请先登录"),
	ERROR_FORBIDDEN(403, "请求被拒绝"),
	ERROR_NOT_FOUND(404, "请求未找到"),
	ERROR_INTERNAL_SERVER(500, "服务器内部错误"),
	ERROR_PASSWORD(410,"用户名或密码输入错误");

	/**
	 * code  状态码
	 */
	private Integer code;

	/**
	 * message  状态信息
	 */
	private String message;

	/**
	 * Description:  构造方法
	 *
	 * @param code  状态值
	 * @param message  状态信息
	 */
	private RespCodeEnum(int code, String message) {
		this.code    = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
}
