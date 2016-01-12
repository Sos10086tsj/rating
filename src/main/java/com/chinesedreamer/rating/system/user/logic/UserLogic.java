package com.chinesedreamer.rating.system.user.logic;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.logic.BaseLogic;
import com.chinesedreamer.rating.system.group.UserGroupLevel;
import com.chinesedreamer.rating.system.user.UserStatus;
import com.chinesedreamer.rating.system.user.model.User;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 
 * Copyright:   Copyright (c)2015
 */
public interface UserLogic extends BaseLogic<User, Long>{
	
	/**
	 * 根据用户名查找用�
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
	/**
	 * 根据姓名查找用户
	 * @param name
	 * @return
	 */
	public User findByName(String name);
	/**
	 * 根据用户名查找用�
	 * @param username
	 * @return
	 */
	public User findByUsernameAndStatus(String username,UserStatus status);
	
	/**
	 * 查找某个组别的用�
	 * @param groupId
	 * @param status
	 * @return
	 */
	public List<User> findByGroupIdAndStatus(Long groupId,UserStatus status);
	
	/**
	 * 查找某个组别内，某个职位的用�
	 * @param groupId
	 * @param position
	 * @param status
	 * @return
	 */
	public List<User> findByGroupIdAndPositionIdAndStatus(Long groupId,Long positionId, UserStatus status);
	
	public List<User> findUsers(UserStatus status);
	
	/**
	 * 模糊查询有效用户
	 * @param status
	 * @param name
	 * @return
	 */
	public List<User> findByStatusAndNameLike(UserStatus status, String name);
	
	public List<User> findByGroupLevel(UserGroupLevel level);
	
	public List<User> findByGroupLevelAndPosition(UserGroupLevel level,Integer positionId);
}
