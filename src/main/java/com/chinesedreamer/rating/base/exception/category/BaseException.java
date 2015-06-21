package com.chinesedreamer.rating.base.exception.category;


/**
 * Description: 
 * @author Paris
 * @date Jun 19, 20154:57:15 PM
 * @version beta
 */
public abstract class BaseException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2189125405992643040L;

	private String code;
	
	private String message;
	
	public BaseException(String message, Throwable cause){
		super(message, cause);
		this.message = message;
	}
	
	public BaseException(String message){
		super(message);
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
