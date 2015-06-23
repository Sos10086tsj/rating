package com.chinesedreamer.rating.system.session.logic;

import com.chinesedreamer.rating.system.session.exception.SessionOverdueException;
import com.chinesedreamer.rating.system.session.model.UserSession;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015��8�下午8:13:18 
 * Copyright:   Copyright (c)2015
 */
public interface UserSessionLogic {
	
	/**
	 * 保存用户session
	 */
	public void saveUserSessionCache(UserSession userSession);
	
	/**
	 * 检查session是否存在
	 */
	public void validateSession() throws SessionOverdueException;
	
	/**
	 * 获取当前用户
	 * @return
	 */
	public UserSession getCurrentUserSession();
	
	/**
	 * 根据用户名查找 UserSession
	 * @param username
	 * @return
	 */
	public UserSession findByUsername(String username);
	/**
	 * 根据session id查找 UserSession
	 * @param sessionId
	 * @return
	 */
	public UserSession findBySessionId(String sessionId);
	
	/**
	 * 保存user session表
	 * @param userSession
	 * @return
	 */
	public UserSession save(UserSession userSession);
}
