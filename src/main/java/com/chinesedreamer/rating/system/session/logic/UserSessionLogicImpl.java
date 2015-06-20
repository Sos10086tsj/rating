package com.chinesedreamer.rating.system.session.logic;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.cache.BaseCacheAspect;
import com.chinesedreamer.rating.base.exception.SessionOverdueException;
import com.chinesedreamer.rating.system.session.model.UserSession;
import com.chinesedreamer.rating.system.session.repository.UserSessionRepository;
import com.chinesedreamer.rating.web.filter.SessionFilter;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015��8�下午8:13:34 
 * Copyright:   Copyright (c)2015
 */
@Service
public class UserSessionLogicImpl extends BaseCacheAspect implements UserSessionLogic{

	private final Logger logger = LoggerFactory
			.getLogger(UserSessionLogicImpl.class);

	@Resource
	private UserSessionRepository repository;

	private final String userSessionCacheName = "sessionCache";
	private final String userSessionPrefix = "user-session-";

	@Override
	public void saveUserSessionCache() {
		UserSession userSession = this.convertUserSession();
		this.logger.info("save user session cache:" + userSession);
		if (null == userSession) {
			return;
		}
		setCacheName(userSessionCacheName);
		this.put(userSessionPrefix + userSession.getSessionId(), userSession);
		this.put(userSessionPrefix + userSession.getUsername(), userSession.getSessionId());
	}

	private UserSession convertUserSession() {
		HttpServletRequest request = SessionFilter.SessionContext.getContext();
		return this.repository.findBySessionId(request.getSession().getId());
	}

	public UserSessionLogicImpl() {
		setCacheName(userSessionCacheName);
	}

	@Override
	public UserSession getUserSession(String username) {
		String sessionId = this.get(userSessionPrefix + username);
		if (StringUtils.isEmpty(sessionId)) {
			logger.info("user {} is not online.", username);
		}
		UserSession userSession = this.get(userSessionPrefix + sessionId);
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
		return userSession.getUsername();
	}

}
