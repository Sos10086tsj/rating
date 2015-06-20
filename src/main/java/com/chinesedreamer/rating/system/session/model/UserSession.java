package com.chinesedreamer.rating.system.session.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/** 
 * Description: 用户session表
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月18日 下午8:09:26 
 * Copyright:   Copyright (c)2015
 */

@Entity
@Table(name = "sys_user_session")
public @Getter @Setter class UserSession extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9045807585883359876L;

	@Column
	private String username;//用户名
	
	@Column(name = "session_id")
	private String sessionId;//用户当前session id
	
	@Column(name = "create_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate = new Date();//创建时间
}
