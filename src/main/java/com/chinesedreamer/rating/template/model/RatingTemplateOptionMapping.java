package com.chinesedreamer.rating.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.chinesedreamer.rating.base.jpa.model.BaseEntity;

/**
 * Description: 
 * @author Paris
 * @date Jun 23, 201511:12:48 AM
 * @version beta
 */
@Entity
@Table(name = "rating_tmpl_option")
public class RatingTemplateOptionMapping extends BaseEntity<Long>{
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
	private Float seq;

	public Long getTmplId() {
		return tmplId;
	}

	public RatingTemplate getTemplate() {
		return template;
	}

	public Long getOptionId() {
		return optionId;
	}

	public RatingSuppOption getOption() {
		return option;
	}

	public Float getSeq() {
		return seq;
	}

	public void setTmplId(Long tmplId) {
		this.tmplId = tmplId;
	}

	public void setTemplate(RatingTemplate template) {
		this.template = template;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public void setOption(RatingSuppOption option) {
		this.option = option;
	}

	public void setSeq(Float seq) {
		this.seq = seq;
	}
	
	
}
