package com.chinesedreamer.rating.system.rabc.resource.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/** 
 * Description: 系统资源列表，用来控制菜单
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:05:55 
 * Copyright:   Copyright (c)2015
 */
@Entity
@Table(name = "sys_resource")
public class SysResource extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8109366773417451037L;

	@Column
	private String code;//资源code，唯一
	
	@Column
	private String name;//资源名称
	
	@Column
	private String url;//资源默认url
	
	@Column(name = "show",columnDefinition = "TINYINT(1)")
	private Boolean show;
	
	@Column(name = "parent_code")
	private String parentCode;//上级菜单code
	
	@Column(name = "seq")
	private BigDecimal seq;

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public Boolean getShow() {
		return show;
	}

	public String getParentCode() {
		return parentCode;
	}

	public BigDecimal getSeq() {
		return seq;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setShow(Boolean show) {
		this.show = show;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public void setSeq(BigDecimal seq) {
		this.seq = seq;
	}
	
	
}
