package com.chinesedreamer.rating.system.user.service;

import java.util.List;

import com.chinesedreamer.rating.common.vo.ResponseVo;
import com.chinesedreamer.rating.system.user.exception.PasswordIncorrectException;
import com.chinesedreamer.rating.system.user.exception.UserFrozenException;
import com.chinesedreamer.rating.system.user.exception.UserNotExistException;
import com.chinesedreamer.rating.system.user.model.User;
import com.chinesedreamer.rating.system.user.vo.Menu;
import com.chinesedreamer.rating.system.user.vo.UserVo;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 
 * Copyright:   Copyright (c)2015
 */
public interface UserService {
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	public ResponseVo login(String username, String password) throws UserFrozenException,UserNotExistException,PasswordIncorrectException;
	
	/**
	 * 获取用户菜单
	 * @param username
	 * @return
	 */
	public List<Menu> getUserMenus(String username);
	
	/**
	 * 找到所有已知用
	 * @return
	 */
	public List<UserVo> getAllUsers();
	
	/**
	 * 创建用户
	 * @param username
	 * @param name
	 * @param groupId
	 * @param positionId
	 * @param phone
	 */
	public void saveUser(String username, String name, Long groupId, Integer positionId, String phone);
	
	/**
	 * 更新用户
	 * @param username
	 * @param name
	 * @param groupId
	 * @param positionId
	 * @param phone
	 */
	public void updateUser(String username, String name, Long groupId, Integer positionId, String phone);
	
	/**
	 * 查找用户
	 * @param username
	 * @return
	 */
	public User getUser(String username);
}
