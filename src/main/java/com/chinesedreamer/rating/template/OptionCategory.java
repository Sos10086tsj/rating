package com.chinesedreamer.rating.template;

import lombok.Getter;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201510:36:01 AM
 * @version beta
 */
public enum OptionCategory {
	BXDF("BXDF","表现得分"),
	NLDF("NLDF","能力得分"),
	WCRWQK("WCRWQK","完成任务情况"),
	ZZNL("ZZNL","组织能力"),
	ZHNL("ZHNL","综合能力");
	
	private @Getter final String code;
	private @Getter final String label;
	
	private OptionCategory(String code,String label){
		this.code = code;
		this.label = label;
	}
}
