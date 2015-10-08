package com.chinesedreamer.rating.system.rabc.mapping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;
import com.chinesedreamer.rating.system.rabc.role.model.SysRole;
import com.chinesedreamer.rating.system.user.model.User;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:43:12 
 * Copyright:   Copyright (c)2015
 */
@Entity
@Table(name = "sys_user_role")
public class UserRoleMapping extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6884395327369976660L;
	
	@Column(name = "user_id")
	private Long userId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
	private User user;
	
	@Column(name = "role_id")
	private Long roleId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
	private SysRole role;

	public Long getUserId() {
		return userId;
	}

	public User getUser() {
		return user;
	}

	public Long getRoleId() {
		return roleId;
	}

	public SysRole getRole() {
		return role;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}
	
	
}
