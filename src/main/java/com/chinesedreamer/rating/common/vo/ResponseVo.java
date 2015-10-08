package com.chinesedreamer.rating.common.vo;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午7:19:17 
 * Copyright:   Copyright (c)2015
 */
public class ResponseVo {
	private Boolean hasException = Boolean.FALSE;
	private String errorMessage;//错误提示信息
	private Object obj;//传递的对象
	
	public ResponseVo(){
		
	}
	
	public ResponseVo(Object obj){
		this.obj = obj;
	}
	
	public ResponseVo(String errorMessage){
		this.errorMessage = errorMessage;
		this.hasException = Boolean.TRUE;
	}
	
	public ResponseVo(String errorMessage,Object obj){
		this.obj = obj;
		this.errorMessage = errorMessage;
		this.hasException = Boolean.TRUE;
	}

	public Boolean getHasException() {
		return hasException;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public Object getObj() {
		return obj;
	}

	public void setHasException(Boolean hasException) {
		this.hasException = hasException;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	
}
