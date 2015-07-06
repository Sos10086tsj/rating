package com.chinesedreamer.rating.template.util;

import com.chinesedreamer.rating.system.group.UserGroupLevel;
import com.chinesedreamer.rating.system.user.UserPositionType;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Jul 6, 20153:43:37 PM
 * @version beta
 */
public @Getter @Setter class TmplScoerVO {
	private UserGroupLevel group;
	private UserPositionType position;
}
