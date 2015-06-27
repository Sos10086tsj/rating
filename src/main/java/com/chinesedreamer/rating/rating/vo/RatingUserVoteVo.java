package com.chinesedreamer.rating.rating.vo;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.rating.model.RatingUserVoteItem;

/** 
 * Description: 用户投票详情vo
 * @author Paris Tao
 * @version 1.0beta
 * @date 2015年6月27日 上午10:14:09 
 * Copyright:   Copyright (c)2015
 */
public @Getter @Setter class RatingUserVoteVo {
	private Long userId;//被投票用户id
	private String userName;//被投票用户姓名
	private Map<Long, RatingUserVoteItem> voteItems;//得分项和分数
}
