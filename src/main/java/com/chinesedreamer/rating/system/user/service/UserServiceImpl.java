package com.chinesedreamer.rating.system.user.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.common.utils.EncryptionUtil;
import com.chinesedreamer.rating.common.vo.ResponseVo;
import com.chinesedreamer.rating.system.session.logic.UserSessionLogic;
import com.chinesedreamer.rating.system.session.model.UserSession;
import com.chinesedreamer.rating.system.user.UserStatus;
import com.chinesedreamer.rating.system.user.logic.UserLogic;
import com.chinesedreamer.rating.system.user.model.User;
import com.chinesedreamer.rating.web.filter.SessionFilter;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午7:15:59 
 * Copyright:   Copyright (c)2015
 */
@Service
public class UserServiceImpl implements UserService{
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Resource
	private UserLogic logic;
	@Resource
	private UserSessionLogic userSessionLogic;
	
	@Override
	public ResponseVo login(String username, String password) {
		this.logger.info("user:{} passwor:{} try to login.", username, password);
		User user = this.logic.findByUsernameAndStatus(username, UserStatus.ACTIVE);
		if (null == user || !EncryptionUtil.md5L32(password + user.getSalt()).equals(user.getPassword())) {
			return new ResponseVo("用户不存在或者密码错误！");
		}
		//保存缓存session 信息
		UserSession userSession = this.userSessionLogic.findByUsername(username);
		if (null == userSession) {
			userSession = new UserSession();
		}
		userSession.setSessionId(SessionFilter.SessionContext.getContext().getSession().getId());
		userSession.setCreateDate(new Date());
		this.userSessionLogic.save(userSession);
		this.userSessionLogic.saveUserSessionCache();
		return new ResponseVo(user);
	}

}
