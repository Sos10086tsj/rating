package com.chinesedreamer.rating.system.session.repository;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.system.session.model.UserSession;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午6:43:11 
 * Copyright:   Copyright (c)2015
 */
public interface UserSessionRepository extends BaseRepository<UserSession, Long>{
	public UserSession findByUsername(String username);
	public UserSession findBySessionId(String sessionId);
}
