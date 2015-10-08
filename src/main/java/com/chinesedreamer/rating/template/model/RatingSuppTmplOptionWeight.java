package com.chinesedreamer.rating.template.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseVersionEntity;

/**
 * Description: 权重
 * @author Paris
 * @date Jun 23, 201512:20:26 PM
 * @version beta
 */
@Entity
@Table(name = "rating_supp_tmpl_option_weight")
public class RatingSuppTmplOptionWeight extends BaseVersionEntity<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4262459170876448746L;

	@Column(name = "supp_tmpl_id")
	private Long suppTmplId;
	
	@Column(name = "supp_option_id")
	private Long suppOptionId;
	
	@Column
	private BigDecimal weight;

	public Long getSuppTmplId() {
		return suppTmplId;
	}

	public Long getSuppOptionId() {
		return suppOptionId;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setSuppTmplId(Long suppTmplId) {
		this.suppTmplId = suppTmplId;
	}

	public void setSuppOptionId(Long suppOptionId) {
		this.suppOptionId = suppOptionId;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	
}
