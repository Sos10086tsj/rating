package com.chinesedreamer.rating.system.rabc.opr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:24:54 
 * Copyright:   Copyright (c)2015
 */
@Entity
@Table(name = "sys_operation")
public @Getter @Setter class SysOperation extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7887559971663447044L;

	@Column
	private String code;
	
	@Column
	private String name;
	
	@Column(name = "show", columnDefinition = "TINYINT(1)")
	private Boolean show;
}
