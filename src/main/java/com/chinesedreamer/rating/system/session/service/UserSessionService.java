package com.chinesedreamer.rating.system.session.service;
/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月18日 下午8:15:59 
 * Copyright:   Copyright (c)2015
 */
public interface UserSessionService {
	/**
	 * 查看用户是否已经登录
	 * @param sessionId
	 * @return true 已经登录	false 未登录
	 */
	public boolean userLogged(String sessionId);
}
