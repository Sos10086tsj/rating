package com.chinesedreamer.rating.rating.vo;

/** 
 * Description: 投票页vo
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月25日 下午8:39:20 
 * Copyright:   Copyright (c)2015
 */
public class RatingPageVo {
	private Long ratingId;//投票时间id
	private String ratingName;//投票时间名称
	private Long tmplId;//模板id
	private String tmplName;//模板名称
	public Long getRatingId() {
		return ratingId;
	}
	public String getRatingName() {
		return ratingName;
	}
	public Long getTmplId() {
		return tmplId;
	}
	public String getTmplName() {
		return tmplName;
	}
	public void setRatingId(Long ratingId) {
		this.ratingId = ratingId;
	}
	public void setRatingName(String ratingName) {
		this.ratingName = ratingName;
	}
	public void setTmplId(Long tmplId) {
		this.tmplId = tmplId;
	}
	public void setTmplName(String tmplName) {
		this.tmplName = tmplName;
	}
	
	
}
