package com.chinesedreamer.rating.system.session.logic;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.cache.BaseCacheAspect;
import com.chinesedreamer.rating.base.exception.SessionOverdueException;
import com.chinesedreamer.rating.system.session.model.UserSession;
import com.chinesedreamer.rating.web.filter.SessionFilter;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月18日 下午8:13:34 
 * Copyright:   Copyright (c)2015
 */
@Service
public class UserSessionLogicImpl extends BaseCacheAspect implements UserSessionLogic{

	private final Logger logger = LoggerFactory
			.getLogger(UserSessionLogicImpl.class);
	

	private final String userSessionCacheName = "sessionCache";
	private final String userSessionPrefix = "user-session-";

	@Override
	public void saveUserSessionCache() {
		UserSession userSession = this.convertUserSession();
		this.logger.info("save user session cache:" + userSession);
		setCacheName(userSessionCacheName);
		this.put(userSessionPrefix + userSession.getSessionId(), userSession);
		//TODO user id
		this.put(userSessionPrefix + this.findBySessionId(userSession.getSessionId()), userSession.getSessionId());
	}

	private UserSession convertUserSession() {
		HttpServletRequest request = SessionFilter.SessionContext.getContext();
		return new UserSession(request.getSession().getId(), new Date());
	}

	public UserSessionLogicImpl() {
		setCacheName(userSessionCacheName);
	}

	@Override
	public UserSession getUserSession(String user) {
		String sessionId = this.get(userSessionPrefix + user);
		if (StringUtils.isEmpty(sessionId)) {
			logger.info("user {} is not online, no need to update.", user);
		}
		UserSession userSession = this.get(userSessionPrefix + sessionId);
		//同步接口，不阻止
		if (null == userSession) {
			logger.info("user hasn't login, sessionId:{}", sessionId);
		}
		return userSession;
	}

	@Override
	public String getCurrentUser() {
		HttpServletRequest request = SessionFilter.SessionContext.getContext();
		String sessionId = request.getSession().getId();
		UserSession userSession = this.get(userSessionPrefix + sessionId);
		if (null == userSession) {
			throw new SessionOverdueException("链接已过期，请重新登录！");
		}
		return null;
	}

	@Override
	public UserSession findBySessionId(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
