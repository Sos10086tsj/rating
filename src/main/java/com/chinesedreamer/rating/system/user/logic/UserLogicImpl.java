package com.chinesedreamer.rating.system.user.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.system.user.UserStatus;
import com.chinesedreamer.rating.system.user.model.User;
import com.chinesedreamer.rating.system.user.repository.UserRepository;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015ÂπÊú8Êó‰∏ãÂçà7:57:26 
 * Copyright:   Copyright (c)2015
 */
@Service
public class UserLogicImpl extends BaseLogicImpl<User, Long> implements UserLogic{
	@Resource
	private UserRepository repository;

	@Override
	public User findByUsernameAndStatus(String username, UserStatus status) {
		return this.repository.findByUsernameAndStatus(username, status);
	}

	@Override
	public List<User> findByGroupIdAndStatus(Long groupId, UserStatus status) {
		return this.repository.findByGroupIdAndStatus(groupId, status);
	}

	@Override
	public List<User> findByGroupIdAndPositionIdAndStatus(Long groupId,
			Long positionId, UserStatus status) {
		return this.repository.findByGroupIdAndPositionIdAndStatus(groupId, positionId, status);
	}

	@Override
	public List<User> findAll() {
		return this.repository.findAll();
	}

}
