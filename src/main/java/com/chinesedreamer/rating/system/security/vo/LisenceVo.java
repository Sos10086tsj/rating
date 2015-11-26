package com.chinesedreamer.rating.system.security.vo;

import com.chinesedreamer.rating.system.security.constant.ActivationState;

public class LisenceVo {
	private ActivationState state;
	private Integer remaingDay;
	public ActivationState getState() {
		return state;
	}
	public Integer getRemaingDay() {
		return remaingDay;
	}
	public void setState(ActivationState state) {
		this.state = state;
	}
	public void setRemaingDay(Integer remaingDay) {
		this.remaingDay = remaingDay;
	}
	
	
}
