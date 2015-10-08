package com.chinesedreamer.rating.system.rabc.opr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
public class SysOperation extends BaseEntity<Long>{

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

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public Boolean getShow() {
		return show;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}
	
	
}
