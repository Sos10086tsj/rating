package com.chinesedreamer.rating.rating.vo;

import java.util.Date;
import java.util.List;

/**
 * Description: 用户查看投票列表
 * @author Paris
 * @date Jun 25, 201510:44:51 AM
 * @version beta
 */
public class RatingUserVo {
	private Long id;//投票id
	private String name;//投票事件
	private Date effFrom;//起始时间
	private Date effTo;//结束时间
	private Boolean overdue;
	private List<RatingTemplateVo> templates;
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
	public List<RatingTemplateVo> getTemplates() {
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
	public void setTemplates(List<RatingTemplateVo> templates) {
		this.templates = templates;
	}
	public Boolean getOverdue() {
		return overdue;
	}
	public void setOverdue(Boolean overdue) {
		this.overdue = overdue;
	}
	
	
}
