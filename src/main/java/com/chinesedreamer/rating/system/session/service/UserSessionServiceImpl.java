package com.chinesedreamer.rating.system.session.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.system.session.exception.SessionOverdueException;
import com.chinesedreamer.rating.system.session.logic.UserSessionLogic;
import com.chinesedreamer.rating.system.session.model.UserSession;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015��8�下午8:17:08 
 * Copyright:   Copyright (c)2015
 */
@Service
public class UserSessionServiceImpl  implements UserSessionService{

	@Resource
	private UserSessionLogic logic;

	@Override
	public UserSession getCurrentUserSession() {
		return this.logic.getCurrentUserSession();
	}

	@Override
	public void validateSession() throws SessionOverdueException{
		this.logic.validateSession();
	}
}
