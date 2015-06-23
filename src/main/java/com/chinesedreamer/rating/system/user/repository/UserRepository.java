package com.chinesedreamer.rating.system.user.repository;

import java.util.List;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
import com.chinesedreamer.rating.system.user.UserStatus;
import com.chinesedreamer.rating.system.user.model.User;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015��8�下午7:53:51 
 * Copyright:   Copyright (c)2015
 */
public interface UserRepository extends BaseRepository<User, Long>{
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
	
	public List<User> findByStatusOrderByIdAsc(UserStatus status);
}
