package com.chinesedreamer.rating.system.user.vo;

import lombok.Getter;
import lombok.Setter;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月21日 下午3:34:43 
 * Copyright:   Copyright (c)2015
 */
public @Getter @Setter class UserVo {
	private Long id;
	private String username;
	private String name;
	private String groupId;
	private String groupName;
	private String positionId;
	private String positionName;
	private String phone;
	private String status;
}
