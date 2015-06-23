package com.chinesedreamer.rating.template.mode;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 权重
 * @author Paris
 * @date Jun 23, 201512:20:26 PM
 * @version beta
 */
@Entity
@Table(name = "rating_supp_tmpl_option_weight")
public @Getter @Setter class RatingTmplOptionWeight extends BaseEntity<Long>{
	@Column(name = "tmpl_id")
	private Long tmplId;
	
	@Column(name = "option_id")
	private Long optionId;
	
	@Column
	private BigDecimal weight;
}
