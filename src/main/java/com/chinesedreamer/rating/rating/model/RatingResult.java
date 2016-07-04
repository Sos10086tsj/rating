package com.chinesedreamer.rating.rating.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 用表取代视图保存用户得分情况
 * Auth:Paris
 * Date:Jul 4, 2016
**/
@Entity
@Table(name="rating_result")
public class RatingResult extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1101616426838252492L;

	@Column(name = "tmpl_id")
	private Long tmplId;//模板id
	
	@Column(name = "voter_id")
	private Long voterId;//投票人id
	
	@Column(name = "voter_group_id")
	private Long voterGroupId;//投票人当时所在组
	
	@Column(name = "voter_position_id")
	private Integer voterPositionId;//投票人所在职位
	
	@Column
	private Long scorer;//得分人
	
	@Column(name = "scorer_group")
	private Long scorerGroup;//得分人所在组
	
	@Column(name = "scorer_position")
	private Integer scorerPosition;//得分人所在职位
	
	@Column(name = "option_id")
	private Long optionId;//得分项
	
	@Column
	private Float score;//得分

	public Long getTmplId() {
		return tmplId;
	}

	public Long getVoterId() {
		return voterId;
	}

	public Long getVoterGroupId() {
		return voterGroupId;
	}

	public Integer getVoterPositionId() {
		return voterPositionId;
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

	public Long getOptionId() {
		return optionId;
	}

	public Float getScore() {
		return score;
	}

	public void setTmplId(Long tmplId) {
		this.tmplId = tmplId;
	}

	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}

	public void setVoterGroupId(Long voterGroupId) {
		this.voterGroupId = voterGroupId;
	}

	public void setVoterPositionId(Integer voterPositionId) {
		this.voterPositionId = voterPositionId;
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

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public void setScore(Float score) {
		this.score = score;
	}
	
	
}
