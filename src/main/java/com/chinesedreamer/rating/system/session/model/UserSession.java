package com.chinesedreamer.rating.system.session.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 
 * @author Paris
 * @date Feb 9, 201511:34:21 AM
 * @version beta
 */
@Entity
@Table(name = "sys_user_session")
public class UserSession extends BaseEntity<Long>{
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
	public String getUsername() {
		return username;
	}
	public String getSessionId() {
		return sessionId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
