package com.chinesedreamer.rating.system.session.model;

import java.util.Date;

/**
 * Description: 
 * @author Paris
 * @date Feb 9, 201511:34:21 AM
 * @version beta
 */
public class UserSession {
	private String sessionId;
	private Date updateDate;
	
	
	
	public UserSession(String sessionId, Date updateDate) {
		super();
		this.sessionId = sessionId;
		this.updateDate = updateDate;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
