package com.chinesedreamer.rating.system.user.service;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.exception.category.BizException;
import com.chinesedreamer.rating.common.utils.EncryptionUtil;
import com.chinesedreamer.rating.common.vo.ResponseVo;
import com.chinesedreamer.rating.system.session.logic.UserSessionLogic;
import com.chinesedreamer.rating.system.session.model.UserSession;
import com.chinesedreamer.rating.system.user.UserStatus;
import com.chinesedreamer.rating.system.user.exception.PasswordIncorrectException;
import com.chinesedreamer.rating.system.user.exception.UserFrozenException;
import com.chinesedreamer.rating.system.user.exception.UserNotExistException;
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
	public ResponseVo login(String username, String password) throws UserFrozenException,UserNotExistException,PasswordIncorrectException{
		this.logger.info("user:{} passwor:{} try to login.", username, password);
		User user = this.logic.findByUsernameAndStatus(username, UserStatus.ACTIVE);
		if(null == user){
			BizException ex = null;
			if (null == this.logic.findByUsernameAndStatus(username, UserStatus.INACTIVE)) {
				ex = new UserFrozenException("用户：" + username + " 已被禁用，请联系管理员");
			}else {
				ex = new UserNotExistException("用户：" + username + " 不存在");
			}
			logger.error("{}",ex);
			throw ex;
		}
		if (!EncryptionUtil.md5L32(password + user.getSalt()).equals(user.getPassword())) {
			PasswordIncorrectException ex = new PasswordIncorrectException("用户名或密码错误");
			logger.error("{}",ex);
			throw ex;
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
