package com.chinesedreamer.rating.common.vo;

/**
 * Description: 
 * @author Paris
 * @date Jun 24, 20154:48:42 PM
 * @version beta
 */
public class OptionTitle {
	private String value;
	private String label;
	private Integer width;
	
	public OptionTitle(String value,String label,Integer width){
		this.value = value;
		this.label = label;
		this.width = width;
	}

	public String getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public Integer getWidth() {
		return width;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}
	
	
}
