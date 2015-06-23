package com.chinesedreamer.rating.template.mode;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:12:48 AM
 * @version beta
 */
@Entity
@Table(name = "rating_tmpl_option")
public @Getter @Setter class RatingTemplateOptionMapping extends BaseEntity<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6170783186682269121L;

	@Column(name = "tmpl_id")
	private Long tmplId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tmpl_id", referencedColumnName = "id", insertable = false, updatable = false)
	private RatingTemplate template;
	
	@Column(name = "option_id")
	private Long optionId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id", referencedColumnName = "id", insertable = false, updatable = false)
	private RatingSuppOption option;
	
	@Column
	private BigDecimal seq;
}
