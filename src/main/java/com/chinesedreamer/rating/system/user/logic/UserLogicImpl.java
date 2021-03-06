package com.chinesedreamer.rating.system.user.logic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogicImpl;
import com.chinesedreamer.rating.system.group.UserGroupLevel;
import com.chinesedreamer.rating.system.user.UserStatus;
import com.chinesedreamer.rating.system.user.model.User;
import com.chinesedreamer.rating.system.user.repository.UserRepository;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 
 * Copyright:   Copyright (c)2015
 */
@Service
public class UserLogicImpl extends BaseLogicImpl<User, Long> implements UserLogic{
	@Resource
	private UserRepository repository;
	
	@Override
	public User findByUsername(String username) {
		return this.repository.findByUsername(username);
	}

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
	public List<User> findUsers(UserStatus status){
		return this.repository.findByStatus(status);
	}

	@Override
	public List<User> findByStatusAndNameLike(UserStatus status, String name) {
		return this.repository.findByStatusAndNameLike(status, "%"+name+"%");
	}

	@Override
	public List<User> findByGroupLevel(UserGroupLevel level) {
		return this.repository.findByGroupLevel(level);
	}

	@Override
	public List<User> findByGroupLevelAndPosition(UserGroupLevel level,
			Integer positionId) {
		return this.repository.findByGroupLevelAndPosition(level, positionId);
	}

	@Override
	public User findByName(String name) {
		return this.repository.findByName(name);
	}

}
