package com.chinesedreamer.rating.template.vo;

import java.math.BigDecimal;

/**
 * Description: 
 * @author Paris
 * @date Jul 17, 20153:42:22 PM
 * @version beta
 */
public class OptionVo {
	private Long id;
	private String name;
	private String categoryCode;
	private String categoryName;
	
	private BigDecimal weight;//模板时才需要
	
	private String operation;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public String getOperation() {
		return operation;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	
}
