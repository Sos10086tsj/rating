package com.chinesedreamer.rating.system.group.vo;

import lombok.Setter;

import lombok.Getter;


/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年7月5日 上午8:51:07 
 * Copyright:   Copyright (c)2015
 */
public @Getter @Setter class GroupVo {
	private Long id;
	private Integer level;//用户组类型
	private String levelName;
	private String name;//组名
}
