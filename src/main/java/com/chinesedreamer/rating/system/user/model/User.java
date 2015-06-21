package com.chinesedreamer.rating.system.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseVersionEntity;
import com.chinesedreamer.rating.system.user.UserStatus;

/** 
 * Description: 用户类
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月18日 下午7:48:10 
 * Copyright:   Copyright (c)2015
 */
@Entity
@Table(name = "sys_user")
public @Getter @Setter class User extends BaseVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1570580429146763239L;

	@Column
	private String username;//用户名
	
	@Column
	private String salt;//随即盐
	
	@Column
	private String password;//密码
	
	@Column
	private String name;//用户姓名 
	
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;//用户状态
	
	@Column
	private String phone;//电话
	
	@Column(name = "group_id")
	private Long groupId;//用户组别。如总体组、普通组
	
	@Column(name = "position_id")
	private Long positionId;//职位。如组长、组员
}
