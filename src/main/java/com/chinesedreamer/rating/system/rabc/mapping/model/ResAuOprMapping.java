package com.chinesedreamer.rating.system.rabc.mapping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;
import com.chinesedreamer.rating.system.rabc.authority.model.SysAuthority;
import com.chinesedreamer.rating.system.rabc.opr.model.SysOperation;
import com.chinesedreamer.rating.system.rabc.resource.model.SysResource;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 上午10:28:16 
 * Copyright:   Copyright (c)2015
 */
@Entity
@Table(name = "sys_res_au_opr_mapping")
public @Getter @Setter class ResAuOprMapping extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7326781406017322473L;

	@Column(name = "res_code")
	private String resCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "res_code", referencedColumnName = "code", insertable = false, updatable = false)
	private SysResource resource;
	
	@Column(name = "auth_code")
	private String authCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auth_code", referencedColumnName = "code", insertable = false, updatable = false)
	private SysAuthority authority;
	
	@Column(name = "opr_code")
	private String oprCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "opr_code", referencedColumnName = "code", insertable = false, updatable = false)
	private SysOperation operation;
}
