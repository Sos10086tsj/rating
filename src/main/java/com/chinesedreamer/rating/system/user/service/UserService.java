package com.chinesedreamer.rating.system.user.service;

import com.chinesedreamer.rating.common.vo.ResponseVo;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午7:15:27 
 * Copyright:   Copyright (c)2015
 */
public interface UserService {
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public ResponseVo login(String username, String password);
}
