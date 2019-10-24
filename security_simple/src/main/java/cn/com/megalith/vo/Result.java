package cn.com.megalith.vo;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 *   接口返回数据格式
 * @author scott
 * @email jeecgos@163.com
 * @date  2019年1月19日
 */

public class Result<T> implements Serializable {

	private String message ;

	private Integer code ;

	private T result;


	public static<T> Result<T> ok(T data) {
		Result<T> r = new Result<>();
		r.setMessage("ok");
		r.setCode(HttpStatus.OK.value());
		r.setResult(data);
		return r;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
}