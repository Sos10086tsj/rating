package com.chinesedreamer.rating.rating.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 投票详情
 * @author Paris
 * @date Jun 25, 20151:03:05 PM
 * @version beta
 */
@Entity
@Table(name = "rating_user_vote_item")
public @Getter @Setter class RatingUserVoteItem extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3695018656999771930L;

	@Column(name = "user_vote_id")
	private Long userVoteId;//投票记录外键
	
	@Column(name = "option_id")
	private Long optionId;//投票项外键
	
	@Column(name = "score_id")
	private Long scoreId;//得分外键
	
	@Column
	private Long scorer;//得分人
	
	@Column(name = "scorer_group")
	private Long scorerGroup;//得分人所在组
	
	@Column(name = "scorer_position")
	private Integer scorerPosition;//得分人所在职位
	
	@Column(name = "score")
	private Float score;
}
