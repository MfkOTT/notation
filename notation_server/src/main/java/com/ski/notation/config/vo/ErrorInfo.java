package com.ski.notation.config.vo;

/**
 * 全局异常构造体
 * 
 * @author maoyl05
 *
 * @param <T>
 */
public class ErrorInfo<T> {
	public static final Integer OK = 0;
	public static final Integer ERROR = -100;

	private Integer code;
	private String message;
	private String url;
	private T data;

	public ErrorInfo(){}
	public ErrorInfo(Integer code, String message){
		this.code = code;
		this.message = message;
	}
	public ErrorInfo(Integer code, String message,T data){
		this.code = code;
		this.message = message;
		this.data = data;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ErrorInfo [code=" + code + ", message=" + message + ", url="
				+ url + ", data=" + data + "]";
	}
}
