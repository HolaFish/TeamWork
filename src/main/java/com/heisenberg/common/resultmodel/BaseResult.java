package com.heisenberg.common.resultmodel;

import java.util.Map;

public class BaseResult<T> {
	private String resultType;
	private String message;
	private T obj;
	private Map<String,Object> mapResult;
	
	public BaseResult() {
		super();
	}
	
	public BaseResult(String resultType, String message) {
		super();
		this.resultType = resultType;
		this.message = message;
	}

	public BaseResult(String resultType, String message, T obj) {
		super();
		this.resultType = resultType;
		this.message = message;
		this.obj = obj;
	}
	
	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getMapResult() {
		return mapResult;
	}

	public void setMapResult(Map<String, Object> mapResult) {
		this.mapResult = mapResult;
	}
	
	
}
