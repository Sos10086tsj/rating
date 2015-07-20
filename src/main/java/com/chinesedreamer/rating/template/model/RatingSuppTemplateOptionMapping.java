package com.chinesedreamer.rating.template.model;

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
@Table(name = "rating_supp_tmpl_option")
public @Getter @Setter class RatingSuppTemplateOptionMapping extends BaseEntity<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1037652902932046004L;

	@Column(name = "supp_tmpl_id")
	private Long suppTmplId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supp_tmpl_id", referencedColumnName = "id", insertable = false, updatable = false)
	private RatingSuppTemplate suppTemplate;
	
	@Column(name = "option_id")
	private Long optionId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_id", referencedColumnName = "id", insertable = false, updatable = false)
	private RatingSuppOption option;
	
	@Column
	private Float seq;
}