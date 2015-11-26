package com.chinesedreamer.rating.system.security.constant;

public enum ActivationState {
	ACTIVE(1),PROBATION(2),PROBATION_OVERDUE(3),REJECT(9);
	
	private final Integer value;
	
	private ActivationState(Integer value){
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
	
	
}
