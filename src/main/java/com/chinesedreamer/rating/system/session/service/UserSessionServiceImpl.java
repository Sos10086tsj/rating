package com.chinesedreamer.rating.system.session.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.system.session.logic.UserSessionLogic;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月18日 下午8:17:08 
 * Copyright:   Copyright (c)2015
 */
@Service
public class UserSessionServiceImpl  implements UserSessionService{

	@Resource
	private UserSessionLogic userSessionLogic;

	@Override
	public void saveUserSessionCache() {
		this.userSessionLogic.saveUserSessionCache();
	}
}
