package com.chinesedreamer.rating.template.mode;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseVersionEntity;

/**
 * Description: 权重
 * @author Paris
 * @date Jun 23, 201512:20:26 PM
 * @version beta
 */
@Entity
@Table(name = "rating_supp_tmpl_option_weight")
public @Getter @Setter class RatingSuppTmplOptionWeight extends BaseVersionEntity<Long>{
	@Column(name = "supp_tmpl_id")
	private Long suppTmplId;
	
	@Column(name = "supp_option_id")
	private Long suppOptionId;
	
	@Column
	private BigDecimal weight;
}
