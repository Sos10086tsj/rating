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
 * @date 2015εΉζ1ζδΈε7:15:27 
 * Copyright:   Copyright (c)2015
 */
public interface UserService {
	/**
	 * η¨ζ·η»ε½
	 * @param username
	 * @param password
	 * @return
	 */
	public ResponseVo login(String username, String password) throws UserFrozenException,UserNotExistException,PasswordIncorrectException;
	
	/**
	 * θ·εη¨ζ·θε
	 * @param username
	 * @return
	 */
	public List<Menu> getUserMenus(String username);
	
	/**
	 * ζΎε°ζζε·²η₯η¨ζ
	 * @return
	 */
	public List<UserVo> getAllUsers();
}
