package com.chinesedreamer.rating.rating.vo;

/**
 * Description: 用户对是否参与某个投票
 * @author Paris
 * @date Jun 25, 201510:51:23 AM
 * @version beta
 */
public class RatingTemplateVo {
	private Long id;
	private String code;
	private String name;
	private Boolean voted;
	public Long getId() {
		return id;
	}
	public String getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	public Boolean getVoted() {
		return voted;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setVoted(Boolean voted) {
		this.voted = voted;
	}
	
	
}
