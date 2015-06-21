package com.chinesedreamer.rating.system.user.exception;

import com.chinesedreamer.rating.base.exception.category.BizException;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午8:44:08 
 * Copyright:   Copyright (c)2015
 */
public class UserFrozenException extends BizException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4174595831834938118L;

	public UserFrozenException(String message) {
		super(message);
	}

}
