package com.chinesedreamer.rating.system.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseVersionEntity;
import com.chinesedreamer.rating.system.group.model.UserGroup;
import com.chinesedreamer.rating.system.user.UserStatus;

/** 
 * Description: 用户
 * @author Paris Tao
 * @version 1.0beta
 * @date 
 * Copyright:   Copyright (c)2015
 */
@Entity
@Table(name = "sys_user")
public class User extends BaseVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1570580429146763239L;

	@Column
	private String username;//用户
	
	@Column
	private String salt;//随即
	
	@Column
	private String password;//密码
	
	@Column
	private String name;//用户姓名 
	
	@Column(name = "status")
	@Enumerated(EnumType.ORDINAL)
	private UserStatus status;//用户状
	
	@Column
	private String phone;//电话
	
	@Column(name = "group_id")
	private Long groupId;//用户组别。如总体组、普通组
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id", referencedColumnName = "id", insertable = false, updatable = false)
	private UserGroup userGroup;
	
	@Column(name = "position_id")
	private Integer positionId;//职位。如组长、组

	public String getUsername() {
		return username;
	}

	public String getSalt() {
		return salt;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public UserStatus getStatus() {
		return status;
	}

	public String getPhone() {
		return phone;
	}

	public Long getGroupId() {
		return groupId;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	
	
}
