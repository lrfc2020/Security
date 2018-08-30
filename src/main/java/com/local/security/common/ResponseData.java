package com.local.security.common;

import com.local.security.common.enums.RespCodeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseData<T> implements Serializable {
	/**
	 * 返回的状态码
	 */
	private  Integer code;

	/**
	 * 返回的消息
	 */
	private  String msg;

	/**
	 * 返回的数据
	 */
	private  T data;

	/**
	 * 无参构造函数
	 */
	public ResponseData() {}


	public static ResponseData getInstance() {
		return new ResponseData();
	}



	/**
	 * Description  [返回失败信息]
	 *
	 * @param code  [状态码]
	 * @param message  [状态消息]
	 * @return  ResponseData<T>
	 */
	public ResponseData<T> failed(Integer code, String message) {
		return  new ResponseData(code, message, null);
	}

	public ResponseData<T> failed(RespCodeEnum codeEnum) {
		return generateRespData(codeEnum, null);
	}

	public ResponseData<T> success(RespCodeEnum codeEnum, T data) {
		return generateRespData(codeEnum, data);
	}

	public ResponseData<T> success() {
		return generateRespData(RespCodeEnum.SUCCESS, null);
	}

	/**
	 *
	 * @param code  状态码
	 * @param message   消息
	 * @param data   数据
	 * @return
	 */
	public ResponseData<T> generateRespData(Integer code, String message, T data) {
		return new ResponseData(code, message, data);
	}

	/**
	 *
	 * @param codeEnum
	 * @param data
	 * @return
	 */
	public ResponseData<T> generateRespData(RespCodeEnum codeEnum, T data) {
		return generateRespData(codeEnum.getCode(), codeEnum.getMessage(), data);
	}

	/**
	 * Description:  [构造方法]
	 * @param code  状态码
	 * @param msg  消息
	 * @param data  数据
	 */
	public ResponseData(Integer code, String msg, T data) {
		this.code = code;
		this.msg  = msg;
		this.data = data;
	}
}
