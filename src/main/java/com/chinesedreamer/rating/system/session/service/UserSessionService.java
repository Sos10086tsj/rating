package com.chinesedreamer.rating.system.session.service;

import com.chinesedreamer.rating.system.session.model.UserSession;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015��8�下午8:15:59 
 * Copyright:   Copyright (c)2015
 */
public interface UserSessionService {
	public void saveUserSessionCache();
	/**
	 * 检查session是否存在
	 */
	public void validateSession();
	public UserSession getCurrentUserSession();
}
