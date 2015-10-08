package com.chinesedreamer.rating.rating.vo;

import java.util.Date;
import java.util.List;

import com.chinesedreamer.rating.common.vo.SelectVo;

/** 
 * Description: 
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年7月12日 上午9:19:13 
 * Copyright:   Copyright (c)2015
 */
public class RatingVo {
	private Long id;//rating id
	private String name;
	private Date effFrom;
	private Date effTo;
	private Boolean overdue;
	private List<SelectVo> templates;
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Date getEffFrom() {
		return effFrom;
	}
	public Date getEffTo() {
		return effTo;
	}
	public List<SelectVo> getTemplates() {
		return templates;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEffFrom(Date effFrom) {
		this.effFrom = effFrom;
	}
	public void setEffTo(Date effTo) {
		this.effTo = effTo;
	}
	public void setTemplates(List<SelectVo> templates) {
		this.templates = templates;
	}
	public Boolean getOverdue() {
		return overdue;
	}
	public void setOverdue(Boolean overdue) {
		this.overdue = overdue;
	}
	
	
}
