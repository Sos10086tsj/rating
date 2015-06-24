package com.chinesedreamer.rating.common.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Jun 24, 20154:48:42 PM
 * @version beta
 */
public @Getter @Setter class SelectVo {
	private String value;
	private String label;
	
	public SelectVo(String value,String label){
		this.value = value;
		this.label = label;
	}
}
