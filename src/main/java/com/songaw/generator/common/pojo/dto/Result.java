package com.songaw.generator.common.pojo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Result<T> implements Serializable {

	private static final long serialVersionUID = -1872625368730485194L;

	public static final int CODE_SUCCESS = 200;
    public static final int CODE_SYS_ERR = 500; // 系统错误
    public static final int CODE_PARAMS_ERR = 400; // 参数错误
	public static final int CODE_LOGIN_ERR = 401; // 未授权：登录失败
    public static final int CODE_AUTHORITY_ERR = 403; // 权限错误
    public static final int CODE_NOT_FOUND = 404; // 无法找到文件
    public static final int CODE_ENCRYPTION_ERR = 405; // 资源被禁止
    public static final String SYS_ERR_STRING = "系统错误";
    
    
    public Result<T> code(int code) {
        this.code = code;
		return this;
	}

	public Result<T> message(String message) {
		this.message = message;
		return this;
	}

	public Result<T> data(T result) {
		this.data = result;
		return this;
	}
    
    public Result(){}
    
    public static<T> Result<T> getSuccessResult(T value) {
		Result<T> rslt = new Result<T>();
		rslt.setCode(CODE_SUCCESS);
		rslt.setData(value);
		return rslt;
    }
	public static<T> Result<T> getCodeAuthorityErrResult(String message){
		Result<T> rslt = new Result<T>();
		rslt.setCode(CODE_AUTHORITY_ERR);
		rslt.setMessage(message);
		return rslt;
	}
	public static<T> Result<T> getCodeLoginErrResult(String message){
		Result<T> rslt = new Result<T>();
		rslt.setCode(CODE_LOGIN_ERR);
		rslt.setMessage(message);
		return rslt;
	}
	public static<T> Result<T> getCodeNotFoundResult(String message){
		Result<T> rslt = new Result<T>();
		rslt.setCode(CODE_NOT_FOUND);
		rslt.setMessage(message);
		return rslt;
	}
    public static<T> Result<T> getParamsErrorResult(String message){
    	Result<T> rslt = new Result<T>();
		rslt.setCode(CODE_PARAMS_ERR);
		rslt.setMessage(message);
		return rslt;
    }
    
    public static<T> Result<T> getSystemErrorResult(String message){
    	Result<T> rslt = new Result<T>();
		rslt.setCode(CODE_SYS_ERR);
		rslt.setMessage(message);
		return rslt;
    }

    /** 错误码 **/
	public int code=200;
	
	/** 返回信息 **/
    public String message;
    
    /** 返回对象 存放具体的json数据 **/
    public T data;
    
    @JsonProperty("success")
	public boolean getSuccess() {
		return this.code == CODE_SUCCESS;
	}

}