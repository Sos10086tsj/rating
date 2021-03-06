package com.chinesedreamer.rating.system.user.service;

import java.util.List;

import com.chinesedreamer.rating.common.vo.ResponseVo;
import com.chinesedreamer.rating.common.vo.SelectVo;
import com.chinesedreamer.rating.system.session.model.UserSession;
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
	 * 退出
	 * @param user
	 */
	public void logout(UserSession userSession);
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
	
	/**
	 * 模糊匹配用户
	 * @param name
	 * @return
	 */
	public List<SelectVo> lookupUser(String name);
	
	/**
	 * 获取需要投票的客户列表
	 * @param currentUser
	 * @param tmplCode
	 * @return 
	 */
	public List<SelectVo> getScorers(User currentUser, String tmplCode);
	
	public User showUserProfile(String username);
	/**
	 * 修改用户信息
	 * @param username
	 * @param oldPassword
	 * @param newPassword
	 * @param name
	 * @param phone
	 * @return
	 */
	public ResponseVo updateProfile(String username, String oldPassword, String newPassword,
			String name,String phone);
	
	/**
	 * 删除用户
	 * @param userId
	 */
	public void deleteUser(Long userId);
	
	public User findOne(Long userId);
	
	public List<User> getGroupUsers(Long groupId);
}
