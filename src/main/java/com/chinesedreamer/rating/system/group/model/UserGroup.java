package com.chinesedreamer.rating.system.group.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseVersionEntity;
import com.chinesedreamer.rating.system.group.UserGroupLevel;

/** 
 * Description: 用户组别
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月18日 下午8:02:24 
 * Copyright:   Copyright (c)2015
 */
@Entity
@Table(name = "sys_group")
public @Getter @Setter class UserGroup extends BaseVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3236770836183678124L;

	@Column(name = "level")
	@Enumerated(EnumType.ORDINAL)
	private UserGroupLevel level;//用户组类型
	
	@Column
	private String name;//组名
}
