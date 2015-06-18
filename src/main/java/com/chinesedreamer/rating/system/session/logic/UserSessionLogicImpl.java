package com.chinesedreamer.rating.system.session.logic;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.system.session.model.UserSession;
import com.chinesedreamer.rating.system.session.repository.UserSessionRepository;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月18日 下午8:13:34 
 * Copyright:   Copyright (c)2015
 */
@Service
public class UserSessionLogicImpl extends BaseLogicImpl<UserSession, Long> implements UserSessionLogic{
	@Resource
	private UserSessionRepository repository;
	
	@Override
	public UserSession findBySessionId(String sessionId) {
		return this.repository.findBySessionId(sessionId);
	}

}
