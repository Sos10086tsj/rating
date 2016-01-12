package com.chinesedreamer.rating.system.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.chinesedreamer.rating.base.jpa.repository.BaseRepository;
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
public interface UserRepository extends BaseRepository<User, Long>{
	
	/**
	 * 根据用户名查�
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
	
	public User findByName(String name);
	
	/**
	 * 根据用户名查�
	 * @param username
	 * @return
	 */
	public User findByUsernameAndStatus(String username,UserStatus status);
	
	/**
	 * 查找某个组别�
	 * @param groupId
	 * @param status
	 * @return
	 */
	public List<User> findByGroupIdAndStatus(Long groupId,UserStatus status);
	
	/**
	 * 查找某个组别内，某个职位�
	 * @param groupId
	 * @param position
	 * @param status
	 * @return
	 */
	public List<User> findByGroupIdAndPositionIdAndStatus(Long groupId,Long positionId, UserStatus status);
	
	public List<User> findByStatusOrderByIdAsc(UserStatus status);
	
	public List<User> findByStatusAndNameLike(UserStatus status, String name);
	
	@Query("select u from User u inner join u.userGroup ug where ug.level = :level ")
	public List<User> findByGroupLevel(@Param("level") UserGroupLevel level);
	
	@Query("select u from User u inner join u.userGroup ug where ug.level = :level and u.positionId = :positionId")
	public List<User> findByGroupLevelAndPosition(@Param("level")UserGroupLevel level,@Param("positionId")Integer positionId);
	
	public List<User> findByStatus(UserStatus status);
}
