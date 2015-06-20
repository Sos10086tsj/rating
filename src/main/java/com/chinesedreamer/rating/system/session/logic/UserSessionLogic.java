package com.chinesedreamer.rating.system.session.logic;

import com.chinesedreamer.rating.system.session.model.UserSession;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015��8�下午8:13:18 
 * Copyright:   Copyright (c)2015
 */
public interface UserSessionLogic {
	
	public void saveUserSessionCache();
	
	public UserSession getUserSession(String username);
	
	public String getCurrentUser();
}
