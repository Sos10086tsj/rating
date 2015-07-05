package com.chinesedreamer.rating.system.group;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;


/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月18日 下午8:03:25 
 * Copyright:   Copyright (c)2015
 */
public enum UserGroupLevel {
	DEFAULT(0,"默认"),ZONGTI(1,"总体"),PUTONG(2,"普通");
	
	private @Getter final Integer value;
	private @Getter final String label;
	private UserGroupLevel(Integer value,String label){
		this.value = value;
		this.label = label;
	}
	
	public static UserGroupLevel get(Integer value){
		for (UserGroupLevel group : UserGroupLevel.values()) {
			if (group.getValue().equals(value)) {
				return group;
			}
		}
		return DEFAULT;
	}
	
	public static List<UserGroupLevel> initGroupLevels(){
		List<UserGroupLevel> levels = new ArrayList<UserGroupLevel>();
		levels.add(UserGroupLevel.ZONGTI);
		levels.add(UserGroupLevel.PUTONG);
		return levels;
	}
}
