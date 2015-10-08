package com.chinesedreamer.rating.template.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 权重
 * @author Paris
 * @date Jun 23, 201512:20:26 PM
 * @version beta
 */
@Entity
@Table(name = "rating_tmpl_option_weight")
public class RatingTmplOptionWeight extends BaseEntity<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3859649564176070497L;

	@Column(name = "tmpl_id")
	private Long tmplId;
	
	@Column(name = "option_id")
	private Long optionId;
	
	@Column
	private BigDecimal weight;

	public Long getTmplId() {
		return tmplId;
	}

	public Long getOptionId() {
		return optionId;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setTmplId(Long tmplId) {
		this.tmplId = tmplId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	
}
