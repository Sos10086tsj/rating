package com.chinesedreamer.rating.rating.vo;

import java.util.Map;

import com.chinesedreamer.rating.rating.model.RatingUserVoteItem;

/** 
 * Description: 用户投票详情vo
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月27日 上午10:14:09 
 * Copyright:   Copyright (c)2015
 */
public class RatingUserVoteVo {
	private Long userId;//被投票用户id
	private String userName;//被投票用户姓名
	private Map<Long, RatingUserVoteItem> voteItems;//得分项和分数
	public Long getUserId() {
		return userId;
	}
	public String getUserName() {
		return userName;
	}
	public Map<Long, RatingUserVoteItem> getVoteItems() {
		return voteItems;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setVoteItems(Map<Long, RatingUserVoteItem> voteItems) {
		this.voteItems = voteItems;
	}
	
	
}
