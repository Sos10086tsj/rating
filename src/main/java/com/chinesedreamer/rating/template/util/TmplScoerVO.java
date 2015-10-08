package com.chinesedreamer.rating.template.util;

import com.chinesedreamer.rating.system.group.UserGroupLevel;
import com.chinesedreamer.rating.system.user.UserPositionType;

/**
 * Description: 
 * @author Paris
 * @date Jul 6, 20153:43:37 PM
 * @version beta
 */
public class TmplScoerVO {
	private UserGroupLevel group;
	private UserPositionType position;
	public UserGroupLevel getGroup() {
		return group;
	}
	public UserPositionType getPosition() {
		return position;
	}
	public void setGroup(UserGroupLevel group) {
		this.group = group;
	}
	public void setPosition(UserPositionType position) {
		this.position = position;
	}
	
	
}
