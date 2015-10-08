package com.chinesedreamer.rating.rating.vo;

import java.util.Date;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:40:34 AM
 * @version beta
 */
public class RatingCreateVo {
	private Long id;
	private String name;
	private Long[] templateIds;
	private Date effFrom;
	private String effFromStr;
	private Date effTo;
	private String effToStr;
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Long[] getTemplateIds() {
		return templateIds;
	}
	public Date getEffFrom() {
		return effFrom;
	}
	public String getEffFromStr() {
		return effFromStr;
	}
	public Date getEffTo() {
		return effTo;
	}
	public String getEffToStr() {
		return effToStr;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTemplateIds(Long[] templateIds) {
		this.templateIds = templateIds;
	}
	public void setEffFrom(Date effFrom) {
		this.effFrom = effFrom;
	}
	public void setEffFromStr(String effFromStr) {
		this.effFromStr = effFromStr;
	}
	public void setEffTo(Date effTo) {
		this.effTo = effTo;
	}
	public void setEffToStr(String effToStr) {
		this.effToStr = effToStr;
	}
	
	
}
