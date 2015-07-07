package com.chinesedreamer.rating.rating.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 
 * @author Paris
 * @date Jul 2, 20159:40:51 AM
 * @version beta
 */
@Entity
@Table(name = "v_score")
public @Getter @Setter class RatingScoreView extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7655804488902492084L;

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
}
