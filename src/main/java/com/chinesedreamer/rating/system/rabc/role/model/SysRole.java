package com.chinesedreamer.rating.system.rabc.role.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseVersionEntity;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:39:32 
 * Copyright:   Copyright (c)2015
 */
@Entity
@Table(name = "sys_role")
public @Getter @Setter class SysRole extends BaseVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5641406531892661576L;

	@Column
	private String name;
}
