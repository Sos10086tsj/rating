package com.chinesedreamer.rating.rating.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 用户投票结果
 * Auth:Paris
 * Date:Jul 4, 2016
**/
public class RatingUserVoteResult {
	private String name;//得分用户
	private List<String> scores = new ArrayList<String>();//得分
	public String getName() {
		return name;
	}
	public List<String> getScores() {
		return scores;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setScores(List<String> scores) {
		this.scores = scores;
	}
	
	
}
