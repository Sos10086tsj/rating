package com.chinesedreamer.rating.system.group.vo;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年7月5日 上午8:51:07 
 * Copyright:   Copyright (c)2015
 */
public class GroupVo {
	private Long id;
	private Integer level;//用户组类型
	private String levelName;
	private String name;//组名
	public Long getId() {
		return id;
	}
	public Integer getLevel() {
		return level;
	}
	public String getLevelName() {
		return levelName;
	}
	public String getName() {
		return name;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
