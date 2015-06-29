package com.chinesedreamer.rating.common.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Description: 
 * @author Paris
 * @date Jun 24, 20154:48:42 PM
 * @version beta
 */
public @Getter @Setter class OptionTitle {
	private String value;
	private String label;
	private Integer width;
	
	public OptionTitle(String value,String label,Integer width){
		this.value = value;
		this.label = label;
		this.width = width;
	}
}
