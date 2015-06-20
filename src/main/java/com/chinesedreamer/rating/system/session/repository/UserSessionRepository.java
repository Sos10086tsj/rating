package com.chinesedreamer.rating.system.session.repository;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.system.session.model.UserSession;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月18日 下午8:12:20 
 * Copyright:   Copyright (c)2015
 */
public interface UserSessionRepository extends BaseRepository<UserSession, Long>{
	/**
	 * 根据session id查找当前用户
	 * @param sessionId
	 * @return
	 */
	public UserSession findBySessionId(String sessionId);
}
