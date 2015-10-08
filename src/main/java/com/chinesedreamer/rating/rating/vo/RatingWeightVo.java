package com.chinesedreamer.rating.rating.vo;

import java.math.BigDecimal;
/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年7月12日 上午9:40:47 
 * Copyright:   Copyright (c)2015
 */
public class RatingWeightVo {
	private String categoryCode;
	private String category;
	private Long id;
	private String name;
	private BigDecimal weight;
	public String getCategoryCode() {
		return categoryCode;
	}
	public String getCategory() {
		return category;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	
}
