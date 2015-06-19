package com.chinesedreamer.rating.base.exception;
/**
 * Description: 
 * @author Paris
 * @date Jun 19, 20154:59:17 PM
 * @version beta
 */
public class BizException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3355113620854658771L;

	public BizException(String message, Throwable cause){
		super(message, cause);
	}
	
	public BizException(String message){
		super(message);
	}
}
