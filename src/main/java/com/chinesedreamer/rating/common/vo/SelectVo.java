package com.chinesedreamer.rating.common.vo;

/**
 * Description: 
 * @author Paris
 * @date Jun 24, 20154:48:42 PM
 * @version beta
 */
public class SelectVo {
	private String value;
	private String label;
	
	public SelectVo(String value,String label){
		this.value = value;
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
