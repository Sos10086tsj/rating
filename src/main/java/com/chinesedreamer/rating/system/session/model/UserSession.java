package com.chinesedreamer.rating.system.session.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Feb 9, 201511:34:21 AM
 * @version beta
 */
@Entity
@Table(name = "sys_user_session")
public @Getter @Setter class UserSession extends BaseEntity<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5349953457939454847L;
	@Column
	private String username;
	@Column(name = "session_id")
	private String sessionId;
	@Column(name = "create_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;
}
