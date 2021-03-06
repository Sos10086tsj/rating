package com.chinesedreamer.rating.system.rabc.mapping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;
import com.chinesedreamer.rating.system.rabc.authority.model.SysAuthority;
import com.chinesedreamer.rating.system.rabc.role.model.SysRole;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:48:56 
 * Copyright:   Copyright (c)2015
 */
@Entity
@Table(name = "sys_role_auth_mapping")
public class RoleAuthMapping extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1703163562469301196L;

	@Column(name = "auth_id")
	private Long authId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auth_id", referencedColumnName = "id", insertable = false, updatable = false)
	private SysAuthority authority;
	
	@Column(name = "role_id")
	private Long roleId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
	private SysRole role;

	public Long getAuthId() {
		return authId;
	}

	public SysAuthority getAuthority() {
		return authority;
	}

	public Long getRoleId() {
		return roleId;
	}

	public SysRole getRole() {
		return role;
	}

	public void setAuthId(Long authId) {
		this.authId = authId;
	}

	public void setAuthority(SysAuthority authority) {
		this.authority = authority;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setRole(SysRole role) {
		this.role = role;
	}
	
	
}
