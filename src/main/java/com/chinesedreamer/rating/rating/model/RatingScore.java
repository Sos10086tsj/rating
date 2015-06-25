package com.chinesedreamer.rating.rating.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseVersionEntity;

/**
 * Description: 分数
 * @author Paris
 * @date Jun 25, 20151:10:08 PM
 * @version beta
 */
@Entity
@Table(name = "rating_score")
public @Getter @Setter class RatingScore extends BaseVersionEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8793825657539121773L;

	@Column(name = "name")
	private String name;
	
	@Column
	private Integer score;
}
