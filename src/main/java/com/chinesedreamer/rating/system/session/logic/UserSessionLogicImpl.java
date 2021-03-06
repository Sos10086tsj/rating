package com.chinesedreamer.rating.system.session.logic;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.cache.BaseCacheAspect;
import com.chinesedreamer.rating.system.session.exception.SessionOverdueException;
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
	public void saveUserSessionCache(UserSession userSession) {
		this.logger.info("save user session cache:" + userSession);
		if (null == userSession) {
			return;
		}
		setCacheName(userSessionCacheName);
		this.put(userSessionPrefix + userSession.getSessionId(), userSession);
	}

	public UserSessionLogicImpl() {
		setCacheName(userSessionCacheName);
	}

	@Override
	public UserSession getCurrentUserSession() {
		HttpServletRequest request = SessionFilter.SessionContext.getContext();
		String sessionId = request.getSession().getId();
		UserSession userSession = this.get(userSessionPrefix + sessionId);
		if (null == userSession) {
			throw new SessionOverdueException("链接已过期，请重新登录！");
		}
		return userSession;
	}

	@Override
	public UserSession findByUsername(String username) {
		return this.repository.findByUsername(username);
	}

	@Override
	public UserSession findBySessionId(String sessionId) {
		List<UserSession> userSessions = this.repository.findBySessionIdOrderByCreateDateDesc(sessionId);
		return userSessions.isEmpty() ? null : userSessions.get(0);
	}

	@Override
	public UserSession save(UserSession userSession) {
		return this.repository.saveAndFlush(userSession);
	}

	@Override
	public void validateSession() throws SessionOverdueException{
		HttpServletRequest request = SessionFilter.SessionContext.getContext();
		String sessionId = request.getSession().getId();
		UserSession userSession = this.get(userSessionPrefix + sessionId);
		if (null == userSession) {
			throw new SessionOverdueException("链接已过期，请重新登录！");
		}
	}

	@Override
	public void clear(UserSession userSession) {
		this.evict(userSessionPrefix + userSession.getSessionId());
		List<UserSession> userSessions = this.repository.findBySessionIdOrderByCreateDateDesc(userSession.getSessionId());
		for (UserSession session : userSessions) {
			this.repository.delete(session);
		}
	}

}
