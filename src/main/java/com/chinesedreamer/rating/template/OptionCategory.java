package com.chinesedreamer.rating.template;

import lombok.Getter;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201510:36:01 AM
 * @version beta
 */
public enum OptionCategory {
	BXDF("BXDF","���ֵ÷�"),
	NLDF("NLDF","�����÷�"),
	WCRWQK("WCRWQK","����������"),
	ZZNL("ZZNL","��֯����"),
	ZHNL("ZHNL","�ۺ�����");
	
	private @Getter final String code;
	private @Getter final String label;
	
	private OptionCategory(String code,String label){
		this.code = code;
		this.label = label;
	}
}
