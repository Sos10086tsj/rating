package com.chinesedreamer.rating.system.rabc.authority.model;

import javax.persistence.Column;
import javax.persistence.Table;


import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:21:46 
 * Copyright:   Copyright (c)2015
 */
@Entity
@Table(name = "sys_authority")
public @Getter @Setter class SysAuthority extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7487948227419865741L;

	@Column
	private String code;
	
	@Column
	private String name;
	
	@Column(name = "show", columnDefinition="TINYINT(1)")
	private Boolean show;
}
