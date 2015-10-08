package com.chinesedreamer.rating.rating.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 投票详情
 * @author Paris
 * @date Jun 25, 20151:03:05 PM
 * @version beta
 */
@Entity
@Table(name = "rating_user_vote_item")
public class RatingUserVoteItem extends BaseEntity<Long>{

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

	public Long getUserVoteId() {
		return userVoteId;
	}

	public Long getOptionId() {
		return optionId;
	}

	public Long getScoreId() {
		return scoreId;
	}

	public Long getScorer() {
		return scorer;
	}

	public Long getScorerGroup() {
		return scorerGroup;
	}

	public Integer getScorerPosition() {
		return scorerPosition;
	}

	public Float getScore() {
		return score;
	}

	public void setUserVoteId(Long userVoteId) {
		this.userVoteId = userVoteId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}

	public void setScorer(Long scorer) {
		this.scorer = scorer;
	}

	public void setScorerGroup(Long scorerGroup) {
		this.scorerGroup = scorerGroup;
	}

	public void setScorerPosition(Integer scorerPosition) {
		this.scorerPosition = scorerPosition;
	}

	public void setScore(Float score) {
		this.score = score;
	}
	
	
}
