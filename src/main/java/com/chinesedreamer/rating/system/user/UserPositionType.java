package com.chinesedreamer.rating.system.user;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015骞�鏈�1鏃�涓嬪崍3:29:11 
 * Copyright:   Copyright (c)2015
 */
public enum UserPositionType {
	LEADER(1,"组长"),TEAM_MATE(2,"组员");
	
	private final Integer value;
	private final String label;
	
	private UserPositionType(Integer value,String label){
		this.value = value;
		this.label = label;
	}
	
	public static UserPositionType get(Integer value){
		for (UserPositionType ut : UserPositionType.values()) {
			if (value == ut.getValue()) {
				return ut;
			}
		}
		return null;
	}

	public Integer getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	
}
