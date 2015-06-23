package com.chinesedreamer.rating.system.user.service;

import java.util.List;

import com.chinesedreamer.rating.common.vo.ResponseVo;
import com.chinesedreamer.rating.system.user.exception.PasswordIncorrectException;
import com.chinesedreamer.rating.system.user.exception.UserFrozenException;
import com.chinesedreamer.rating.system.user.exception.UserNotExistException;
import com.chinesedreamer.rating.system.user.vo.Menu;
import com.chinesedreamer.rating.system.user.vo.UserVo;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015��1�上午7:15:27 
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
	 * 找到所有已知用�
	 * @return
	 */
	public List<UserVo> getAllUsers();
}
