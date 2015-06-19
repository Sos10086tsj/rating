package com.chinesedreamer.rating.system.session.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.system.session.logic.UserSessionLogic;
import com.chinesedreamer.rating.system.session.model.UserSession;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月18日 下午8:17:08 
 * Copyright:   Copyright (c)2015
 */
@Service
public class UserSessionServiceImpl implements UserSessionService{
	@Resource
	private UserSessionLogic logic;

	@Override
	public boolean userLogged(String sessionId) {
		UserSession userSession = this.logic.findBySessionId(sessionId);
		return (null == userSession ? false : true );
	}

}
